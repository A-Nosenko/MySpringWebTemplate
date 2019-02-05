package template.service.contract;

import org.springframework.stereotype.Service;
import template.model.Product;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAll();

    int addProduct(Product product);

    void removeProduct(int id);

    Product getProduct(int id);
}
