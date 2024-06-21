package top.chr0nix.maa4j.entity.converter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AdminEntity {

    @Id
    @Column(columnDefinition = "bigint unsigned auto_increment comment '主键'", nullable = false)
    Long id;



}
