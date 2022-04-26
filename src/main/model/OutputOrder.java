package main.model;

public class OutputOrder {

    private String item;
    private int quantity;
    private int price;
    private static int totalPrice;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static int getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(int totalPrice) {
        OutputOrder.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OutputOrder{" +
                "item='" + item + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", TotalPrice=" + totalPrice +
                '}';
    }
}
