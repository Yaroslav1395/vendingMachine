package PayPalAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class PayPalAccounts {
    private static List<PayPalUser> payPalAccountsList = new ArrayList<>();

    static {
        createPayPalAccounts();
    }
    public static Map<String, PayPalUser> getPayPalAccountsList() {
        Map<String, PayPalUser> payPalUserMap = new HashMap<>();
        for (PayPalUser payPalUser : payPalAccountsList) {
            payPalUserMap.put(payPalUser.getEmail(), payPalUser);
        }
        return payPalUserMap;
    }

    private static void createPayPalAccounts(){
        payPalAccountsList = List.of(
                new PayPalUser("amanda@ya.com", "amanda1985", 100),
                new PayPalUser("john@amazon.eu", "john1985", 20),
                new PayPalUser("boris@ya.com", "boris1995", 30),
                new PayPalUser("vlad@ya.com", "vlad1995", 40)
        );
    }

    public static void acceptPaymentInformation(PayPalUser payPalUser){
        for (PayPalUser user : payPalAccountsList) {
            if(user.getEmail().equals(payPalUser.getEmail())){
                user.setAmount(payPalUser.getAmount());
            }
        }
    }

}
