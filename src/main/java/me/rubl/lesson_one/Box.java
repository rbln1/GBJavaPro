package me.rubl.lesson_one;

import java.util.ArrayList;

public class Box<T extends Fruit> {

    private ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<T>();
    }

    public void put(T fruit) {
        fruits.add(fruit);
    }

    public ArrayList<T> getAll() {
        return fruits;
    }

    public float getWeight() {
        float weight = 0.0f;

        if (fruits.size() > 0) {
            for (T fruit : fruits) {
                weight += fruit.getWeight();
            }
        }

        return weight;
    }

    public boolean compare(Box<? extends Fruit> box) {
        return getWeight() == box.getWeight();
    }

    public void pour(Box<T> into) {
        if (fruits.size() > 0) {
            for (T fruit : fruits) {
                into.put(fruit);
            }
            fruits.clear();
        }
    }
}
