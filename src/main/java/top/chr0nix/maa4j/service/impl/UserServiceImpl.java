package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.dto.UserLoginDTO;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.exception.UserNotFoundException;
import top.chr0nix.maa4j.repository.UserRepository;
import top.chr0nix.maa4j.service.intf.UserService;
import top.chr0nix.maa4j.utils.Encoder;
import top.chr0nix.maa4j.utils.JWTUtils;
import top.chr0nix.maa4j.utils.Result;
import top.chr0nix.maa4j.utils.SnowFlake;

import java.time.LocalDateTime;
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
    public Result<String> addUser(AddUserDTO user) {
        try {
            if (user != null){
                UserEntity userEntity = new UserEntity();
                Long id = idGenerator.nextId();
                userEntity.setId(id);
                userEntity.setName(user.getName());
                userEntity.setPassword(user.getPassword());
                userEntity.setRegister_time(LocalDateTime.now());
                userEntity.setGame_key(UUID.randomUUID().toString().replace("-", ""));
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
            user.setLast_login(LocalDateTime.now());
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
    public UserEntity addAccountToUser(Long accountId, Long userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
        List<String> accounts = user.getAccounts();
        if (accounts == null) {
            accounts = List.of(accountId.toString());
        }else {
            accounts.add(accountId.toString());
        }
        user.setAccounts(accounts);
        userRepo.saveAndFlush(user);
        return user;
    }

}
