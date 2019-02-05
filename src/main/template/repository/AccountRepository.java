package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import template.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Modifying
    @Query("UPDATE Account a SET a.currentMoney = :theSum WHERE a.userId = :userId")
    void setCurrentMoney(@Param("userId") int userId,
                         @Param("theSum") int sum);

    Account findAccountByUserId(int userId);
}
