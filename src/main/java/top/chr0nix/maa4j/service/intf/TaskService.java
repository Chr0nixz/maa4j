package top.chr0nix.maa4j.service.intf;

import top.chr0nix.maa4j.utils.model.AccountTask;

import java.util.HashMap;

public interface TaskService {

    void calculateSan();

    AccountTask getAccountTask(Long accountId) throws Exception;

}
