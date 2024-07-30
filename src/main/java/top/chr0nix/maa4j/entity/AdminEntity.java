package top.chr0nix.maa4j.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.entity.converter.AuthorityHashMapConverter;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    @Column(columnDefinition = "bigint unsigned comment '主键'", nullable = false)
    Long id;

    @Column(columnDefinition = "varchar(255) unique comment '用户名'", nullable = false)
    String name;

    @Column(columnDefinition = "text comment '密码'", nullable = false)
    String password;

    @Column(columnDefinition = "text comment '权限'", nullable = false)
    @Convert(converter = AuthorityHashMapConverter.class)
    HashMap<String, Boolean> authority;

}
