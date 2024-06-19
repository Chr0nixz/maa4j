package top.chr0nix.maa4j.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @Column(columnDefinition = "bigint unsigned comment '主键'", nullable = false)
    Long id;

    @Column(columnDefinition = "varchar(255) unique comment '账号'", nullable = false)
    String account;

    @Column(columnDefinition = "text comment '加密密码'", nullable = false)
    String password;

    @Column(columnDefinition = "bigint comment '用户id'", nullable = false)
    Long owner;

}
