package top.chr0nix.maa4j.service.intf;

import org.springframework.data.domain.Sort;
import top.chr0nix.maa4j.utils.Result;

public interface AdminService {

    Result<String> loginAdmin(String name, String password);

    Result<String> readUsers(int pageNum, int size, Sort sort);

    Result<String> readAccounts(int pageNum, int size, Sort sort);

    Result<String> readDevices();

}
