package main.model;

public class OutputOrder {

    private String item;
    private int quantity;
    private int price;
    private int totalPrice = 0;

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

    public int getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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
