package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.service.intf.UserService;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepo;
    private UserService userService;

    @Autowired
    public void setAccountRepo(AccountRepository repo){
        this.accountRepo = repo;
    }

    @Autowired
    public void setUserService(UserService service){
        this.userService = service;
    }

    @Override
    public int addAccount(AddAccountDTO accountDTO) {
        AccountEntity account = new AccountEntity();
        Long id = Long.valueOf(UUID.randomUUID().toString().replace("-",""));
        account.setId(id);
        account.setAccount(accountDTO.getAccount());
        account.setPassword(accountDTO.getPassword());
        account.setOwner(accountDTO.getOwner());
        accountRepo.save(account);
        AccountEntity addedUser = accountRepo.findFirstByAccount(accountDTO.getAccount());
        userService.addAccountToUser(addedUser.getId(), addedUser.getOwner());
        return 200;
    }

}
