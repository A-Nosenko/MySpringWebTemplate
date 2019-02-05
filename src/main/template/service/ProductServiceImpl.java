package template.service;

import template.model.Product;
import template.repository.ProductRepository;
import template.service.contract.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public int addProduct(Product product) {
        return productRepository.save(product).getId();
    }

    @Override
    public void removeProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProduct(int id) {
        return productRepository.getOne(id);
    }
}
