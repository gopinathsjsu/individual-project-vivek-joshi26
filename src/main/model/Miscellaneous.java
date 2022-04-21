package main.model;

public class Miscellaneous extends Category{

    private int maxLimit = 6;
    private String category = "Misc";


    @Override
    public boolean limitExceeded(double quantity) {
        return quantity> this.maxLimit;
    }

    @Override
    public String getCategoryName() {
        return this.category;
    }

}
