package cl.techstore.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // No olvides agregar su Getter y Setter
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // El nombre no puede estar vacío ni ser solo espacios
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String name;
    
    // El precio no puede ser nulo y mínimo debe ser 0
    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double price;

    public Product() {
    }

    // --- Los Getters y Setters se mantienen exactamente igual ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}