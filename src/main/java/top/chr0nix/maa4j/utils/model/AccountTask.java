package top.chr0nix.maa4j.utils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;
import top.chr0nix.maa4j.maa.MaaTasks.StartUpTask;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTask {

    String account;

    String password;

    @Builder.Default
    ArrayList<MaaTask> tasks = new ArrayList<>();

    public AccountTask init() {
        tasks.add(StartUpTask.builder().account_name(account).build());
        return this;
    }

}
