package top.chr0nix.maa4j.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.chr0nix.maa4j.entity.converter.LongListConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Accessors(chain = true)
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

    @Column(columnDefinition = "game_key text comment '秘钥'")
    String gameKey;

    @Column(columnDefinition = "boolean default false comment '是否封禁'")
    boolean banned;

    @Column(columnDefinition = "register_time datetime comment '注册时间'", nullable = false)
    LocalDateTime registerTime;

    @Column(columnDefinition = "expire_time datetime comment '过期时间'")
    LocalDateTime expireTime;

    @Column(columnDefinition = "last_login datetime comment '上次登录时间'")
    LocalDateTime lastLogin;

}
