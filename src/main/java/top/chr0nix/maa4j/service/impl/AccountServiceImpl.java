package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.service.intf.UserService;
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
    public Result<String> addAccount(AddAccountDTO accountDTO, Long ownerId) {
        try {
            AccountEntity account = new AccountEntity();
            Long id = idGenerator.nextId();
            account.setId(id);
            account.setAccount(accountDTO.getAccount());
            account.setPassword(accountDTO.getPassword());
            account.setOwner(ownerId);
            userService.addAccountToUser(account.getId(), account.getOwner());
            accountRepo.save(account);
            return Result.success("添加成功！");
        } catch (DataIntegrityViolationException e) {
            return Result.failed("账号已存在！");
        }
    }

}
