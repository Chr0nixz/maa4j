package top.chr0nix.maa4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.chr0nix.maa4j.entity.AccountEntity;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findFirstByAccount(String account);

    List<AccountEntity> findAccountEntitiesByRunningEqualsOrderByPriority(boolean running);

    AccountEntity findFirstById(Long accountId);

    void deleteByAccount(String account);

}
