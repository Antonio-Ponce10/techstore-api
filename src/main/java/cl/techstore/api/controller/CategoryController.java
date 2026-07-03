package cl.techstore.api.controller;

import cl.techstore.api.model.Category;
import cl.techstore.api.service.CategoryService;
import cl.techstore.api.service.SqsAuditService; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SqsAuditService sqsAuditService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        // Primero guardamos la categoría en la base de datos
        Category nuevaCategoria = categoryService.createCategory(category);
        
        try {
            // Como no hay JWT, usamos un usuario por defecto para la auditoría
            String usuarioLogueado = "admin@techstore.cl"; 
            
            sqsAuditService.enviarAuditoria(
                "CREAR", 
                nuevaCategoria.getId(), 
                nuevaCategoria.getName(), 
                usuarioLogueado
            );
        } catch (Exception e) {
            System.err.println("Aviso: No se pudo enviar la auditoría a SQS");
        }

        return nuevaCategoria;
    }
}