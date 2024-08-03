package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.utils.Result;

public interface UserService {

    Result<String> addUser(AddUserDTO user);

    Result<String> loginUser(String name, String password);

    UserEntity getUserById(Long id);

    int deleteUser(Long id);

    void addAccountToUser(Long accountId, Long id);

    boolean hasAccount(String account, Long userId);

    boolean hasAccountId(Long accountId, Long userId);

    boolean hasAccountId(Long accountId, UserEntity user);

}
