package io.github.lemniscatex.webapp.controller;

import io.github.lemniscatex.webapp.model.Product;
import io.github.lemniscatex.webapp.repository.ProductRepository;
import io.github.lemniscatex.webapp.util.FileOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        Iterable<Product> ps = productRepository.findAll();
        System.out.println(ps);
        model.addAttribute("products", ps);
        return "products";
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestParam(name = "name") String name,
                             @RequestParam(name = "price") Double price,
                             @RequestParam(name = "quantity") Integer quantity,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "category") String category,
                             @RequestParam(name = "files") MultipartFile[] files) {
        System.out.println(111);
        Product product = new Product(name, price, quantity, description, category);
        productRepository.save(product);
        FileOperation.storeFile("products", product.id, files);
        product.photosPath = "products/" + product.id.toString() + "/";
        productRepository.save(product);
        return "redirect:/products";
    }

    @PostMapping("/products/sub")
    public String subProduct(@RequestParam(name = "name") String name) {
        System.out.println(name);
        return "redirect:/products";
    }

    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam(name = "id") Integer id) {
        FileOperation.deleteFile("products", id);
        productRepository.deleteByIdEquals(id);
        return "redirect:/products";
    }

    @PostMapping("/products/modify")
    public String modifyProduct(@RequestParam(name = "id") Integer id,
                                @RequestParam(name = "modifiedName") String modifiedName,
                                @RequestParam(name = "modifiedPrice") Double modifiedPrice,
                                @RequestParam(name = "modifiedQuantity") Integer modifiedQuantity,
                                @RequestParam(name = "modifiedDescription") String modifiedDescription,
                                @RequestParam(name = "modifiedCategory") String modifiedCategory,
                                @RequestParam(name = "modifiedFiles", required = false) MultipartFile[] modifiedFiles) {
        Product product = productRepository.getProductById(id);
        product.setName(modifiedName);
        product.setPrice(modifiedPrice);
        product.setQuantity(modifiedQuantity);
        product.setDescription(modifiedDescription);
        product.setCategory(modifiedCategory);
        productRepository.save(product);
        FileOperation.storeFile("products", product.id, modifiedFiles);
        return "redirect:/products";
    }
}
