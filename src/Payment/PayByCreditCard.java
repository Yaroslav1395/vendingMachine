package Payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import CreditCard.CreditCard;

//класс конкретной стратегии
public class PayByCreditCard implements PayStrategy{
    ////поле для считывания данных при авторизации новых пользователей
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    //данная стратегия хранит кредитную карту
    private CreditCard card;

    //производим оплату
    @Override
    public void pay(int paymentAmount) {
        //проверяем авторизована карта или нет
        if (cardIsPresent()) {
            System.out.println("Оплатили " + paymentAmount + " используя кредитную карту.");
            //списываем с карты деньги
            card.setAmount(card.getAmount() - paymentAmount);
        }
    }

    //собираем данные для проверок
    @Override
    public void collectPaymentDetails() {
        try {
            //просим клиента ввести данные карты для ее создания
            System.out.print("Введите номер карты: ");
            String number = READER.readLine();
            System.out.print("Введите срок действия карты 'mm/yy': ");
            String date = READER.readLine();
            System.out.print("Введите CVV код: ");
            String cvv = READER.readLine();
            card = new CreditCard(number, date, cvv);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //вернет сумму на карте
    @Override
    public int getAccountAmount() {
        return card.getAmount();
    }

    //проверяем авторизована карта или нет
    private boolean cardIsPresent() {
        return card != null;
    }
}
