package main.iteratorPattern;

import java.util.ArrayList;

public class OrderRepository implements Container{

    ArrayList<String> orderContent;

    public OrderRepository(ArrayList<String> orderContent){
        this.orderContent = orderContent;
    }


    @Override
    public Iterator getIterator() {
        return new OrderIterator();
    }

    private class OrderIterator implements Iterator{
        int index;


        @Override
        public boolean hasNext() {
            return index < orderContent.size();
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return orderContent.get(index++);
            }
            return null;
        }
    }
}
