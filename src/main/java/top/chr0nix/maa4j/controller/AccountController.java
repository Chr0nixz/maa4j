package top.chr0nix.maa4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chr0nix.maa4j.annotation.UserLogin;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.exception.UserNotFoundException;
import top.chr0nix.maa4j.maa.MaaTasks.StartUpTask;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.service.intf.MaaService;
import top.chr0nix.maa4j.utils.JWTUtils;
import top.chr0nix.maa4j.utils.Result;

@RestController
@RequestMapping("/account")
@ResponseBody
public class AccountController {

    private AccountService accountService;
    private MaaService maaService;

    @Autowired
    public void setAccountService(AccountService service){
        this.accountService = service;
    }

    @Autowired
    public void setMaaService(MaaService service) {
        this.maaService = service;
    }

    @UserLogin
    @PostMapping("/add")
    public Result<String> addAccount(@RequestHeader("Authorization") String token,
                             @RequestBody AddAccountDTO accountDTO) {
        return accountService.addAccount(accountDTO, JWTUtils.getId(token));
    }

    @UserLogin
    @PostMapping("/start")
    public Result<String> start(@RequestHeader("Authorization") String token) {
        String account = JWTUtils.getId(token).toString();
        maaService.createInstance(JWTUtils.getId(token).toString(), "127.0.0.1:16384", null);
        System.out.println(maaService.appendTask(account, new StartUpTask("Official", false, "114514")));
        maaService.start(account);
        return Result.success();
    }

}
