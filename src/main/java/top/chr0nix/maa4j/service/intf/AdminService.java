package top.chr0nix.maa4j.service.intf;

import org.springframework.data.domain.Sort;
import top.chr0nix.maa4j.utils.Result;

import java.util.List;

public interface AdminService {

    public Result<String> loginAdmin(String name, String password);

    public Result<String> readUsers(int pageNum, int size, Sort sort);

    public Result<String> readAccounts(int pageNum, int size, Sort sort);

}
