package me.rubl.lesson_seven;

import me.rubl.lesson_seven.annotations.AfterSuite;
import me.rubl.lesson_seven.annotations.BeforeSuite;
import me.rubl.lesson_seven.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class LessonSeven {

    public static void main(String[] args) {
        start(TestClass.class);
    }

    public static void start(Class tClass) {

        try {
            final Object inst = tClass.getConstructor().newInstance();

            Method beforeSuiteMethod = null;
            Method afterSuiteMethod = null;

            Method[] methods = tClass.getDeclaredMethods();
            HashMap<Integer, ArrayList<Method>> methodsMap = new HashMap<>();

            for (Method m : methods) {
                if (m.isAnnotationPresent(BeforeSuite.class)) {
                    if (beforeSuiteMethod == null) {
                        beforeSuiteMethod = m;
                    } else throw new RuntimeException("BeforeSuite method can only be in one instance");
                }
                if (m.isAnnotationPresent(AfterSuite.class)) {
                    if (afterSuiteMethod == null) {
                        afterSuiteMethod = m;
                    } else throw new RuntimeException("AfterSuite method can only be in one instance");
                }
                if (m.isAnnotationPresent(Test.class)) {
                    int priority = m.getAnnotation(Test.class).priority();
                    if(methodsMap.containsKey(priority)){
                        methodsMap.get(priority).add(m);
                    } else {
                        ArrayList<Method> methodsList = new ArrayList<>();
                        methodsList.add(m);
                        methodsMap.put(priority, methodsList);
                    }
                }
            }

            if (beforeSuiteMethod != null) beforeSuiteMethod.invoke(inst);

            methodsMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(it -> {
                        it.getValue().forEach(m -> {
                            try {
                                m.invoke(inst);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        });
                    });

            if (afterSuiteMethod != null) {
                afterSuiteMethod.invoke(inst);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
