package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.exception.UserNotFoundException;
import top.chr0nix.maa4j.repository.UserRepository;
import top.chr0nix.maa4j.service.intf.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Autowired
    public void setUserRepo(UserRepository repo){
        this.userRepo = repo;
    }

    @Override
    public int addUser(AddUserDTO user) {
        if (user != null){
            UserEntity userEntity = new UserEntity();
            userEntity.setName(user.getName());
            userEntity.setPassword(user.getPassword());
            userRepo.save(userEntity);
        }
        return 200;
    }

    @Override
    public int deleteUser(Long id) {
        return 0;
    }

    @Override
    public int addAccountToUser(Long accountId, Long userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(""));
        List<String> accounts = user.getAccounts();
        if (accounts == null) {
            accounts = List.of(accountId.toString());
        }else {
            accounts.add(accountId.toString());
        }
        user.setAccounts(accounts);
        userRepo.saveAndFlush(user);
        return 200;
    }

}
