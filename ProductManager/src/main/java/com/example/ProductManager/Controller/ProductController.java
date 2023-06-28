package com.example.ProductManager.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ProductManager.model.entity.Product;
import com.example.ProductManager.model.entity.TypeProduct;
import com.example.ProductManager.model.service.ProductService;

@Controller
@RequestMapping(value = "/")
public class ProductController {
    @Autowired
    ProductService productService = new ProductService();

    @GetMapping(value = "/")
    public String showlist(Model model) {
        ArrayList<Product> productlist = productService.getAllProducts();
        model.addAttribute("productlist", productlist);
        return "index";
    }

    @GetMapping(value = "/add")
    public String showAdd(Model model) {
        Product product = new Product();
        ArrayList<TypeProduct> typeProductsList = productService.getallTypeProducts();
        model.addAttribute("product", product);
        model.addAttribute("typeProductsList", typeProductsList);
        return "add";
    }

    @PostMapping(value = "add/addNew")
    public String addNewProduct(@ModelAttribute("product") Product product,
            @RequestParam("idTypeProduct") int idTypeProduct, Model model) {
        TypeProduct typeProduct = productService.getTypeProductById(idTypeProduct);
        product.setTypeProduct(typeProduct);
        productService.AddNewProduct(product);
        return showlist(model);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model) {
        productService.deleteProduct(id);
        return showlist(model);
    }

    // view and edit
    @GetMapping(value = "/view/{id}")
    public String showinforProduct(@PathVariable("id") int id, Model model) {
         Product product = productService.getProductbyId(id);
        ArrayList<TypeProduct> typeProductsList = productService.getallTypeProducts();
        model.addAttribute("product", product);
        model.addAttribute("typeProductsList", typeProductsList);
        return "view";
    }
    @PostMapping(value ="/edit")
      public String editProduct(@ModelAttribute("product") Product product,
            @RequestParam("idTypeProduct") int idTypeProduct, Model model) throws Exception {
        TypeProduct typeProduct = productService.getTypeProductById(idTypeProduct);
        product.setTypeProduct(typeProduct);
        productService.editProduct(product);
        return showinforProduct(product.getIdProduct(), model);
    }
}
