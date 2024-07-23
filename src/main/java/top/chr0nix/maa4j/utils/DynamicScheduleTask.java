package top.chr0nix.maa4j.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import top.chr0nix.maa4j.service.intf.DeviceService;
import top.chr0nix.maa4j.service.intf.ScheduleService;

@Component
public class DynamicScheduleTask implements SchedulingConfigurer {

    private final ScheduleService scheduleService;
    private final DeviceService deviceService;

    @Autowired
    public DynamicScheduleTask(ScheduleService scheduleService,
                               DeviceService deviceService) {
        this.scheduleService = scheduleService;
        this.deviceService = deviceService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //队列巡检
        taskRegistrar.addTriggerTask(
                scheduleService::queueInspect,
                triggerContext -> new CronTrigger("0 */1 * * * *").nextExecution(triggerContext)
        );

        //理智刷新
        taskRegistrar.addTriggerTask(
                scheduleService::sanRefresh,
                triggerContext -> new CronTrigger("0 */6 * * * *").nextExecution(triggerContext)
        );

        //设备监控
        taskRegistrar.addTriggerTask(
                deviceService::monitorDevice,
                triggerContext -> new CronTrigger("0/5 * * * * ?").nextExecution(triggerContext)
        );

        //队列升级
        taskRegistrar.addTriggerTask(
                scheduleService::queuePromote,
                triggerContext -> new CronTrigger("5/5 * * * * ?").nextExecution(triggerContext)
        );

    }
}
