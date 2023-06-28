package com.example.ProductManager.model.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ProductManager.model.entity.TypeProduct;

@Repository
public class TypeProductRepo {
   
    @Autowired
    public ArrayList<TypeProduct> getallTypeProduct() throws Exception{
        ArrayList<TypeProduct> tp = new ArrayList<>();
        Class.forName(Baseconection.nameClass);
        Connection con = DriverManager.getConnection(Baseconection.url, Baseconection.username, Baseconection.password);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("Select * from TypeProduct");   
        while(rs.next()){
            int idTypeProduct = rs.getInt("idTypeProduct");
            String nameTypeProduct = rs.getString("nameTypeProduct");
            TypeProduct typeProduct = new TypeProduct(idTypeProduct, nameTypeProduct);
            tp.add(typeProduct);
        }
        con.close();
        stm.close();
        return tp;        
    }
    public TypeProduct getTypeProductById(int idTypeProduct) throws Exception{
        Class.forName(Baseconection.nameClass);
        Connection con = DriverManager.getConnection(Baseconection.url, Baseconection.username, Baseconection.password);
        PreparedStatement ps = con.prepareStatement("select * from TypeProduct where idTypeProduct = ?");
        ps.setInt(1, idTypeProduct);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String nameTypeProduct = rs.getString("nameTypeProduct");
        TypeProduct typeProduct = new TypeProduct(idTypeProduct, nameTypeProduct);
        ps.close();
        return typeProduct;
    }
}
