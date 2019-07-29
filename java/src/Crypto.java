import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

class Crypto implements Connection{
    static ArrayList<String> pairs   = new ArrayList<>();
    private static HashMap<String, Double>  min_quantity_map = new HashMap<>(),
                                            max_quantity_map = new HashMap<>(),
                                            min_price_map    = new HashMap<>(),
                                            max_price_map    = new HashMap<>(),
                                            min_amount_map   = new HashMap<>(),
                                            max_amount_map   = new HashMap<>();
    static double min_quantity;
    static double max_quantity;
    static double min_price;
    static double max_price;
    static double max_amount;
    static double min_amount;
    Crypto(String pair){
        getMap();
        double min_quantity = min_quantity_map.get(pair);
        double max_quantity = max_quantity_map.get(pair);
        double min_price = min_price_map.get(pair);
        double max_price = max_price_map.get(pair);
        double max_amount = min_amount_map.get(pair);
        double min_amount = max_amount_map.get(pair);
    }

    private static void getMap(){
        String shit = Objects.requireNonNull(CONNECTION
                .Request("pair_settings", null))
                .replaceAll(("\""), "")
                .replaceAll(("},"), "}\n")
                .replaceAll(("^\\{|}$"), "")
                .replaceAll((":\\{"), " {");
        getNamesOfPairs();
        getPairs(shit);
    }



        static void getNamesOfPairs(){
        String[] cryptoWithAllInfo = Objects.requireNonNull(CONNECTION
                .Request("pair_settings", null))
                .replaceAll(("\""), "")
                .replaceAll(("},"), "}\n")
                .replaceAll(("^\\{|}$"), "")
                .replaceAll((":\\{"), " {")
                .split("\n");
        for (String s : cryptoWithAllInfo) {
            pairs.add(s.replaceAll("[^A-Z]{2,}", "").trim());
        }
    }
    private static void getPairs(String shit){
        String[] allCrypto = shit.replaceAll(("[A-Za-z,_{}]"), "")
                         .split("\n");
        for (int i = 0; i < allCrypto.length; i++) {
            String[] everyCrypto = allCrypto[i].trim()
                        .replaceAll("^:", "")
                        .split(":");
            min_quantity_map.put(pairs.get(i), Double.valueOf(everyCrypto[0]));
            max_quantity_map.put(pairs.get(i), Double.valueOf(everyCrypto[1]));
            min_price_map.put(pairs.get(i), Double.valueOf(everyCrypto[2]));
            max_price_map.put(pairs.get(i), Double.valueOf(everyCrypto[3]));
            min_amount_map.put(pairs.get(i), Double.valueOf(everyCrypto[4]));
            max_amount_map.put(pairs.get(i), Double.valueOf(everyCrypto[4]));
        }
    }
}
