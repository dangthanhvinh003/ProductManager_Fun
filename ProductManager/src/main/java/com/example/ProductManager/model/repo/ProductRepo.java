package com.example.ProductManager.model.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ProductManager.model.entity.Product;
import com.example.ProductManager.model.entity.TypeProduct;


@Repository
public class ProductRepo {
    @Autowired
    TypeProductRepo typeProductRepo = new TypeProductRepo();
    
    public ArrayList<Product> getAllProducts() throws Exception{
        ArrayList<Product> productsList = new ArrayList<>();
        Class.forName(Baseconection.nameClass);
        Connection con = DriverManager.getConnection(Baseconection.url, Baseconection.username, Baseconection.password);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("Select * from Product");   
        while(rs.next()){
            int idProduct = rs.getInt("idProduct");
            String nameProduct = rs.getString("nameProduct");
            int priceProduct = rs.getInt("priceProduct");
            int idTypeProduct = rs.getInt("idTypeProduct");
            TypeProduct typeProduct = typeProductRepo.getTypeProductById(idTypeProduct);
            Product product = new Product(idProduct,nameProduct,priceProduct,typeProduct);
            productsList.add(product);
        }
        stm.close();
        return productsList;
    }

    public Product getProductById(int idProduct)throws Exception{
        Class.forName(Baseconection.nameClass);
        Connection con = DriverManager.getConnection(Baseconection.url, Baseconection.username, Baseconection.password);
        PreparedStatement ps = con.prepareStatement("Select * from Product where idProduct = ?");
        ps.setInt(1, idProduct);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String nameProduct = rs.getString("nameProduct");
        int priceProduct = rs.getInt("priceProduct");
        int idTypeProduct = rs.getInt("idTypeProduct");
        TypeProduct typeProduct = typeProductRepo.getTypeProductById(idTypeProduct);
        Product product = new Product(idProduct, nameProduct, priceProduct, typeProduct);
        ps.close();
        return product;
    }
      public void AddProduct(Product product)throws Exception{
        Class.forName(Baseconection.nameClass);
        Connection con = DriverManager.getConnection(Baseconection.url, Baseconection.username, Baseconection.password);
        PreparedStatement ps = con.prepareStatement("Insert into Product(nameProduct, priceProduct, idTypeProduct) values (?,?,?)");
        ps.setString(1, product.getNameProduct());
        ps.setInt(2, product.getPriceProduct());
        ps.setInt(3, product.getTypeProduct().getIdTypeProduct());
        ps.executeUpdate();
        ps.close();
    }
    public void deleteProduct(int idProduct)throws Exception{
        Class.forName(Baseconection.nameClass);
        Connection con = DriverManager.getConnection(Baseconection.url, Baseconection.username, Baseconection.password);
        PreparedStatement ps = con.prepareStatement("Delete from Product where idProduct = ?");
        ps.setInt(1, idProduct);
        ps.executeUpdate();
        ps.close();
    }
    public void editProduct(Product product) throws Exception{
         Class.forName(Baseconection.nameClass);
        Connection con = DriverManager.getConnection(Baseconection.url, Baseconection.username, Baseconection.password);
        PreparedStatement ps = con.prepareStatement("update Product set nameProduct = ?, priceProduct = ?, idTypeProduct = ?   where idProduct = ?");
        ps.setString(1, product.getNameProduct());
        ps.setInt(2, product.getPriceProduct());
        ps.setInt(3, product.getTypeProduct().getIdTypeProduct());
        ps.setInt(4, product.getIdProduct());
        ps.executeUpdate();
        ps.close();
    }

}
