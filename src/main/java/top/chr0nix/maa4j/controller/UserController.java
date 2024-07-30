package top.chr0nix.maa4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.dto.UserLoginDTO;
import top.chr0nix.maa4j.service.intf.UserService;
import top.chr0nix.maa4j.utils.Result;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<String> userLogin(@RequestBody UserLoginDTO userLoginDTO){
        return userService.loginUser(userLoginDTO.getName(), userLoginDTO.getPassword());
    }

    @PostMapping("/register")
    public Result<String> addUser(@RequestBody AddUserDTO userDTO){
        return userService.addUser(userDTO);
    }

}
