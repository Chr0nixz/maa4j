package top.chr0nix.maa4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.chr0nix.maa4j.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findFirstByNameAndPassword(String name, String password);

}
