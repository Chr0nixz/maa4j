package top.chr0nix.maa4j.controller;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chr0nix.maa4j.annotation.Authority;
import top.chr0nix.maa4j.constant.AdminAuthority;
import top.chr0nix.maa4j.dto.AdminLoginDTO;
import top.chr0nix.maa4j.dto.GetPageDTO;
import top.chr0nix.maa4j.service.intf.AdminService;
import top.chr0nix.maa4j.utils.Result;
import top.chr0nix.maa4j.utils.SortHandler;

import java.util.HashMap;

@RestController
@ResponseBody
@RequestMapping("/admin")
@Tag(name = "管理员控制")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public Result<String> loginAdmin(@RequestBody AdminLoginDTO adminLoginDTO) {
        return adminService.loginAdmin(adminLoginDTO.getName(), adminLoginDTO.getPassword());
    }

    @Authority(AdminAuthority.READ_USERS)
    @GetMapping("/users")
    @Operation(summary = "读取用户")
    public Result<String> readUsers(@RequestBody GetPageDTO getPageDTO) {
        return adminService.readUsers(getPageDTO.getPageNum() - 1, getPageDTO.getSize(), SortHandler.getSort(getPageDTO.getOrders()));
    }

    @Authority(AdminAuthority.READ_ACCOUNTS)
    @GetMapping("/accounts")
    @Operation(summary = "读取账号")
    public Result<String> readAccounts(@RequestBody HashMap<String, Object> pageMap) {
        Gson gson = new Gson();
        GetPageDTO getPageDTO = gson.fromJson(pageMap.toString(), GetPageDTO.class);
        return adminService.readAccounts(getPageDTO.getPageNum() - 1, getPageDTO.getSize(), SortHandler.getSort(getPageDTO.getOrders()));
    }

    @Authority(AdminAuthority.READ_DEVICES)
    @GetMapping("/devices")
    @Operation(summary = "读取设备")
    public  Result<String> readDevices() {
        return adminService.readDevices();
    }

}
