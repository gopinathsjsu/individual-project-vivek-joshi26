package main.model;

public abstract class Category {

    public static Category Create(String category){
        if(category.equals("Essentials"))
            return new Essentials();
        else if(category.equals("Luxury"))
            return new Luxury();
        else return new Miscellaneous();
    }

    public abstract boolean limitExceeded(double quantity);

    public abstract String getCategoryName();

}
