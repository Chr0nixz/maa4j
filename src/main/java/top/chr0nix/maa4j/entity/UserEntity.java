package top.chr0nix.maa4j.entity;

import jakarta.persistence.*;
import lombok.Data;
import top.chr0nix.maa4j.entity.converter.JsonConverter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(columnDefinition = "bigint unsigned comment '主键'", nullable = false)
    Long id;

    @Column(columnDefinition = "varchar(255) unique comment '用户名'", nullable = false)
    String name;

    @Column(columnDefinition = "text comment '密码'", nullable = false)
    String password;

    @Column(columnDefinition = "text comment '账号'")
    @Convert(converter = JsonConverter.class)
    List<String> accounts;

    @Column(columnDefinition = "text comment '秘钥'")
    String game_key;

    @Column(columnDefinition = "boolean default false comment '是否封禁'")
    boolean banned;

    @Column(columnDefinition = "datetime comment '注册时间'", nullable = false)
    LocalDateTime register_time;

    @Column(columnDefinition = "datetime comment '过期时间'")
    LocalDateTime expire_time;

    @Column(columnDefinition = "datetime comment '上次登录时间'")
    LocalDateTime last_login;

}
