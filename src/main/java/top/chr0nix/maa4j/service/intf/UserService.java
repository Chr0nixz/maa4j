package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.dto.UserLoginDTO;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.utils.Result;

public interface UserService {

    Result<String> addUser(AddUserDTO user);

    Result<String> loginUser(UserLoginDTO userLoginDTO);

    UserEntity getUserById(Long id);

    int deleteUser(Long id);

    void addAccountToUser(Long accountId, Long id);

}
