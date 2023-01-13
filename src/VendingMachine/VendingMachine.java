package VendingMachine;

import Products.Product;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class VendingMachine{
    private final List<Product> products = new ArrayList<>();
    private int capacity = 20;
    private int vendingMachineMoney = 0;
    private int userMoney = 0;

    public VendingMachine() {
        fillVendingMachine();
        printAvailableProducts();
        doActions();
    }

    private void fillVendingMachine(){
        while (!(capacity == 0)){
            products.add(Product.returnOneRandomProduct());
            capacity -= 1;
        }
    }

    private void userInputMoney(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Внесите оплату -> ");
            userMoney += scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Ввели строку вместо цифры. Попробуйте снова");
            userInputMoney();
        }
    }
    private String userInputAction(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите символ действия: ");
        String userInput = "";
        try{
            userInput = scanner.nextLine();
            if(userInput.length() > 1) throw new IllegalArgumentException();
            if(isNumeric(userInput)) throw new IllegalArgumentException();
            return userInput;
        }
        catch (IllegalArgumentException e){
            System.out.println("Такого действия нет, попробуйте снова");
        }
        return "";
    }
    private boolean isNumeric(String string){
        try{
            Integer.parseInt(string);
        }
        catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
    private void printAvailableProductsToBuy(){
        products.stream()
                .filter(product -> product.getPrice() <= userMoney)
                .collect(toSet())
                .forEach(product -> System.out.printf("%s - Купить %s\n", product.getKey(), product.getName()));
    }
    private void byProduct(String userInput){
        Product foundProduct = products.stream()
                .filter(product -> Character.toString(product.getKey()).equals(userInput))
                .findFirst().orElseThrow();

        userMoney -= foundProduct.getPrice();
        vendingMachineMoney += foundProduct.getPrice();
        products.remove(foundProduct);
    }
    private void printAvailableProducts(){
        System.out.println("|---Товар---|---Цена---|");
        products.forEach(product -> {
            System.out.printf("|%10s | %5s    |\n", product.getName(), product.getPrice());
        });
    }
    private void printUserActions(){
        System.out.println("Выберите действие");
        System.out.println("z - Внести оплату");
        System.out.println("x - Посмотреть весь товар");
        System.out.println("a - Выйти");
        printAvailableProductsToBuy();
    }
    private void doActions(){
        boolean isEnd = false;
        while (!(isEnd)){
            printUserActions();
            String userInputString = userInputAction();
            switch (userInputString) {
                case "z" -> userInputMoney();
                case "x" -> printAvailableProducts();
                case "a" -> isEnd = true;
                default -> byProduct(userInputString);
            }
        }
    }

}
