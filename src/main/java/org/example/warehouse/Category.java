package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private final String name;
    private static final Map<String, Category> instances = new HashMap<>();
    private Category(String name) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }
    public static Category of(String name) {
        if (name == null) throw new IllegalArgumentException("Category name can't be null");
        if (instances.containsKey(name)) return instances.get(name);
        else {
            Category category = new Category(name);
            instances.put(name, category);
            return category;
        }
    }
    public String getName() {return name;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
