package main.model;

public class Luxury extends Category{
    private int maxLimit = 20;
    private String category = "Luxury";


    @Override
    public boolean limitExceeded(double quantity) {
        return quantity > this.maxLimit;
    }

    @Override
    public String getCategoryName() {
        return this.category;
    }
}
