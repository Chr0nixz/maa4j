package top.chr0nix.maa4j.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.service.intf.TaskService;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TaskServiceImpl implements TaskService {

    private AccountRepository accountRepo;
    private ConcurrentLinkedQueue<String> queue;

    @Autowired
    public void setAccountRepo(AccountRepository repo) {
        this.accountRepo = repo;
    }

    @Override
    public boolean init() {
        List<AccountEntity> accounts = accountRepo.findAccountEntitiesByRunningEqualsOrderByPriority(true);
        for (AccountEntity account : accounts) {
            this.queue.add(account.getAccount());
        }
        return true;
    }

}
