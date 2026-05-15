package cl.techstore.api.service;

import cl.techstore.api.model.Product;
import cl.techstore.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Le dice a Spring que esta clase es nuestro "Chef" (Lógica de negocio)
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // El Chef se comunica con el Refrigerador

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        // Aquí en el futuro podríamos agregar lógica, por ejemplo:
        // if (product.getPrice() < 0) throw new Error("El precio no puede ser negativo");
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Asegúrate de que este método esté AQUÍ ADENTRO, antes de la última llave
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

}