package PayPalAccount;

public class PayPalUser {
    private String email;
    private String password;
    private int amount;

    public PayPalUser() {
    }

    public PayPalUser(String email, String password, int amount) {
        this.email = email;
        this.password = password;
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
