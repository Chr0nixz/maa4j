package top.chr0nix.maa4j.utils.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryInfo {

    ConcurrentLinkedQueue<Long> waitAccountQueue = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Long> preAccountQueue = new ConcurrentLinkedQueue<>();
    List<Long> workAccountList = Collections.synchronizedList(new ArrayList<>());

    HashMap<Long, UserSan> userSanInfoMap = new HashMap<>();

    HashMap<String, Integer> deviceStatusMap = new HashMap<>();

}
