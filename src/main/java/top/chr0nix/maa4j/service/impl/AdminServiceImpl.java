package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.repository.AdminRepository;
import top.chr0nix.maa4j.service.intf.AdminService;
import top.chr0nix.maa4j.utils.Result;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public Result<String> readUsers(int pageNum, int Size) {
        return null;
    }

    @Override
    public Result<String> readAccounts() {
        return null;
    }
}
