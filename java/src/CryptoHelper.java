import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CryptoHelper implements Connection{
    double avgSell, avgBuy;
    public CryptoHelper(String pair){
        avgSell = getAvgOfBuys(pair);
        avgBuy = getAvgOFsells(pair);
    }
    public static double getAvgOfBuys(String pair) {
        Matcher m = Pattern.compile("type:buy[a-z0-9:,.]+price:[0-9.]+")
                .matcher(Objects.requireNonNull(CONNECTION
                        .Request("trades", new HashMap<String, String>() {
                            {
                                put("pair", pair);
                            }
                        }))
                        .replaceAll(("[\\[\\]\"{}]"), "")
                        .replaceAll((pair + ":"), ""));
        int counter = 0;
        double sumOfAll = 0;
        while (m.find()) {
            counter++;
            sumOfAll += Double.parseDouble(m.group()
                    .replaceAll(("[a-z0-9:.,]+:"), ""));
        }
        return sumOfAll / counter;
    }
    public static double getAvgOFsells(String pair){
        Matcher m = Pattern.compile("type:sell[a-z0-9:,.]+price:[0-9.]+")
                .matcher(Objects.requireNonNull(CONNECTION
                        .Request("trades", new HashMap<String, String>() {
                            {
                                put("pair", pair);
                            }}))
                        .replaceAll(("[\\[\\]\"{}]"), "")
                        .replaceAll((pair + ":"), ""));
        int counter = 0;
        double sumOfAll = 0;
        while (m.find()) {
            counter++;
            sumOfAll += Double.parseDouble(m.group()
                    .replaceAll(("[a-z0-9:.,]+:"), ""));
        }
        return sumOfAll / counter;

        //String result = ;
        //type:sell,[a-z0-9:,.]+price:[0-9.]+
        //price:[0-9.]+
        //date":[0-9]+
    }
}
