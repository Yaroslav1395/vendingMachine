package Products;

import java.util.Objects;
import java.util.Random;

public class Product {
    private String name;
    private int price;
    private char key;

    public Product() {
    }

    public Product(String name, int price, char key) {
        this.name = name;
        this.price = price;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public void setPrice(int price) {
        this.price = price;

    }

    private static Product[] makeProductsObjects(){
        return new Product[]{
                new Gum("Жвачка", 20, 'q'),
                new Chocolate("Шоколад", 40, 'w'),
                new Soda("Газировка", 50, 'e'),
                new Water("Вода", 40, 'r'),
                new Crisps("Чипсы", 30, 't')
        };
    }

    public static Product returnOneRandomProduct(){
        Product[] products = makeProductsObjects();
        int randomNumber = new Random().nextInt(products.length - 1);
        return new Product(
                products[randomNumber].getName(),
                products[randomNumber].getPrice(),
                products[randomNumber].getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return name + " Цена: " + price;
    }
}
