package top.chr0nix.maa4j.entity;

import jakarta.persistence.*;
import lombok.Data;
import top.chr0nix.maa4j.entity.converter.JsonConverter;

import java.util.List;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "bigint unsigned auto_increment comment '主键'", nullable = false)
    Long id;

    @Column(columnDefinition = "varchar(255) unique comment '用户名'", nullable = false)
    String name;

    @Column(columnDefinition = "text comment '密码'", nullable = false)
    String password;

    @Column(columnDefinition = "text comment '账号'")
    @Convert(converter = JsonConverter.class)
    List<String> accounts;

}
