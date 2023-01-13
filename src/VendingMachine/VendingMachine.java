package VendingMachine;

import Products.Product;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine{
    private final List<Product> products = new ArrayList<>();
    private int capacity = 20;

    public VendingMachine() {
        fillVendingMachine();
    }

    private void fillVendingMachine(){
        while (!(capacity == 0)){
            products.add(Product.returnOneRandomProduct());
            capacity -= 1;
        }
    }
}
