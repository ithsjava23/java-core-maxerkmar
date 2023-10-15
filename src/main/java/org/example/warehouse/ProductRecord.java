package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRecord {
    private UUID id;
    private String name;
    private Category category;
    private BigDecimal price;

    public ProductRecord(UUID id, String name, Category category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public UUID uuid() {return id;}

    public String name() {return name;}

    public Category category() {return category;}

    public BigDecimal price() {return price;}

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
