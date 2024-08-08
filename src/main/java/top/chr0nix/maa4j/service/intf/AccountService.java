package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.dto.AccountConfigDTO;
import top.chr0nix.maa4j.dto.AddAccountDTO;
import top.chr0nix.maa4j.dto.UpdateAccountConfigDTO;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.exception.account.AccountNotFoundException;
import top.chr0nix.maa4j.utils.Result;

public interface AccountService {

    Result<String> addAccount(AddAccountDTO addAccountDTO, Long ownerId);

    Result<String> deleteAccount(String account);

    Long getIdByAccount(String account) throws AccountNotFoundException;

    Result<String> updateConfig(AccountConfigDTO accountConfigDTO, String account);

    Result<String> updateConfig(UpdateAccountConfigDTO updateAccountConfigDTO, String account);

    Result<String> getConfig(String account);

    String getPassword(AccountEntity accountEntity) throws Exception;

    void gameLogin(String account);

    Result<String> startAccount(String account);

}
