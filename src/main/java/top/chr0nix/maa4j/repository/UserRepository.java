package top.chr0nix.maa4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.chr0nix.maa4j.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findFirstByNameAndPassword(String name, String password);

    UserEntity findFirstById(Long userId);
}
