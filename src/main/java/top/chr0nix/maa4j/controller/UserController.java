package top.chr0nix.maa4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.repository.UserRepository;
import top.chr0nix.maa4j.service.intf.UserService;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/add")
    public int addUser(@RequestBody AddUserDTO userDTO){
        userService.addUser(userDTO);
        return 200;
    }

}
