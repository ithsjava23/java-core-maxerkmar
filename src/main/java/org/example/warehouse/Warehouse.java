package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private final String name;
    private final List<ProductRecord> products = new ArrayList<>();
    private final List<ProductRecord> updatedPrice = new ArrayList<>();

    private Warehouse(String name) {this.name = name;}
    public String getName() {return name;}
    public static Warehouse getInstance() {
        return new Warehouse("");
    }
    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }
    public boolean isEmpty() {
        return products.isEmpty();
    }
    public List<ProductRecord> getProducts() {
        return products.stream().toList();
    }
    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Product name can't be null or empty.");
        if (category == null) throw new IllegalArgumentException("Category can't be null.");
        if (id == null) id = UUID.randomUUID();
        if (price == null) price = BigDecimal.ZERO;
        UUID thisId = id;
        products.stream()
                .map(ProductRecord::uuid)
                .forEach(uuid -> {
                    if (uuid.equals(thisId))
                        throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        });
        ProductRecord productRecord = new ProductRecord(id, name, category, price);
        products.add(productRecord);
        return productRecord;
    }
    public Optional<ProductRecord> getProductById(UUID id) {
        return products.stream().filter(product -> product.uuid().equals(id)).findFirst();
    }
    public void updateProductPrice(UUID uuid, BigDecimal price) {
        if (getProductById(uuid).isEmpty()) throw new IllegalArgumentException("Product with that id doesn't exist.");
        for (ProductRecord product : products) {
            if (product.uuid().equals(uuid)) {
                product.setPrice(price);
                updatedPrice.add(product);
                break;
            }
        }
    }
    public List<ProductRecord> getChangedProducts() {
        return updatedPrice;
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }
    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
    }

}
