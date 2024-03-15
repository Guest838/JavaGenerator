package alex_java;

import java.lang.reflect.Field;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import static java.lang.Math.pow;

public class Generator {
    private static long jvmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
    private static long m = (long) Math.pow(2, 31);
    private static long a = 214013;
    private static long c = 2531011;

    public static long Generate_long() {
        long rand = (a * jvmStartTime + c) % m;
        jvmStartTime = rand;
        return rand;
    }

    public static <T> void Generate(T init_obj) {
        Class<?> cl = init_obj.getClass();
        Field[] Decfields = cl.getDeclaredFields();
        for (Field x : Decfields) {
            x.setAccessible(true);
            if (x.getType().equals(String.class)) {
                x.setAccessible(true);
                short string_length = (short) (Generate_long() % Short.MAX_VALUE);
                char[] str = new char[string_length];
                for (short i = 0; i < string_length; i++) {
                    str[i] = (char) (Generate_long() % 65535);
                }
                try {
                    x.set(init_obj, new String(str));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            } else if (x.getType().equals(int.class)) {
                try {
                    x.set(init_obj, (int) (Generate_long() % Integer.MAX_VALUE));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            } else if (x.getType().equals(long.class)) {
                try {
                    x.set(init_obj, (Generate_long()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }


            }
            else if (x.getType().equals(boolean.class))
            { int generatedValue=(int)Generate_long() % 2;
                boolean boolValue;
                try {
                    if ( generatedValue>= 1) {
                        boolValue = true;
                    }
                    else {
                        boolValue = false;
                    }
                    x.set(init_obj, (boolValue));

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            //x.set(init_obj, (String) "new value");
        }


    }

}
