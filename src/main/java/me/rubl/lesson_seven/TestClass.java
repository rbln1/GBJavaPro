package me.rubl.lesson_seven;

import me.rubl.lesson_seven.annotations.AfterSuite;
import me.rubl.lesson_seven.annotations.BeforeSuite;
import me.rubl.lesson_seven.annotations.Test;

public class TestClass {

    private static int testCounter = 0;

    @BeforeSuite
    public void prepare() {
        System.out.println("prepare: " + testCounter);
        testCounter = 100;
    }

    @Test(priority = 3)
    public void test1() {
        System.out.println("test1: " + ++testCounter);
    }

    @Test(priority = 1)
    public void test2() {
        System.out.println("test2: " + ++testCounter);
    }

    @Test
    public void test3() {
        System.out.println("test3: " + ++testCounter);
    }

    @Test
    public void test4() {
        System.out.println("test4: " + ++testCounter);
    }

    @Test
    public void test5() {
        System.out.println("test5: " + ++testCounter);
    }

    @Test(priority = 2)
    public void test6() {
        System.out.println("test6: " + ++testCounter);
    }

    @Test(priority = 7)
    public void test7() {
        System.out.println("test7: " + ++testCounter);
    }

    @AfterSuite
    public void shutdown() {
        System.out.println("shutdown: " + testCounter);
    }
}
