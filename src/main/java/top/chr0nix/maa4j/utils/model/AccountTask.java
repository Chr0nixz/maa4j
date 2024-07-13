package top.chr0nix.maa4j.utils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.maa.MaaTasks.MaaTask;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTask {

    String account;

    String password;

    ArrayList<MaaTask> tasks = new ArrayList<>();

}
