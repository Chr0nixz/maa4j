package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.utils.Result;

public interface AccountService {

    Result<String> addAccount(AddAccountDTO addAccountDTO, Long ownerId);

    Result<String> updateConfig(AccountConfigDTO accountConfigDTO, Long ownerId);

}
