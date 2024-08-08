package top.chr0nix.maa4j.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.chr0nix.maa4j.maa.MaaCore;
import top.chr0nix.maa4j.service.intf.AsyncService;
import top.chr0nix.maa4j.utils.model.Maa4jProperties;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    private final String executor = "taskExecutor";

    private final Maa4jProperties maa4jProperties;

    @Autowired
    public AsyncServiceImpl(Maa4jProperties maa4jProperties) {
        this.maa4jProperties = maa4jProperties;
    }

    @Override
    @Async(executor)
    public void maaLoadResource(MaaCore maaCore) {
        if (maaCore.AsstLoadResource(maa4jProperties.getMaa_path())) {
            log.info("Maa加载成功！");
        }
    }
}
