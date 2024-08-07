package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.dto.AddUserDTO;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.exception.user.UserNotFoundException;
import top.chr0nix.maa4j.message.UserMessages;
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
    public void setUserRepo(UserRepository repo) {
        this.userRepo = repo;
    }

    @Autowired
    public void setIdGenerator(SnowFlake snowFlake) {
        this.idGenerator = snowFlake;
    }

    @Override
    public Result<String> addUser(AddUserDTO addUserDTO) {
        if (addUserDTO != null) {
            Long id = idGenerator.nextId();
            UserEntity userEntity = UserEntity.builder()
                    .id(id)
                    .name(addUserDTO.getName())
                    .password(addUserDTO.getPassword())
                    .registerTime(LocalDateTime.now())
                    .gameKey(UUID.randomUUID().toString().replace("-", ""))
                    .build();
            userRepo.save(userEntity);
            return Result.success(UserMessages.ADD_USER_SUCCESS);
        } else {
            return Result.paramError(UserMessages.USER_PARAM_ERROR);
        }
    }

    @Override
    public Result<String> loginUser(String name, String password) {
        if (name == null || password == null) {
            return Result.paramError(UserMessages.EMPTY_NAME_OR_PASSWORD);
        }
        var user = userRepo.findFirstByNameAndPassword(name, password);
        if (user != null) {
            if (user.getLoginKey() == null) {
                user.setLoginKey(UUID.randomUUID().toString().replace("-", ""));
            }
            user.setLastLogin(LocalDateTime.now());
            userRepo.saveAndFlush(user);
            return Result.success(JWTUtils.generateTokenForUser(user), UserMessages.LOGIN_SUCCESS);
        } else  {
            return  Result.failed(UserMessages.WRONG_NAME_OR_PASSWORD);
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
            accounts = new ArrayList<>(List.of(accountId));
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
