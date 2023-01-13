package Products;

import java.util.Random;

public class Product {
    private String name;
    private int price;

    public Product() {
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
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

    public void setPrice(int price) {
        this.price = price;
    }

    private static Product[] makeProductsObjects(){
        return new Product[]{
                new Gum("Жвачка", 20),
                new Chocolate("Шоколад", 40),
                new Soda("Газировка", 50),
                new Water("Вода", 40),
                new Crisps("Чипсы", 30)
        };
    }

    public static Product returnOneRandomProduct(){
        Product[] products = makeProductsObjects();
        int randomNumber = new Random().nextInt(products.length - 1);
        return new Product(products[randomNumber].getName(), products[randomNumber].getPrice());
    }
}
