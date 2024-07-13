package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.dto.UserLoginDTO;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.exception.user.UserNotFoundException;
import top.chr0nix.maa4j.repository.UserRepository;
import top.chr0nix.maa4j.service.intf.UserService;
import top.chr0nix.maa4j.utils.JWTUtils;
import top.chr0nix.maa4j.utils.Result;
import top.chr0nix.maa4j.utils.SnowFlake;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private SnowFlake idGenerator;

    @Autowired
    public void setUserRepo(UserRepository repo){
        this.userRepo = repo;
    }

    @Autowired
    public void setIdGenerator(SnowFlake snowFlake){
        this.idGenerator = snowFlake;
    }

    @Override
    public Result<String> addUser(AddUserDTO addUserDTO) {
        try {
            if (addUserDTO != null){
                UserEntity userEntity = new UserEntity();
                Long id = idGenerator.nextId();
                //UserEntity userEntity = UserEntity.builder().id(id).name(addUserDTO.getName()).password(addUserDTO.getPassword()).r
                userEntity.setId(id);
                userEntity.setName(addUserDTO.getName());
                userEntity.setPassword(addUserDTO.getPassword());
                userEntity.setRegisterTime(LocalDateTime.now());
                userEntity.setGameKey(UUID.randomUUID().toString().replace("-", ""));
                userRepo.save(userEntity);
                return Result.success();
            } else {
                return Result.paramError("参数有误");
            }
        } catch (DataIntegrityViolationException e) {
            return Result.failed("用户已存在!");
        }
    }

    @Override
    public Result<String> loginUser(UserLoginDTO userLoginDTO) {
        if (userLoginDTO.getName() == null || userLoginDTO.getPassword() == null) {
            return Result.paramError("用户名和密码不能为空！");
        }
        var user = userRepo.findFirstByNameAndPassword(userLoginDTO.getName(), userLoginDTO.getPassword());
        if (user != null) {
            user.setLastLogin(LocalDateTime.now());
            userRepo.saveAndFlush(user);
            return Result.success(JWTUtils.generateTokenForUser(user), "登录成功！");
        } else  {
            return  Result.failed("用户名或密码错误！");
        }
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public int deleteUser(Long id) {
        return 0;
    }

    @Override
    public void addAccountToUser(Long accountId, Long userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
        ArrayList<Long> accounts = user.getAccounts();
        if (accounts == null) {
            accounts = new ArrayList<Long>(List.of(accountId));
        }else {
            accounts.add(accountId);
        }
        user.setAccounts(accounts);
        userRepo.saveAndFlush(user);
    }

    @Override
    public boolean hasAccount(String account, Long userId) {
        UserEntity user = userRepo.findById(userId).orElse(new UserEntity());
        return hasAccountId(user.getId(), user);
    }

    @Override
    public boolean hasAccountId(Long accountId, Long userId) {
        return hasAccountId(accountId, userRepo.findById(userId).orElse(new UserEntity()));
    }

    @Override
    public boolean hasAccountId(Long accountId, UserEntity user) {
        return user.getAccounts().contains(accountId);
    }

}
