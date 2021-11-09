package stocktrader.server;

class Stock {
    private final int stockNo;
    private double price;

    public Stock(int stockNo, int price) {
        this.stockNo = stockNo;
        this.price = price;
    }

    public int getStockNo() {
        return stockNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
