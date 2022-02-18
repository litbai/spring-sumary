package cc.lzy.spring.concurrent;

import java.lang.reflect.Field;

/**
 * class function desc
 *
 * @author taigai
 * @date 2021/12/22 5:01 PM
 */
public class ClassUtils {


    private ClassUtils() {}

    /**
     * 获取声明的成员属性值，包括父类声明的属性
     *
     * @param fieldName 属性名
     * @param target    对象
     * @param <T>       泛型
     * @return 属性值，如果没有属性，返回null
     */
    public static <T> T getFieldVal(String fieldName, Object target) {
        Class<?> klass = target.getClass();
        while (klass != null) {
            try {
                Field f = klass.getDeclaredField(fieldName);
                f.setAccessible(true);
                return (T)f.get(target);
            } catch (Exception e) {
                klass = klass.getSuperclass();
            }
        }
        return null;
    }
}