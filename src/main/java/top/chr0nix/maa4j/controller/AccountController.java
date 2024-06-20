package top.chr0nix.maa4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chr0nix.maa4j.annotation.UserLogin;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.exception.UserNotFoundException;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.utils.JWTUtils;
import top.chr0nix.maa4j.utils.Result;

@RestController
@RequestMapping("/account")
@ResponseBody
public class AccountController {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService service){
        this.accountService = service;
    }

    @UserLogin
    @PostMapping("/add")
    public Result<String> addAccount(@RequestHeader("Authorization") String token,
                             @RequestBody AddAccountDTO accountDTO) {
        return accountService.addAccount(accountDTO, JWTUtils.getId(token));
    }

}
