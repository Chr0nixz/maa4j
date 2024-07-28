package top.chr0nix.maa4j.utils.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
public class AdminAuthority {

    boolean canReadUsers;

    boolean canDeleteUsers;

    boolean canReadAccounts;

    boolean canDeleteAccounts;

}
