package cc.lzy.spring.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * class function desc
 *
 * @author taigai
 * @date 2021/11/14 10:10 AM
 */
public class BeanUtilsTest {
    public static void main(String[] args) throws Exception {
        A a = new A();
        a.setAge(1001);
        BeanInfo beanInfo = Introspector.getBeanInfo(A.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : propertyDescriptors) {
            String name = pd.getName();

            Method readMethod = pd.getReadMethod();
            Object ret = readMethod.invoke(a);
            System.out.println("read ret=" + ret);


            Method writeMethod = pd.getWriteMethod();
            Object write = writeMethod.invoke(a, 1002);
            System.out.println("write ret=" + write);

            int age = a.getAge();

            System.out.println("");

        }

    }

    public static class A {
        private int age;

        /**
         * Getter method for property <tt>age</tt>.
         *
         * @return property value of age
         */
        public int getAge() {
            return age;
        }

        /**
         * Setter method for property <tt>age</tt>.
         *
         * @param age value to be assigned to property age
         */
        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "A{" +
                "age=" + age +
                '}';
        }
    }

    public static class B {
        private Integer age;

        /**
         * Getter method for property <tt>age</tt>.
         *
         * @return property value of age
         */
        public Integer getAge() {
            return age;
        }

        /**
         * Setter method for property <tt>age</tt>.
         *
         * @param age value to be assigned to property age
         */
        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "B{" +
                "age=" + age +
                '}';
        }
    }
}