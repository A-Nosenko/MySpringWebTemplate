package template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import template.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
