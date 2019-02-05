package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import template.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
