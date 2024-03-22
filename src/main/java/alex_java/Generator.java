package alex_java;

import java.lang.reflect.Field;
import java.lang.management.ManagementFactory;
import java.lang.Math;

public class Generator {
    private static long jvmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
    private static final long module = (long) Math.pow(2, 31);
    private static final long multiplier = 214013;
    private static final long increment = 2531011;

    public static long Generate_long() {
        long randLong = (multiplier * jvmStartTime + increment) % module;
        jvmStartTime = randLong;
        return randLong;
    }

    public static <T> void Generate(T initObj) {
        try {
            Class<?> initClass = initObj.getClass();
            Field[] decFields = initClass.getDeclaredFields();
            for (Field field : decFields) {
                field.setAccessible(true);
                if (field.getType().equals(String.class)) {
                    field.setAccessible(true);
                    short stringLength = (short) (Generate_long() % Short.MAX_VALUE);
                    char[] string = new char[stringLength];
                    for (short i = 0; i < stringLength; i++) {
                        string[i] = (char) (Generate_long() % 65535);
                    }
                    field.set(initObj, new String(string));
                } else if (field.getType().equals(int.class)) {
                    field.set(initObj, (int) (Generate_long() % Integer.MAX_VALUE));
                } else if (field.getType().equals(long.class)) {
                    field.set(initObj, (Generate_long()));
                } else if (field.getType().equals(boolean.class)) {
                    int generatedValue = (int) (Generate_long() % 2);
                    boolean boolValue;

                        if (generatedValue == 1) {
                            boolValue = true;
                        } else {
                            boolValue = false;
                        }
                    field.set(initObj, (boolValue));
                } else if (field.getType().equals(double.class)) {
                    int point = (int) (Generate_long() % 18);
                    long value = Generate_long();
                    double result = value * Math.pow(0.1, point);
                    field.set(initObj, result);
                } else if (field.getType().equals(float.class)) {
                    int point = (int) (Generate_long() % 9);
                    int value = (int) Generate_long();
                    float result = (float) (value * Math.pow(0.1, point));
                    field.set(initObj, result);
                } else if (field.getType().equals(short.class)) {
                    field.set(initObj, (short) (Generate_long() % Short.MAX_VALUE));
                    }
                 else if (field.getType().equals(byte.class)) {
                    field.set(initObj, (byte) (Generate_long() % Byte.MAX_VALUE));
                    }
                 else if (field.getType().equals(char.class)) {
                    field.set(initObj, (char) (Generate_long() % 65535));
                    }
                }
            }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        }


    }


