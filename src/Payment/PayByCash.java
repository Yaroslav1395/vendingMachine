package Payment;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PayByCash implements PayStrategy{
    private int amount = 0;
    @Override
    public boolean pay(int paymentAmount) {
        if (cashCheck()) {
            System.out.println("Оплатили " + paymentAmount + " используя PayPal.");
            amount -= paymentAmount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void collectPaymentDetails() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Внесите оплату -> ");
            amount += scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Ввели строку вместо цифры. Попробуйте снова");
            collectPaymentDetails();
        }
    }

    @Override
    public int getAccountAmount() {
        return amount;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount += amount;
    }

    private boolean cashCheck(){
        return amount != 0;
    }
}
