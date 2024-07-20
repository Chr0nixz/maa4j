package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.entity.taskConfig.AccountConfig;
import top.chr0nix.maa4j.exception.account.AccountNotFoundException;
import top.chr0nix.maa4j.exception.config.WrongFightConfigException;
import top.chr0nix.maa4j.exception.config.WrongInfrastConfigException;
import top.chr0nix.maa4j.exception.config.WrongRecruitConfigException;
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
        try {
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
            return Result.success("添加成功！");
        } catch (DataIntegrityViolationException e) {
            return Result.failed("账号已存在！");
        } catch (Exception e) {
            return Result.failed("添加失败!");
        }
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
        try {
            accountConfigDTO.setAccountId(getIdByAccount(account));
            AccountConfig accountConfig = new AccountConfig();
            accountConfig.loadDTO(accountConfigDTO);
            AccountEntity accountEntity = accountRepo.findFirstById(accountConfigDTO.getAccountId());
            accountEntity.setConfig(accountConfig);
            accountRepo.saveAndFlush(accountEntity);
        } catch (WrongFightConfigException | WrongInfrastConfigException | WrongRecruitConfigException e) {
            return Result.paramError(e.getMessage());
        } catch (AccountNotFoundException e){
            return Result.notFound(e.getMessage());
        }
        return Result.success(ConfigMessages.CONFIG_SUCESS);
    }

    @Override
    public Result<String> getConfig(AccountConfigDTO accountConfigDTO, Long ownerId) {
        return null;
    }

    @Override
    public String getPassword(AccountEntity accountEntity) throws Exception {
        UserEntity owner = userService.getUserById(accountEntity.getOwner());
        String gameKey = owner.getGameKey();
        return AESUtils.decrypt(accountEntity.getPassword(), gameKey);
    }

    @Override
    public void gameLogin(String account) {
        accountRepo.saveAndFlush(accountRepo.findFirstByAccount(account).setLastLogin(LocalDateTime.now()));
    }

}
