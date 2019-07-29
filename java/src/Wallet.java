import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wallet implements Connection {
    private static HashMap<String, Double> balance_map = new HashMap<>();
    private static String myAllBalance;

    static double balance = 0;
    Wallet(String crypto){
        getUserInfoBalance();
        balance = balance_map.get(crypto);

    }
    public static void getUserInfoBalance(){
        Matcher m = Pattern.compile("balances.*},")
                            .matcher(Objects.requireNonNull
                                    (CONNECTION
                                            .Request("user_info", null)));
        while (m.find()) {
            myAllBalance = m.group()
                            .replaceAll((":\\{|[^A-Z:0-9,]"), "");
        }
        getValueOfBalance();
    }
    public static void getValueOfBalance(){
        String[] cryptoAndValue = myAllBalance.split(",");
        for (String s : cryptoAndValue) {
            balance_map.put(s.split(":")[0],
                            Double.parseDouble(s.split(":")[1]));
        }
    }
}
