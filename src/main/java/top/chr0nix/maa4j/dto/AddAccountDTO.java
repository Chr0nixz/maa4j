package top.chr0nix.maa4j.dto;

import lombok.Data;

@Data
public class AddAccountDTO {

    private String account;

    private String password;

    private Long owner;

}
