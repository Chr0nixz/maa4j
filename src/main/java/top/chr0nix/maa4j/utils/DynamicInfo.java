package top.chr0nix.maa4j.utils;

import lombok.Data;
import org.springframework.stereotype.Component;
import top.chr0nix.maa4j.entity.AccountEntity;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@Data
public class DynamicInfo {

    HashMap<String, Integer> deviceStatusMap = new HashMap<>();

    ArrayList<AccountEntity> freeTaskList = new ArrayList<>();

    HashMap<Long, Integer> userSanMap = new HashMap<>();

    HashMap<Long, Integer> userMaxSanMap = new HashMap<>();

}
