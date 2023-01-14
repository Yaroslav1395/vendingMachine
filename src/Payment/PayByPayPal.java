package Payment;

import PayPalAccount.PayPalAccounts;
import PayPalAccount.PayPalUser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
//класс конкретной стратегии
public class PayByPayPal implements PayStrategy{
    //поле для считывания данных при авторизации новых пользователей
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    //поле для хранения email клиента
    PayPalUser payPalUser = new PayPalUser();
    private boolean signedIn;



    //Если клиент уже вошел в систему, то для следующей оплаты данные вводить
    //не придется
    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            System.out.println("Оплатили " + paymentAmount + " используя PayPal.");
            payPalUser.setAmount(payPalUser.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    //собираем данные от клиента
    @Override
    public void collectPaymentDetails() {
        try {
            //пока пользователь не авторизован будет производиться это действие
            while (!signedIn) {
                System.out.print("Введите ваш email: ");
                //считываем данные с консоли и присваивает в поле email
                payPalUser.setEmail(READER.readLine());
                System.out.print("Введите ваш пароль: ");
                //считываем данные с консоли и присваивает в поле email
                payPalUser.setPassword(READER.readLine());
                //verify() - проверит данные в DATA_BASE
                if (verify()) {
                    System.out.println("Проверка данных прошла успешно.");
                } else {
                    System.out.println("Неправильный адрес электронной почты или пароль!");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getAccountAmount() {
        return payPalUser.getAmount();
    }

    //после того как клиент введет данные при авторизации, метод проверит есть ли он в базе
    private boolean verify() {
        //проверяем есть ли такой аккаунт
        setSignedIn(checkPayPalUser());
        return signedIn;
    }

    //метод изменит состояние поля авторизации
    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    private boolean checkPayPalUser(){
        Map<String, PayPalUser> payPalUserList = PayPalAccounts.getPayPalAccountsList();

        if(payPalUser.getPassword().equals(payPalUserList.get(payPalUser.getEmail()).getPassword())){
            payPalUser.setAmount(payPalUserList.get(payPalUser.getEmail()).getAmount());
            return true;
        }else {
            return false;
        }
    }
    private void sendPaymentDetailsToPayPal(PayPalUser payPalUser){
        PayPalAccounts.acceptPaymentInformation(payPalUser);
    }
}
