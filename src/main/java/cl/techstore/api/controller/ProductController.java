package cl.techstore.api.controller;

import cl.techstore.api.model.Product;
import cl.techstore.api.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService; // El Mesero ahora habla con el Chef (Servicio)

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

// En el método POST
    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

// En el método PUT
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    // 6. BUSCAR POR NOMBRE (Nuevo)
    // Usamos @RequestParam para que la URL sea algo como: /api/products/search?name=teclado
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchProductsByName(name);
    }
}