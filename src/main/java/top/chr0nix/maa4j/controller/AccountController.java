package top.chr0nix.maa4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.exception.UserNotFoundException;
import top.chr0nix.maa4j.service.intf.AccountService;

@RestController
@RequestMapping("/account")
@ResponseBody
public class AccountController {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService service){
        this.accountService = service;
    }

    @PostMapping("/add")
    public int addAccount(@RequestBody AddAccountDTO accountDTO){
        try {
            accountService.addAccount(accountDTO);
        }catch (UserNotFoundException e){
            return 500;
        }
        return 200;
    }

}
