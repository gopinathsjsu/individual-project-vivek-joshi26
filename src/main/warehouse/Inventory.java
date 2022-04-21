package main.warehouse;

import main.model.Item;

import java.util.HashMap;
import java.util.HashSet;

public class Inventory {

    private static Inventory instance;
    private HashMap<String , Item> items = new HashMap<>();
    private HashSet<String>  cards = new HashSet<>();

    private Inventory(){}

    public static Inventory getInstance(){
        if (instance == null)
            instance = new Inventory();
        return instance;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public HashSet<String> getCards() {
        return cards;
    }
}
