package Payment;

import java.util.InputMismatchException;
import java.util.Scanner;
//создаем конкретную стратегию
public class PayByCash implements PayStrategy{
    private int amount = 0;
    //метод снимет средства за товар
    @Override
    public void pay(int paymentAmount) {
        if (cashCheck()) {
            System.out.println("Оплатили " + paymentAmount + " используя PayPal.");
            amount -= paymentAmount;
        }
    }
    //открывает возможность для пополнения наличными
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

    //вернет сумму пополнения
    @Override
    public int getAccountAmount() {
        return amount;
    }

    //проверит есть ли средства на балансе
    private boolean cashCheck(){
        return amount != 0;
    }
}
