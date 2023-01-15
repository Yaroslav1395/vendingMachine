package Payment;

import PayPalAccount.PayPalAccounts;
import PayPalAccount.PayPalUser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.io.BufferedReader;
//класс конкретной стратегии
public class PayByPayPal implements PayStrategy{
    //поле для считывания данных при авторизации новых пользователей
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    //поле для хранения данных пользователя
    PayPalUser payPalUser = new PayPalUser();
    private boolean signedIn;

    @Override
    public void pay(int paymentAmount) {
        if (signedIn) {
            System.out.println("Оплатили " + paymentAmount + " используя PayPal.");
            payPalUser.setAmount(payPalUser.getAmount() - paymentAmount);
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
        //проверяем есть ли такой аккаунт в базе PayPal
        setSignedIn(checkPayPalUser());
        return signedIn;
    }

    //метод изменит состояние поля авторизации
    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    //подключимся к базе данных PayPal чтобы получить данные пользователя
    private boolean checkPayPalUser(){
        Map<String, PayPalUser> payPalUserList = PayPalAccounts.getPayPalAccountsList();

        if(payPalUser.getPassword().equals(payPalUserList.get(payPalUser.getEmail()).getPassword())){
            payPalUser.setAmount(payPalUserList.get(payPalUser.getEmail()).getAmount());
            return true;
        }else {
            return false;
        }
    }

    //после совершения оплаты, отправляем данные PayPal
    private void sendPaymentDetailsToPayPal(PayPalUser payPalUser){
        PayPalAccounts.acceptPaymentInformation(payPalUser);
    }
}
