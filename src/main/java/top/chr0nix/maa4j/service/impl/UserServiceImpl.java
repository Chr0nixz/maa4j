package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.repository.UserRepository;
import top.chr0nix.maa4j.service.intf.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Autowired
    public UserServiceImpl(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public int addUser(AddUserDTO user) {
        if (user != null){
            UserEntity userEntity = new UserEntity();
            userEntity.setName(user.getName());
            userEntity.setPassword(user.getPassword());
        }
        return 200;
    }

    @Override
    public int deleteUser(Long id) {
        return 0;
    }
}
