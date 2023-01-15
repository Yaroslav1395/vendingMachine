package Payment;
//создаем общий интерфейс для всех стратегий
public interface PayStrategy {
    //метод должен производить оплату
    void pay(int paymentAmount);
    //метод должен собирать детали оплаты
    void collectPaymentDetails();
    //метод должен возвращать данные о балансе
    int getAccountAmount();
}
