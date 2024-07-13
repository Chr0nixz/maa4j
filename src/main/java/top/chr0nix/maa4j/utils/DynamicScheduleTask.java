package top.chr0nix.maa4j.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import top.chr0nix.maa4j.service.intf.TaskService;
import top.chr0nix.maa4j.utils.model.AccountTask;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class DynamicScheduleTask implements SchedulingConfigurer {

    private DynamicInfo dynamicInfo;
    private TaskService taskService;

    @Autowired
    public void setDynamicInfo(DynamicInfo dynamicInfo) {
        this.dynamicInfo = dynamicInfo;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //队列巡检
        taskRegistrar.addTriggerTask(
                () -> {
                    LinkedHashSet<Long> waitSet = new LinkedHashSet<>(dynamicInfo.getWaitAccountQueue());
                    ConcurrentLinkedQueue<Long> waitQueue = new ConcurrentLinkedQueue<>(waitSet);
                    LinkedHashSet<AccountTask> preSet = new LinkedHashSet<>(dynamicInfo.getPreAccountQueue());
                    ConcurrentLinkedQueue<AccountTask> preQueue = new ConcurrentLinkedQueue<>(preSet);
                    dynamicInfo.setPreAccountQueue(new ConcurrentLinkedQueue<>(preSet));
                    int preCount = dynamicInfo.getPreAccountQueue().size();
                    int deviceCount = dynamicInfo.getDeviceStatusMap().size();
                    if (preCount < deviceCount) {
                        for (int i = 0 ; i < deviceCount - preCount ; i++) {
                            try {
                                preQueue.add(taskService.getAccountTask(waitQueue.poll()));
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }
                    dynamicInfo.setWaitAccountQueue(waitQueue);
                    dynamicInfo.setPreAccountQueue(preQueue);
                    System.out.println("巡检");
                },
                triggerContext -> new CronTrigger("0 */1 * * * *").nextExecution(triggerContext)
        );

        //理智刷新
        taskRegistrar.addTriggerTask(
                () -> taskService.calculateSan(),
                triggerContext -> new CronTrigger("0 */6 * * * *").nextExecution(triggerContext)
        );

    }
}
