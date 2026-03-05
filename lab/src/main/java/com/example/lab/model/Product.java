package com.example.lab.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {
    @NotBlank(message = "Name is required")
    @Size(min=3,message = "name must be at least 3 characters")
    private String name;
    @Positive(message = "Price must be positive")
    private Double price;
    @NotBlank(message = "Category is required")
    private String category;
    @Positive(message = "Quantity must be positive")
    private int quantity;

    public Product(String name, Double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
