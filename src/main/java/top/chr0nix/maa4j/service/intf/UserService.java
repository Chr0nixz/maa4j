package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.dto.AddUserDTO;

public interface UserService {

    public int addUser(AddUserDTO user);

    public int deleteUser(Long id);

}
