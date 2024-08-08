package top.chr0nix.maa4j.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.dto.UpdateAccountConfigDTO;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.entity.taskConfig.AccountConfig;
import top.chr0nix.maa4j.exception.account.AccountBannedException;
import top.chr0nix.maa4j.exception.account.AccountNotFoundException;
import top.chr0nix.maa4j.message.AccountMessages;
import top.chr0nix.maa4j.message.ConfigMessages;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.service.intf.UserService;
import top.chr0nix.maa4j.utils.AESUtils;
import top.chr0nix.maa4j.utils.Result;
import top.chr0nix.maa4j.utils.SnowFlake;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepo;
    private final UserService userService;
    private final SnowFlake idGenerator;
    private final Gson gson;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              UserService userService,
                              SnowFlake idGenerator,
                              Gson gson) {
        this.accountRepo = accountRepository;
        this.userService = userService;
        this.idGenerator = idGenerator;
        this.gson = gson;
    }


    @Override
    public Result<String> addAccount(AddAccountDTO addAccountDTO, Long ownerId) {
        Long id = idGenerator.nextId();
        UserEntity user = userService.getUserById(ownerId);
        AccountEntity account = AccountEntity.builder()
                .id(id)
                .account(addAccountDTO.getAccount())
                .owner(ownerId)
                .password(AESUtils.encrypt(addAccountDTO.getPassword(), user.getGameKey()))
                .build();
        accountRepo.save(account);
        userService.addAccountToUser(account.getId(), account.getOwner());
        return Result.success(AccountMessages.ADD_ACCOUNT_SUCCESS);
    }

    @Override
    public Result<String> deleteAccount(String account) {
        accountRepo.deleteByAccount(account);
        return Result.success(AccountMessages.ACCOUNT_DELETE_SUCCESS);
    }

    @Override
    public Long getIdByAccount(String account) throws AccountNotFoundException {
        AccountEntity accountEntity = accountRepo.findFirstByAccount(account);
        if (accountEntity == null) {
            throw new AccountNotFoundException();
        }
        return accountEntity.getId();
    }

    @Override
    public Result<String> updateConfig(AccountConfigDTO accountConfigDTO, String account) {
        AccountConfig accountConfig = new AccountConfig();
        accountConfig.loadDTO(accountConfigDTO);
        AccountEntity accountEntity = accountRepo.findFirstByAccount(account);
        accountEntity.setConfig(accountConfig);
        accountRepo.saveAndFlush(accountEntity);
        return Result.success(ConfigMessages.CONFIG_SUCESS);
    }

    @Override
    public Result<String> updateConfig(UpdateAccountConfigDTO updateAccountConfigDTO, String account) {
        AccountEntity accountEntity = accountRepo.findFirstByAccount(account);
        String property = updateAccountConfigDTO.getProperty();
        String value = updateAccountConfigDTO.getValue();
        if (!property.contains(".")) {
            try {
                accountEntity.getConfig().getClass().getDeclaredField(property);
            } catch (NoSuchFieldException e){
                return Result.failed();
            }
        }
        String[] strings = property.split("[.]");
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        String target = list.getLast();
        list.removeLast();
        Object ptr = accountEntity.getConfig();
        for (String str : list) {
            try {
                Field field = ptr.getClass().getDeclaredField(str);
                field.setAccessible(true);
                ptr = field.get(ptr);
            } catch (NoSuchFieldException | IllegalAccessException e){
                return Result.failed();
            }
        }
        try {
            Field field = ptr.getClass().getDeclaredField(target);
            field.setAccessible(true);
            Object object = field.get(ptr);
            String name = "set" + target.substring(0, 1).toUpperCase() + target.substring(1);
            if (object instanceof Integer) {
                Method method = ptr.getClass().getDeclaredMethod(name, Integer.TYPE);
                method.setAccessible(true);
                method.invoke(ptr, Integer.parseInt(value));
            } else {
                var clazz = object.getClass();
                Method method = ptr.getClass().getDeclaredMethod(name, clazz);
                method.setAccessible(true);
                if (value.startsWith("{")) {
                    method.invoke(ptr, gson.fromJson(value, clazz));
                } else {
                    method.invoke(ptr, clazz.cast(value));
                }
            }
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
        accountRepo.saveAndFlush(accountEntity);
        return Result.success();
    }

    @Override
    public Result<String> getConfig(String account) {
        AccountConfig accountConfig = accountRepo.findFirstByAccount(account).getConfig();
        Gson gson = new Gson();
        return Result.success(gson.toJson(accountConfig), "成功");
    }

    @Override
    public String getPassword(AccountEntity accountEntity) {
        UserEntity owner = userService.getUserById(accountEntity.getOwner());
        String gameKey = owner.getGameKey();
        return AESUtils.decrypt(accountEntity.getPassword(), gameKey);
    }

    @Override
    public void gameLogin(String account) {
        accountRepo.saveAndFlush(accountRepo.findFirstByAccount(account).setLastLogin(LocalDateTime.now()));
    }

    @Override
    public Result<String> startAccount(String account) {
        AccountEntity accountEntity = accountRepo.findFirstByAccount(account);
        if (accountEntity.isBanned()) {
            throw new AccountBannedException();
        }
        accountRepo.saveAndFlush(accountEntity.setRunning(true));
        return Result.success();
    }

}
