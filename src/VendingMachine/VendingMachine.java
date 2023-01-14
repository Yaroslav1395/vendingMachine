package VendingMachine;

import Payment.*;
import Products.Product;

import java.util.*;

import static java.util.stream.Collectors.*;

public class VendingMachine{
    private final List<Product> products = new ArrayList<>();
    private int capacity = 20;
    private int vendingMachineMoney = 0;
    private PayStrategy payStrategy = new PayByCash();
    private Order order = new Order();
    private boolean isPayStrategyInstalled = false;

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
                .filter(product -> product.getPrice() <= payStrategy.getAccountAmount())
                .collect(toSet())
                .forEach(product -> System.out.printf("%s - Купить %s\n", product.getKey(), product.getName()));
    }
    private void byProduct(String userInput){
        Product foundProduct = products.stream()
                .filter(product -> Character.toString(product.getKey()).equals(userInput))
                .findFirst().orElseThrow();

        payStrategy.pay(foundProduct.getPrice());
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
        while (!order.isClosed()){
                printUserActions();
                String userInputString = userInputAction();
                switch (userInputString) {
                    case "z" -> makePayment();
                    case "x" -> printAvailableProducts();
                    case "a" -> order.setClosed(true);
                    default -> byProduct(userInputString);
            }
        }

    }

    private void paymentOptionsPrint(){
        System.out.println("Выберите способ оплаты:" + "\n" +
                "p - PayPal" + "\n" +
                "o - Кредитная карта" + "\n" +
                "i - Наличные");
    }
    private void makePayment() {
        paymentOptionsPrint();

        String paymentMethod = userInputAction();

        // Клиент создаёт различные стратегии на основании
        // пользовательских данных, конфигурации и прочих параметров.
        switch (paymentMethod) {
            case "p" -> payStrategy = new PayByPayPal();
            case "o" -> payStrategy = new PayByCreditCard();
            case "i" -> payStrategy = new PayByCash();
            default -> {
                System.out.println("Такого способа оплаты нет. Попробуйте снова");
                makePayment();
            }
        }

        // Объект заказа делегирует сбор платёжных данных стратегии, т.к.
        // только стратегии знают какие данные им нужны для приёма оплаты.
        order.processOrder(payStrategy);
    }

}
