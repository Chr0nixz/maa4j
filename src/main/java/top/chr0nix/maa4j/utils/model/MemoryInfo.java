package top.chr0nix.maa4j.utils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemoryInfo {

    @Builder.Default
    ConcurrentLinkedQueue<Long> waitAccountQueue = new ConcurrentLinkedQueue<>();
    @Builder.Default
    ConcurrentLinkedQueue<AccountTask> preAccountQueue = new ConcurrentLinkedQueue<>();
    @Builder.Default
    List<String> workAccountList = Collections.synchronizedList(new ArrayList<>());

    @Builder.Default
    HashMap<Long, UserSan> accountSanInfoMap = new HashMap<>();

    @Builder.Default
    HashMap<String, Integer> deviceStatusMap = new HashMap<>();

}
