package me.rubl.lesson_one;

import java.util.ArrayList;
import java.util.Arrays;

public class LessonOne {

    public static void main(String[] args) {

        Integer[] arr1 = {1,2,3,4,5};
        System.out.println(Arrays.toString(arr1));
        taskOne(arr1, 1, 3);
        System.out.println(Arrays.toString(arr1));

        Box<Apple> appleBox = new Box<Apple>();
        appleBox.put(new Apple());
        appleBox.put(new Apple());
        appleBox.put(new Apple());

        Box<Apple> appleBox2 = new Box<Apple>();
        appleBox2.put(new Apple());
        appleBox2.put(new Apple());
        appleBox2.put(new Apple());
        appleBox2.put(new Apple());

        Box<Orange> orangeBox = new Box<Orange>();
        orangeBox.put(new Orange());
        orangeBox.put(new Orange());

        System.out.println(appleBox.getWeight());
        appleBox2.pour(appleBox);
        System.out.println(appleBox.getWeight());

        System.out.println("orange: " + orangeBox.getWeight());
        System.out.println("apple: " + appleBox.getWeight());

        System.out.println(appleBox.compare(orangeBox));
    }

    static <T> void taskOne(T[] arr, int firstIndex, int secondIndex) {
        if (firstIndex < arr.length && secondIndex < arr.length && firstIndex != secondIndex) {
            T tmp = arr[firstIndex];
            arr[firstIndex] = arr[secondIndex];
            arr[secondIndex] = tmp;
        }
    }

    static <T> ArrayList<T> taskTwo(T[] arr) {

        return new ArrayList<T>(Arrays.asList(arr));
    }

}
