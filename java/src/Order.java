import java.util.HashMap;
import java.util.Objects;

class Order implements Connection{
    static boolean getOpenOrders(){
        int s = Objects.requireNonNull(CONNECTION
                .Request("user_open_orders", null))
                .replaceAll(("\\{}"), (""))
                .length();
        return s == 0;
    }
    static void createOrder(String pair, double quantity, double price, String type){
        Objects.requireNonNull(CONNECTION
            .Request("pair_settings", new HashMap<String, String>() {
            {
                put("pair", pair);
                put("quantity", String.valueOf(quantity));
                put("price", String.valueOf(price));
                put("type", type);
            }
        }));
    }
}
