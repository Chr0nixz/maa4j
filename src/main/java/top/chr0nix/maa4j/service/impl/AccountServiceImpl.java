package top.chr0nix.maa4j.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.dto.AddAccountDTO;
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

import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepo;
    private UserService userService;
    private SnowFlake idGenerator;

    @Autowired
    public void setAccountRepo(AccountRepository repo){
        this.accountRepo = repo;
    }

    @Autowired
    public void setUserService(UserService service){
        this.userService = service;
    }

    @Autowired
    public void setIdGenerator(SnowFlake snowFlake){
        this.idGenerator = snowFlake;
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
    public Long getIdByAccount(String account) throws AccountNotFoundException {
        AccountEntity accountEntity = accountRepo.findFirstByAccount(account);
        if (accountEntity == null) {
            throw new AccountNotFoundException();
        }
        return accountEntity.getId();
    }

    @Override
    public Result<String> updateConfig(AccountConfigDTO accountConfigDTO, Long ownerId, String account) {
        accountConfigDTO.setAccountId(getIdByAccount(account));
        AccountConfig accountConfig = new AccountConfig();
        accountConfig.loadDTO(accountConfigDTO);
        AccountEntity accountEntity = accountRepo.findFirstById(accountConfigDTO.getAccountId());
        accountEntity.setConfig(accountConfig);
        accountRepo.saveAndFlush(accountEntity);
        return Result.success(ConfigMessages.CONFIG_SUCESS);
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
