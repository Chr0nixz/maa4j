package top.chr0nix.maa4j.utils;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.chr0nix.maa4j.entity.AccountEntity;
import top.chr0nix.maa4j.repository.AccountRepository;
import top.chr0nix.maa4j.utils.model.MemoryInfo;
import top.chr0nix.maa4j.utils.model.UserSan;

import java.util.ArrayList;
import java.util.LinkedList;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicInfo extends MemoryInfo {

    AccountRepository accountRepo;

    @Autowired
    public void setAccountRepo(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void load(MemoryInfo memoryInfo) {
        this.setDeviceStatusMap(memoryInfo.getDeviceStatusMap())
                .setAccountSanInfoMap(memoryInfo.getAccountSanInfoMap())
                .setPreAccountQueue(memoryInfo.getPreAccountQueue())
                .setWorkAccountList(memoryInfo.getWorkAccountList())
                .setWaitAccountQueue(memoryInfo.getWaitAccountQueue());
    }

    public MemoryInfo dump() {
        return MemoryInfo.builder()
                .waitAccountQueue(getWaitAccountQueue())
                .deviceStatusMap(getDeviceStatusMap())
                .accountSanInfoMap(getAccountSanInfoMap())
                .preAccountQueue(getPreAccountQueue())
                .workAccountList(getWorkAccountList())
                .build();
    }

    public ArrayList<AccountEntity> getAllWaitAccountInfo() {
        LinkedList<Long> queue = new LinkedList<>(getWaitAccountQueue());
        ArrayList<AccountEntity> list = new ArrayList<>();
        if (queue.isEmpty()) {
            return new ArrayList<>();
        }
        for (Long accountId : queue) {
            list.add(accountRepo.findFirstById(accountId));
        }
        return list;
    }

    public void setAccountSanZero(Long accountId) {
        if (getAccountSanInfoMap().containsKey(accountId)) {
            getAccountSanInfoMap().get(accountId).setCurSan(0);
        } else {
            getAccountSanInfoMap().put(accountId, new UserSan(0, 135));
        }
    }

    public void addAccountSan(Long accountId, int san) {
        if (getAccountSanInfoMap().containsKey(accountId)) {
            getAccountSanInfoMap().get(accountId).setCurSan(getAccountSanInfoMap().get(accountId).getCurSan() + san);
        }
    }

}
