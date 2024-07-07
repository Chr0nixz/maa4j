package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.entity.config.AccountConfig;
import top.chr0nix.maa4j.exception.config.WrongFightConfigException;
import top.chr0nix.maa4j.exception.config.WrongInfrastConfigException;
import top.chr0nix.maa4j.exception.config.WrongRecruitConfigException;
import top.chr0nix.maa4j.message.AccountMessages;
import top.chr0nix.maa4j.message.ConfigMessages;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.service.intf.UserService;
import top.chr0nix.maa4j.utils.Encoder;
import top.chr0nix.maa4j.utils.JWTUtils;
import top.chr0nix.maa4j.utils.Result;
import top.chr0nix.maa4j.utils.SnowFlake;

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
            AccountEntity account = new AccountEntity();
            Long id = idGenerator.nextId();
            account.setId(id);
            account.setAccount(addAccountDTO.getAccount());
            account.setOwner(ownerId);
            UserEntity user = userService.getUserById(account.getOwner());
            account.setPassword(Encoder.aesEncrypt(addAccountDTO.getPassword(), user.getGame_key()));
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
    public Result<String> updateConfig(AccountConfigDTO accountConfigDTO, Long ownerId) {
        var a = userService.getUserById(ownerId).getAccounts();
        if (!userService.getUserById(ownerId).getAccounts().contains(accountConfigDTO.getAccountId())) {
            return Result.forbidden(AccountMessages.NOT_BELONGING);
        }
        try {
            AccountConfig accountConfig = new AccountConfig();
            accountConfig.loadDTO(accountConfigDTO);
            AccountEntity account = accountRepo.findFirstById(accountConfigDTO.getAccountId());
            account.setConfig(accountConfig);
            accountRepo.saveAndFlush(account);
        } catch (WrongFightConfigException e) {
            return Result.paramError(e.getMessage());
        } catch (WrongInfrastConfigException e) {
            return Result.paramError(e.getMessage());
        } catch (WrongRecruitConfigException e) {
            return Result.paramError(e.getMessage());
        }
        return Result.success(ConfigMessages.CONFIG_SUCESS);
    }

}
