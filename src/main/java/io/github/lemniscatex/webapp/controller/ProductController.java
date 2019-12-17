package io.github.lemniscatex.webapp.controller;

import io.github.lemniscatex.webapp.model.Product;
import io.github.lemniscatex.webapp.repository.ProductRepository;
import io.github.lemniscatex.webapp.service.FileOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin-products";
    }

    @PostMapping("/admin/products/add")
    public String addProduct(@RequestParam(name = "name") String name,
                             @RequestParam(name = "price") Double price,
                             @RequestParam(name = "quantity") Integer quantity,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "category") String category,
                             @RequestParam(name = "file") MultipartFile file) {
        Product product = new Product(name, price, quantity, description, category);
        productRepository.save(product);
        product.photosPath = FileOperation.storeFile("products", product.id, file);
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/products/delete")
    public String deleteProduct(@RequestParam(name = "id") Integer id) {
        Product p = productRepository.getProductById(id);
        Path parent = Paths.get("/public", p.photosPath).getParent();
        FileSystemUtils.deleteRecursively(parent.toFile());

        productRepository.deleteByIdEquals(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/products/modify")
    public String modifyProduct(@RequestParam(name = "id") Integer id,
                                @RequestParam(name = "modifiedName") String modifiedName,
                                @RequestParam(name = "modifiedPrice") Double modifiedPrice,
                                @RequestParam(name = "modifiedQuantity") Integer modifiedQuantity,
                                @RequestParam(name = "modifiedDescription") String modifiedDescription,
                                @RequestParam(name = "modifiedCategory") String modifiedCategory) {
        Product product = productRepository.getProductById(id);
        product.name = modifiedName;
        product.price = modifiedPrice;
        product.quantity = modifiedQuantity;
        product.description = modifiedDescription;
        product.category = modifiedCategory;
        productRepository.save(product);
        return "redirect:/admin/products";
    }
}
