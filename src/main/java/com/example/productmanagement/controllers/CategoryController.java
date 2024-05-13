package com.example.productmanagement.controllers;

import com.example.productmanagement.Entities.Category;
import com.example.productmanagement.Entities.Product;
import com.example.productmanagement.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class CategoryController {
    @Qualifier("defaultCategoryService")
    private CategoryService CategoryService;
    @RequestMapping("/createCategory")
    public String createCategory(Model model) {
        model.addAttribute("categoryVue", new Category());
        return "createCategory"; // Nom de la vue du formulaire
    }
    @RequestMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("categoryVue") Category categoryController, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage", "Category saved successfully!");
        // Save the product again to update the QR code
        CategoryService.saveCategory(categoryController);
        return "redirect:createCategory";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("productId") Long categoryId, RedirectAttributes redirectAttributes) {
        // Call your CategoryService method to delete the product by ID
        CategoryService.deleteCategory(categoryId);
        redirectAttributes.addFlashAttribute("categoryDeleteMessage", "Category deleted successfully!");
        // Redirect to the category list page after deletion
        return "redirect:/listCategory";
    }

    @RequestMapping("/listCategory")
    public String listProduct(ModelMap modelMap){
        List<Category> categoryList = CategoryService.findAllCategories();
        modelMap.addAttribute("categoryvue", categoryList);
        return "listCategory";
    }
}
