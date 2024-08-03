package top.chr0nix.maa4j.entity.taskConfig;

import top.chr0nix.maa4j.maa.MaaTasks.AwardTask;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;

import java.util.Collections;
import java.util.List;

public class AwardConfig extends TaskConfig {

    boolean award;

    boolean mail;

    boolean recruit;

    boolean orundum;

    boolean mining;

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public List<MaaTask> getTask() {
        return Collections.singletonList(AwardTask.builder().award(award).mail(mail).recruit(recruit)
                .orundum(orundum).mining(mining).build());
    }
}
