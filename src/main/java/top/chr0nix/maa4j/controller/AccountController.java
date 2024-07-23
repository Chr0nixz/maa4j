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

import java.util.LinkedHashMap;

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
    @PostMapping("/start")
    public Result<String> start(@RequestHeader("Authorization") String token) {
        /*
        String account = JWTUtils.getId(token).toString();
        maaService.createInstance(JWTUtils.getId(token).toString(), "127.0.0.1:16384", null);
        System.out.println(maaService.appendTask(account, new StartUpTask("Official", false, "114514")));
        maaService.start(account);
        */
        return Result.success();
    }

    @UserLogin
    @OwnerVerify
    @PostMapping("/config/{account}")
    public Result<String> postConfig(@RequestHeader("Authorization") String token,
                                     @RequestBody LinkedHashMap<String, Object> accountConfigMap,
                                     @PathVariable String account) {
        Gson gson = new Gson();
        AccountConfigDTO accountConfigDTO = gson.fromJson(accountConfigMap.toString(), AccountConfigDTO.class);
        return accountService.updateConfig(accountConfigDTO, JWTUtils.getId(token), account);
    }

    @UserLogin
    @OwnerVerify
    @GetMapping("config/{account}")
    public Result<String> getConfig(@RequestHeader("Authorization") String token,
                                    @PathVariable String account) {
        return accountService.getConfig(account, JWTUtils.getId(token));
    }

}
