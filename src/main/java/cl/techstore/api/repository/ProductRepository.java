package cl.techstore.api.repository;

import cl.techstore.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Busca productos que contengan un texto en su nombre, ignorando mayúsculas y minúsculas
    List<Product> findByNameContainingIgnoreCase(String name);
    
}