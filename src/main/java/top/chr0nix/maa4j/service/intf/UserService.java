package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.dto.UserLoginDTO;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.utils.Result;

public interface UserService {

    public Result<String> addUser(AddUserDTO user);

    public Result<String> loginUser(UserLoginDTO userLoginDTO);

    public UserEntity getUserById(Long id);

    public int deleteUser(Long id);

    public boolean addAccountToUser(Long accountId, Long id);

}
