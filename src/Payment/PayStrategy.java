package Payment;
//создаем общий интерфейс для всех стратегий
public interface PayStrategy {
    //метод должен определить прошла оплата или нет
    boolean pay(int paymentAmount);
    //метод должен собирать детали оплаты
    void collectPaymentDetails();
    int getAccountAmount();
}
