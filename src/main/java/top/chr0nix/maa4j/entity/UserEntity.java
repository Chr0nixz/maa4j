package top.chr0nix.maa4j.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.entity.converter.LongListConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Convert(converter = LongListConverter.class)
    ArrayList<Long> accounts;

    @Column(name = "game_key", columnDefinition = "text comment '账号秘钥'")
    String gameKey;

    @Column(name = "login_key", columnDefinition = "text comment '登录秘钥'")
    String loginKey;

    @Column(columnDefinition = "boolean default false comment '是否封禁'")
    boolean banned;

    @Column(name = "register_time", columnDefinition = "datetime comment '注册时间'", nullable = false)
    LocalDateTime registerTime;

    @Column(name = "expire_time", columnDefinition = "datetime comment '过期时间'")
    LocalDateTime expireTime;

    @Column(name = "last_login", columnDefinition = "datetime comment '上次登录时间'")
    LocalDateTime lastLogin;

}
