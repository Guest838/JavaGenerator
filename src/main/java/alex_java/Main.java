package alex_java;



import javax.management.ObjectInstance;

import static alex_java.Generator.Generate;
import alex_java.Test;

import java.lang.reflect.Field;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Test test = new Test();
        Generate(test);
        Class<?> cl = test.getClass();
        Field[] decFields = cl.getDeclaredFields();
        for (Field field : decFields) {
            field.setAccessible(true);
            try {
                System.out.println((field.get(test)));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}