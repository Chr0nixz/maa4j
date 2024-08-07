package top.chr0nix.maa4j.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.entity.taskConfig.AccountConfig;
import top.chr0nix.maa4j.entity.converter.AccountConfigConverter;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(columnDefinition = "boolean default true comment '是否运行'")
    boolean running;

    @Column(columnDefinition = "boolean default false comment '是否封禁'")
    boolean banned;

    @Column(columnDefinition = "text comment '设置'")
    @Convert(converter = AccountConfigConverter.class)
    AccountConfig config;

    @Column(columnDefinition = "longtext comment '日志'")
    String log;

    @Column(columnDefinition = "tinyint unsigned default '1' comment '优先级'", nullable = false)
    int priority;

    @Column(name = "last_login", columnDefinition = "datetime comment '上次登录时间'")
    LocalDateTime lastLogin;

}
