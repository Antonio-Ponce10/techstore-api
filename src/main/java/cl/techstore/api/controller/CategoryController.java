package cl.techstore.api.controller;

import cl.techstore.api.model.Category;
import cl.techstore.api.service.CategoryService;
// 1. IMPORTAMOS EL SERVICIO DE SQS Y LA SEGURIDAD
import cl.techstore.api.service.SqsAuditService; 
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 2. INYECTAMOS EL SERVICIO DE AUDITORÍA SQS
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
            // Intentamos obtener el correo del usuario logueado con JWT
            String usuarioLogueado = SecurityContextHolder.getContext().getAuthentication().getName();
            
            // 3. ENVIAMOS LA AUDITORÍA A AWS SQS
            // Asegúrate de que los métodos getId() y getName() correspondan a tu modelo Category
            sqsAuditService.enviarAuditoria(
                "CREAR", 
                nuevaCategoria.getId(), 
                nuevaCategoria.getName(), // O getNombre() si lo tienes en español en tu modelo
                usuarioLogueado
            );
        } catch (Exception e) {
            System.err.println("Aviso: No se pudo enviar la auditoría a SQS o no hay token JWT válido.");
        }

        return nuevaCategoria;
    }
}