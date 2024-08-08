package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.entity.taskConfig.AccountConfig;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.service.intf.AccountService;
import top.chr0nix.maa4j.service.intf.TaskService;
import top.chr0nix.maa4j.utils.DynamicInfo;
import top.chr0nix.maa4j.utils.model.AccountTask;
import top.chr0nix.maa4j.utils.model.UserSan;

import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    private AccountRepository accountRepo;
    private AccountService accountService;
    private DynamicInfo dynamicInfo;

    @Autowired
    public void setAccountRepo(AccountRepository repo) {
        this.accountRepo = repo;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public  void setDynamicInfo(DynamicInfo dynamicInfo) {
        this.dynamicInfo = dynamicInfo;
    }

    @Override
    public void calculateSan() {
        var entrySet = dynamicInfo.getAccountSanInfoMap().entrySet();
        for (Map.Entry<Long, UserSan> entry : entrySet) {
            AccountEntity accountEntity = accountRepo.findFirstById(entry.getKey());
            if (accountEntity == null) {
                continue;
            }
            entry.getValue().addSan(1);

        }
    }

    @Override
    public AccountTask getAccountTask(Long accountId) throws Exception {
        AccountEntity accountEntity = accountRepo.findFirstById(accountId);
        AccountTask accountTask = AccountTask.builder().account(accountEntity.getAccount())
                .password(accountService.getPassword(accountEntity)).build().init();
        AccountConfig accountConfig = accountEntity.getConfig();
        if (accountConfig.isEnableRecruit()) {
            accountTask.getTasks().add(accountConfig.getRecruitConfig().generateTask().getFirst());
        }
        if (accountConfig.isEnableInfrast()) {
            accountTask.getTasks().add(accountConfig.getInfrastConfig().generateTask().getFirst());
        }
        if (accountConfig.isEnableFight()) {
            accountTask.getTasks().addAll(accountConfig.getFightConfig().generateTask());
        }
        return accountTask;
    }

}
