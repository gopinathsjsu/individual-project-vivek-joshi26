package main.model;

public class Essentials extends Category{

    private int maxLimit = 3;
    private String category = "Essentials";


    @Override
    public boolean limitExceeded(double quantity) {
        return quantity> this.maxLimit;
    }

    @Override
    public String getCategoryName() {
        return this.category;
    }
}
