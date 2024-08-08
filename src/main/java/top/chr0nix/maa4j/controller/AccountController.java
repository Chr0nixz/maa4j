package top.chr0nix.maa4j.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chr0nix.maa4j.annotation.OwnerVerify;
import top.chr0nix.maa4j.annotation.UserLogin;
import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.dto.UpdateAccountConfigDTO;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.utils.JWTUtils;
import top.chr0nix.maa4j.utils.Result;

@RestController
@RequestMapping("/account")
@ResponseBody
@Tag(name = "账号控制")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @UserLogin
    @PostMapping("/add")
    @Operation(summary = "账号添加")
    @Parameters({
            @Parameter(name = "Authorization", description = "用户token", required = true, in = ParameterIn.HEADER),
    })
    public Result<String> addAccount(@RequestHeader("Authorization") String token,
                             @RequestBody AddAccountDTO addAccountDTO) {
        return accountService.addAccount(addAccountDTO, JWTUtils.getId(token));
    }

    @UserLogin
    @OwnerVerify
    @DeleteMapping("/delete/{account}")
    @Operation(summary = "删除账号")
    @Parameters({
            @Parameter(name = "Authorization", description = "用户token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "account", description = "账号", required = true, in = ParameterIn.PATH)
    })
    public Result<String> deleteAccount(@PathVariable String account) {
        return accountService.deleteAccount(account);
    }

    @UserLogin
    @OwnerVerify
    @PostMapping("/config/{account}")
    @Operation(summary = "更改账号设置")
    @Parameters({
            @Parameter(name = "Authorization", description = "用户token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "account", description = "账号", required = true, in = ParameterIn.PATH)
    })
    public Result<String> postConfig(@RequestBody AccountConfigDTO accountConfigDTO,
                                     @PathVariable String account) {
        return accountService.updateConfig(accountConfigDTO, account);
    }

    @UserLogin
    @OwnerVerify
    @PostMapping("/config/update/{account}")
    @Operation(summary = "更改账号单项设置")
    @Parameters({
            @Parameter(name = "Authorization", description = "用户token", required = true ,in = ParameterIn.HEADER),
            @Parameter(name = "account", description = "账号", required = true, in = ParameterIn.PATH)
    })
    public Result<String> updateConfig(@RequestBody UpdateAccountConfigDTO updateAccountConfigDTO,
                                       @PathVariable String account) {
        return accountService.updateConfig(updateAccountConfigDTO, account);
    }

    @UserLogin
    @OwnerVerify
    @GetMapping("/config/{account}")
    @Operation(summary = "获取账号设置")
    @Parameters({
            @Parameter(name = "Authorization", description = "用户token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "account", description = "账号", required = true, in = ParameterIn.PATH)
    })
    public Result<String> getConfig(@PathVariable String account) {
        return accountService.getConfig(account);
    }

    @UserLogin
    @OwnerVerify
    @PostMapping("/start/{account}")
    @Operation(summary = "账号开启任务")
    @Parameters({
            @Parameter(name = "Authorization", description = "用户token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "account", description = "账号", required = true, in = ParameterIn.PATH)
    })
    public Result<String> startAccount(@PathVariable String account) {
        return accountService.startAccount(account);
    }

}
