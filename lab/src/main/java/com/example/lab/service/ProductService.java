package com.example.lab.service;

import com.example.lab.exception.InvalidPriceRangeException;

import com.example.lab.exception.ResourceNotFoundException;
import com.example.lab.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ProductService {
    Map<String,Product> products=new HashMap<>();
    public Product create(String name,Double price,String description,Integer quantity){
        Product product=new Product(name,price,description,quantity);
        products.put(product.getName(),product);
        return product;
    }
    public List<Product> findAll(){
        return new ArrayList<>(products.values());
    }
    public Product findByName(String name){
        Product product = products.get(name);
        if(product == null){
            throw new ResourceNotFoundException("Product not found with name: " + name);
        }
        return product;
    }
    public Product update(String name,Double price,String description,Integer quantity){
        if(products.containsKey(name)) {
            Product product=new Product(name,price,description,quantity);
            return products.put(name,product);
        }
        return null;
    }
    public void delete(String name){
        if(!products.containsKey(name)){
            throw new ResourceNotFoundException("Cannot delete. Product not found with name: " + name);
        }
        products.remove(name);
    }
    public List<Product> findByCategory(String category){
        List<Product> result = new ArrayList<>();
        for(Product p : products.values()){
            if(p.getCategory().equalsIgnoreCase(category)){
                result.add(p);
            }
        }
        return result;
    }
    public List<Product> findByRange(Double minPrice,Double maxPrice){
        if(minPrice > maxPrice){
            throw new InvalidPriceRangeException("Invalid price range: minPrice must be <= maxPrice");
        }
        List<Product> result = new ArrayList<>();
        for(Product p : products.values()){
            if(p.getPrice()>=minPrice&&p.getPrice()<=maxPrice){
                result.add(p);
            }
        }
        return result;
    }


}
