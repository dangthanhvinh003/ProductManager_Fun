package com.example.ProductManager.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductManager.model.entity.Product;
import com.example.ProductManager.model.entity.TypeProduct;
import com.example.ProductManager.model.repo.ProductRepo;
import com.example.ProductManager.model.repo.TypeProductRepo;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo = new ProductRepo();
    TypeProductRepo typeProductRepo = new TypeProductRepo();

    public ArrayList<Product> getAllProducts() {
        try {
            return productRepo.getAllProducts();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Product getProductbyId(int idProduct) {
        try {
            return productRepo.getProductById(idProduct);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void AddNewProduct(Product product) {
        try {
            productRepo.AddProduct(product);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteProduct(int idProduct) {
        try {
            productRepo.deleteProduct(idProduct);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void editProduct(Product product) throws Exception {
            productRepo.editProduct(product);
    }
    
    public ArrayList<TypeProduct> getallTypeProducts() {
        try {
            return typeProductRepo.getallTypeProduct();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public TypeProduct getTypeProductById(int idTypeProduct){
        try {
            return typeProductRepo.getTypeProductById(idTypeProduct);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
