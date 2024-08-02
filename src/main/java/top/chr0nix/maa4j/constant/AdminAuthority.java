package top.chr0nix.maa4j.constant;

import java.util.Arrays;
import java.util.List;

public interface AdminAuthority {

    String SUPER = "super";

    String READ_USERS = "canReadUsers";

    String READ_ACCOUNTS = "canReadAccounts";

    String READ_DEVICES = "canReadDevices";

    List<String> ALL = Arrays.asList(
            READ_USERS,
            READ_ACCOUNTS,
            READ_DEVICES
    );

}
