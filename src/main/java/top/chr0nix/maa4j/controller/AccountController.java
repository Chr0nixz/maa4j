package top.chr0nix.maa4j.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chr0nix.maa4j.annotation.OwnerVerify;
import top.chr0nix.maa4j.annotation.UserLogin;
import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.utils.JWTUtils;
import top.chr0nix.maa4j.utils.Result;

import java.util.HashMap;

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

    @UserLogin
    @OwnerVerify
    @PostMapping("/config/{account}")
    public Result<String> postConfig(@RequestHeader("Authorization") String token,
                                     @RequestBody HashMap<String, Object> accountConfigMap,
                                     @PathVariable String account) {
        Gson gson = new Gson();
        AccountConfigDTO accountConfigDTO = gson.fromJson(accountConfigMap.toString(), AccountConfigDTO.class);
        return accountService.updateConfig(accountConfigDTO, JWTUtils.getId(token), account);
    }

    @UserLogin
    @OwnerVerify
    @GetMapping("/config/{account}")
    public Result<String> getConfig(@PathVariable String account) {
        return accountService.getConfig(account);
    }

    @UserLogin
    @OwnerVerify
    @PostMapping("/start/{account}")
    public Result<String> startAccount(@PathVariable String account) {
        return accountService.startAccount(account);
    }

}
