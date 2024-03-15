package alex_java;

import java.lang.reflect.Field;
import java.lang.management.ManagementFactory;
import java.lang.Math;

public class Generator {
    private static long jvmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
    private static final long m = (long) Math.pow(2, 31);
    private static final long a = 214013;
    private static final long c = 2531011;

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
            { int generatedValue=(int)(Generate_long() % 2);
                boolean boolValue;
                try {
                    if ( generatedValue== 1) {
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
            else if (x.getType().equals(double.class))
            {
                int point= (int)(Generate_long() %18);
                long value= Generate_long();
                double result=value*Math.pow(0.1,point);
                try {
                    x.set(init_obj, result);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (x.getType().equals(float.class))
            {
                int point= (int)(Generate_long() %9);
                int value= (int)Generate_long();
                float result=(float)(value*Math.pow(0.1,point));
                try {
                    x.set(init_obj, result);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
            else if (x.getType().equals(short.class)) {
                try {
                    x.set(init_obj, (short) (Generate_long() % Short.MAX_VALUE));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (x.getType().equals(byte.class)) {
                try {
                    x.set(init_obj, (byte) (Generate_long() % Byte.MAX_VALUE));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (x.getType().equals(char.class)) {
                try {
                    x.set(init_obj, (char) (Generate_long() % 65535));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                //x.set(init_obj, (String) "new value");
            }

        }


    }

}
