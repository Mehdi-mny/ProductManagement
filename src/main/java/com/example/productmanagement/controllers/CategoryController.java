package com.example.productmanagement.controllers;

import com.example.productmanagement.Entities.Category;
import com.example.productmanagement.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
    public String createCategory() {
        return "createCategory"; // Nom de la vue du formulaire
    }
    @RequestMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("categoryVue") Category categoryController, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage", "Category saved successfully!");
        CategoryService.saveCategory(categoryController);
        return "createCategory";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(@ModelAttribute("productId") Long categoryId, RedirectAttributes redirectAttributes) {
        CategoryService.deleteCategory(categoryId);
        redirectAttributes.addFlashAttribute("categoryDeleteMessage", "Category deleted successfully!");
        return "redirect:listCategory";// redirection vers l' url de listcategory
    }

    @RequestMapping("/listCategory")
    public String listProduct(ModelMap modelMap){
        List<Category> categoryList = CategoryService.findAllCategories();
        modelMap.addAttribute("categoryvue", categoryList);
        return "listCategory";//nom de la vue
    }
}
