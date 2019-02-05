package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import template.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    @Modifying
    @Query("UPDATE UserRole a SET a.roleId = 3 WHERE a.userId = :id")
    void ban(@Param("id") int id);

    @Modifying
    @Query("UPDATE UserRole a SET a.roleId = 2 WHERE a.userId = :id")
    void unBan(@Param("id") int id);
}
