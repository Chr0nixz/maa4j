package top.chr0nix.maa4j.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    @Column(columnDefinition = "bigint unsigned auto_increment comment '主键'", nullable = false)
    Long id;



}
