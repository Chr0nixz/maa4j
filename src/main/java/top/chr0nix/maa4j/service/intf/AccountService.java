package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.utils.Result;

public interface AccountService {

    public Result<String> addAccount(AddAccountDTO accountDTO, Long ownerId) ;

}
