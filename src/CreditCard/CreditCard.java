package CreditCard;

public class CreditCard {
    //сумма на карте
    private int amount;
    //номер карты
    private String number;
    //дата истечения срока
    private String date;
    //код карты
    private String cvv;

    //конструктор карты
    public CreditCard(String number, String date, String cvv) {
        this.amount = 100_000;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    //гетер и сеттер суммы карты
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
