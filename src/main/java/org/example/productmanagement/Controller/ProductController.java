package org.example.productmanagement.Controller;

import org.example.productmanagement.Entity.Product;
import org.example.productmanagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    // Display all products
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product_list";
    }
    // Show form for creating a new product
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            // If the product doesn't exist, redirect to the product list page
            return "redirect:/products";
        }
        model.addAttribute("product", product); // Add the product to the model
        return "product_form"; // Render the product form template
    }
    // Handle the form submission for updating the product
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product updatedProduct) {
        // If the product doesn't exist, redirect to the product list page
        return "redirect:/products"; // Redirect to the product list page after a successful update
    }
    // Save a new or updated product
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
    // Delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}