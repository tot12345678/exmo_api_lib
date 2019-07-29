public class Seller extends ProfitController {
    double curse, profit = 0, usdO = 1, wonaGet = usdO * profit, btc;
    public Seller(double c){
        curse = c;
        btc = usdO/curse*0.998;
        checkOrderStatus();
    }
    private void checkOrderStatus(){
        while (true){
            getOrdersInfo();
            double curse2 = ProfitController.sell_price.get("BTC_USD");
            if(btc * curse2 >= usdO){
                profit = profit + (btc * curse2 - 1);
                System.out.println("продал за " + curse2 + " профит " + profit);
                break;
            }
        }
    }
}
