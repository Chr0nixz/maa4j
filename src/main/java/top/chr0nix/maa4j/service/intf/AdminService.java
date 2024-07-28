package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.utils.Result;

import java.util.List;

public interface AdminService {

    public Result<String> readUsers(int pageNum, int Size);

    public Result<String> readAccounts();

}
