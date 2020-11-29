package me.rubl.lesson_five;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class MainClass {

    private static final int CARS_COUNT = 4;

    private static AtomicInteger placeCounter = new AtomicInteger(0);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(
                new Road(60),
                new Tunnel(CARS_COUNT / 2),
                new Road(40)
        );

        CyclicBarrier prepareBarrier = new CyclicBarrier(
                CARS_COUNT + 1,
                () -> System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!")
        );

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), prepareBarrier, placeCounter);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        prepareBarrier.await();

        while (true) {
            if (placeCounter.get() == CARS_COUNT) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                break;
            }
        }
    }
}
