import java.util.Objects;

class Controller implements Connection {

    static void monitorOfBalance() {
        do for (String nameOfCrypto : getListOfCryptos()) {
            new Wallet(nameOfCrypto);
            if (Wallet.balance >= 0) {
                for (String pair : Crypto.pairs) {
                    if(Order.getOpenOrders()){
                    ProfitController.calculationOfCreationOrders(pair);
                    }
                }
            }
        } while (true);
    }

    static String[] getListOfCryptos() {
        return Objects.requireNonNull(CONNECTION
                .Request("currency", null))
                .replaceAll("[^A-Z,]", "")
                .split(",");
    }
}
