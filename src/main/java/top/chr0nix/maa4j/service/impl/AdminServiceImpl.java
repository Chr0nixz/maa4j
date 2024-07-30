package top.chr0nix.maa4j.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.entity.UserEntity;
import top.chr0nix.maa4j.message.AdminMessages;
import top.chr0nix.maa4j.message.UserMessages;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.repository.AdminRepository;
import top.chr0nix.maa4j.repository.UserRepository;
import top.chr0nix.maa4j.service.intf.AdminService;
import top.chr0nix.maa4j.utils.JWTUtils;
import top.chr0nix.maa4j.utils.Result;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final Gson gson;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository,
                            UserRepository userRepository,
                            AccountRepository accountRepository,
                            Gson gson) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.gson = gson;
    }


    @Override
    public Result<String> loginAdmin(String name, String password) {
        if (name == null || password == null) {
            return Result.paramError(AdminMessages.EMPTY_NAME_OR_PASSWORD);
        }
        var admin = adminRepository.findFirstByNameAndPassword(name, password);
        if (admin == null) {
            return Result.notFound(AdminMessages.WRONG_NAME_OR_PASSWORD);
        }
        return Result.success(JWTUtils.generateTokenForAdmin(admin), AdminMessages.LOGIN_SUCCESS);
    }

    @Override
    public Result<String> readUsers(int pageNum, int size, Sort sort) {
        Pageable pageable = PageRequest.of(pageNum, size, sort);
        Page<UserEntity> page = userRepository.findAll(pageable);
        return Result.success(gson.toJson(page.getContent()), "");
    }

    @Override
    public Result<String> readAccounts(int pageNum, int size, Sort sort) {
        Pageable pageable = PageRequest.of(pageNum, size, sort);
        Page<AccountEntity> page = accountRepository.findAll(pageable);
        return Result.success(gson.toJson(page.getContent()), "");
    }
}
