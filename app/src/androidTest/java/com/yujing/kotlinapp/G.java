package com.yujing.kotlinapp;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class G<T> extends F<T> {
    //代表的是某个实体的类型
    public Class<T> entityClass;
    public void G2(T t) {

    }
    public G() {
        //getClass()获得一个实例的类型类;相当于 某类.class eg:UserDao.class
        Class class1 = getClass();
        System.out.println("★1:" + class1);

        //getSuperclass()获得该类的父类
        Class superclass = class1.getSuperclass();
        System.out.println("★2:" + superclass);

        //getGenericSuperclass()获得带有泛型的父类
        //Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型
        Type type = class1.getGenericSuperclass();
        System.out.println("★3:" + type);

        if (type instanceof ParameterizedType) {
            //ParameterizedType参数化类型，即泛型
            ParameterizedType p = (ParameterizedType) type;
            //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
            Type type2 = p.getActualTypeArguments()[0];
            System.out.println("★4:" + type2);

            for (Type item : p.getActualTypeArguments()) {
                System.out.println("★5:" + item);
            }
        }
    }
    public void m() throws Exception {
        getGenericFieldTypes();
        getMethodParameterTypes();
    }

    public List<String> stringList = new ArrayList<>();


    public List<String> getStringList() {
        return this.stringList;
    }
    public void setList(List<Integer> list){
    }

    public void getGenericFieldTypes() throws Exception {
        Field field = getClass().getField("stringList");
        Type genericsFieldType = field.getGenericType();
        if (genericsFieldType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericsFieldType;
            Type[] fieldArgTypes = parameterizedType.getActualTypeArguments();
            for (Type fieldArgType : fieldArgTypes) {
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("泛型字段的类型：" + fieldArgClass);
            }
        }
    }

    public void getMethodParameterTypes() throws Exception {
        Method method = getClass().getMethod("setList", List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type genericType : genericParameterTypes) {
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type[] types = parameterizedType.getActualTypeArguments();
                for (Type type : types) {
                    Class realType = (Class) type;
                    System.out.println("方法参数的类型：" + realType);
                }
            }
        }
    }


    public Type getMethodParameterTypes(Object obj) {
        Type type = null;
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            for (Type genericType : genericParameterTypes) {
                if (genericType instanceof ParameterizedType) {
                    System.out.println("发现包含泛型方法:" + obj.getClass().getName() + "." + method.getName() + "方法参数的类型：" + genericType);
                    type = genericType;
                }
            }
        }
        return type;
    }


}
