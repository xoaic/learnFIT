package stocktrader.server;

import java.time.Instant;
import java.util.*;

// Should be StockController -- storing business logic
public class StockServer {
    private static final Map<String, String> ALLOWED_USERS = new HashMap<>();

    static {
        // map.put(key, value) -> { key => value }
        // map.put(k1, v1) -> { key : value, k1 : v1 }
        ALLOWED_USERS.put("binhdh", "11223344");
    }

    private boolean hasLogin = false;
    private final Map<Integer, Stock> allStocks = new HashMap<>();
    private final List<StockPurchase> yourPurchases = new ArrayList<>();

    public StockServer() {
        // initialize some stock no
        allStocks.put(1, new Stock(1, 20));
        allStocks.put(2, new Stock(2, 12));
        allStocks.put(3, new Stock(3, 16));
        allStocks.put(4, new Stock(4, 14));
    }

    /**
     * @effects <pre>
     *     if username in ALLOWED_USERS and password = ALLOWED_USERS[username]
     *       set this.hasLogin = true
     *       return "OK"
     *     else
     *       return "aopsdjasdasd" (not logged in)
     * </pre>
     */
    public String login(String username, String password) {
        if (ALLOWED_USERS.containsKey(username)
                && password.equals(ALLOWED_USERS.get(username))) {
            this.hasLogin = true;
            return "OK";
        } else {
            return "aopsdjasdasd";
        }
    }

    public String listAllStocks() {
        nextDay();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Updated ").append(new Date()).append("\n");
        String heading = "   # | Stock No | Price ";
        String format = "     | %8s | %5s";
        if (allStocks.isEmpty()) {
            stringBuilder.append("No stock available!");
        } else {
            stringBuilder.append(heading);
            for (Stock stock : allStocks.values()) {
                stringBuilder.append("\n").append(String.format(
                    format, stock.getStockNo(), stock.getPrice()
                ));
            }
        }
        return stringBuilder.toString();
    }

    public boolean purchase(int stockNo, int quantity) {
        if (!allStocks.containsKey(stockNo)) {
            throw new IllegalArgumentException("Invalid stock no!");
        }
        StockPurchase purchase = new StockPurchase(
                stockNo,
                System.currentTimeMillis(),
                allStocks.get(stockNo).getPrice(),
                quantity
        );
        return yourPurchases.add(purchase);
    }

    public String listOwnStocks() {
        throw new UnsupportedOperationException();
    }

    public boolean sellStock(int stockNo, int quantity) {
        throw new UnsupportedOperationException();
    }

    public void nextDay() {
        Random random = new Random();
        double floor = 10;
        double ceiling = 100;
        for (Stock stock : allStocks.values()) {
            double newPrice = floor + random.nextDouble() * (ceiling - floor);
            stock.setPrice(newPrice);
        }
    }

    // # | Purchase time | Stock no | Price at purchase | Current price | Benefit
    public String trackStocks() {
        throw new UnsupportedOperationException();
    }
}
