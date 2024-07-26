package top.chr0nix.maa4j.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.service.intf.DeviceService;
import top.chr0nix.maa4j.service.intf.MaaService;
import top.chr0nix.maa4j.service.intf.ScheduleService;
import top.chr0nix.maa4j.service.intf.TaskService;
import top.chr0nix.maa4j.utils.DynamicInfo;
import top.chr0nix.maa4j.utils.model.AccountTask;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final DynamicInfo dynamicInfo;
    private final TaskService taskService;
    private final AccountRepository accountRepo;
    private final DeviceService deviceService;
    private final MaaService maaService;

    @Autowired
    public ScheduleServiceImpl(DynamicInfo dynamicInfo,
                               TaskService taskService,
                               AccountRepository accountRepo,
                               DeviceService deviceService,
                               MaaService maaService) {
        this.dynamicInfo = dynamicInfo;
        this.taskService = taskService;
        this.accountRepo = accountRepo;
        this.deviceService = deviceService;
        this.maaService = maaService;
    }

    @Override
    public void queueInspect() {
        LinkedHashSet<Long> waitSet = new LinkedHashSet<>(dynamicInfo.getWaitAccountQueue());
        ConcurrentLinkedQueue<Long> waitQueue = new ConcurrentLinkedQueue<>(waitSet);
        LinkedHashSet<AccountTask> preSet = new LinkedHashSet<>(dynamicInfo.getPreAccountQueue());
        ConcurrentLinkedQueue<AccountTask> preQueue = new ConcurrentLinkedQueue<>(preSet);
        List<AccountEntity> accounts = accountRepo.findAccountEntitiesByRunningEqualsOrderByPriority(true);
        LocalDateTime now = LocalDateTime.now();
        for (AccountEntity account : accounts) {
            if (dynamicInfo.getWorkAccountList().contains(account.getAccount())) {
                continue;
            }
            Duration duration = Duration.between(account.getLastLogin(), now);
            if (duration.toHours() < 3) {
                continue;
            }
            waitQueue.add(account.getId());
            dynamicInfo.setAccountSanZero(account.getId());
        }
        dynamicInfo.setWaitAccountQueue(waitQueue);
        dynamicInfo.setPreAccountQueue(preQueue);
        System.out.println("巡检");
    }

    @Override
    public void sanRefresh() {
        taskService.calculateSan();
    }

    @SneakyThrows
    @Override
    public void queuePromote(){
        ConcurrentLinkedQueue<Long> waitQueue = dynamicInfo.getWaitAccountQueue();
        ConcurrentLinkedQueue<AccountTask> preQueue = dynamicInfo.getPreAccountQueue();
        int preCount = dynamicInfo.getPreAccountQueue().size();
        int deviceCount = deviceService.getDeviceCountFree();
        if (preCount < deviceCount) {
            for (int i = 0 ; i < deviceCount - preCount ; i++) {
                if (!waitQueue.isEmpty()) {
                    preQueue.add(taskService.getAccountTask(waitQueue.poll()));
                }
            }
        }
        if (!dynamicInfo.getPreAccountQueue().isEmpty()){
            if (maaService.startTask(dynamicInfo.getPreAccountQueue().peek())){
                dynamicInfo.getWorkAccountList().add(Objects.requireNonNull(dynamicInfo.getPreAccountQueue().poll()).getAccount());
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(dynamicInfo.dump()));
    }

}
