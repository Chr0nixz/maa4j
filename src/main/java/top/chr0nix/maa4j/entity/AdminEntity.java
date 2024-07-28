package top.chr0nix.maa4j.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chr0nix.maa4j.entity.converter.AdminAuthorityConverter;
import top.chr0nix.maa4j.utils.model.AdminAuthority;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    @Column(columnDefinition = "bigint unsigned auto_increment comment '主键'", nullable = false)
    Long id;

    @Column(columnDefinition = "varchar(255) unique comment '用户名'", nullable = false)
    String name;

    @Column(columnDefinition = "text comment '密码'", nullable = false)
    String password;

    @Column(columnDefinition = "text comment '权限'", nullable = false)
    @Convert(converter = AdminAuthorityConverter.class)
    AdminAuthority authority;

}
