package main.model;

public class Item {

    private Category category;
    private String itemName;
    private int quantity;
    private double price;

    public Item(Category category, String itemName, int quantity, double price) {
        this.category = category;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }


    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "category=" + category +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
