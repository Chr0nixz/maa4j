package top.chr0nix.maa4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.chr0nix.maa4j.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    public AccountEntity findFirstByAccount(String account);

}
