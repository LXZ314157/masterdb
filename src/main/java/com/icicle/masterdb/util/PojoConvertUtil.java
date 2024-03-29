package com.icicle.masterdb.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liumingming
 * Created by liumingming on 2017-10-25.
 */
public class PojoConvertUtil {

    /**
     * 变量缓存
     */
    private static final Map<String, Map<String, Field>> CACHE_FIELDS = new ConcurrentHashMap<>();

    private static final Set<Class> BASIC_CLASS = new HashSet<>();

    static {
        BASIC_CLASS.add(Integer.class);
        BASIC_CLASS.add(Character.class);
        BASIC_CLASS.add(Byte.class);
        BASIC_CLASS.add(Float.class);
        BASIC_CLASS.add(Double.class);
        BASIC_CLASS.add(Boolean.class);
        BASIC_CLASS.add(Long.class);
        BASIC_CLASS.add(Short.class);
        BASIC_CLASS.add(String.class);
        BASIC_CLASS.add(BigDecimal.class);
    }

    /**
     * 转换list
     *
     * @param origList
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> convertPojoList(List origList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>(origList.size());
        for (Object object : origList) {
            targetList.add(convertPojo(object, targetClass));
        }
        return targetList;
    }

    /**
     * 将具有相同属性的类型进行转换
     *
     * @param obj 原对象
     * @param <T> 新对象
     * @return
     */
    public static <T> T convertPojo(Object obj, Class<T> targetClass) {
        try {
            T target = targetClass.newInstance();
            //获取源对象的所有变量
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (isStatic(field)) {
                    continue;
                }

                //获取目标方法
                Field targetField = getTargetField(targetClass, field.getName());
                if (targetField == null) {
                    continue;
                }
                Object value = getFiledValue(field, obj);
                if (value == null) {
                    continue;
                }

                Class type1 = field.getType();
                Class type2 = targetField.getType();
                //两个类型是否相同
                boolean sameType = type1.equals(type2);
                if (isBasicType(type1)) {
                    if (sameType) {
                        setFieldValue(targetField, target, value);
                    }
                } else if (value instanceof Map && Map.class.isAssignableFrom(type2)) {
                    setMap((Map) value, field, targetField, target);
                } else if (value instanceof Set && Set.class.isAssignableFrom(type2)) {
                    setCollection((Collection) value, field, targetField, target);
                } else if (value instanceof List && List.class.isAssignableFrom(type2)) {
                    setCollection((Collection) value, field, targetField, target);
                } else if (value instanceof Enum && Enum.class.isAssignableFrom(type2)) {
                    setEnum((Enum) value, field, targetField, target);
                } else if (value instanceof java.util.Date &&
                        java.util.Date.class.isAssignableFrom(type2)) {
                    setDate((Date) value, targetField, type2, target, sameType);
                }
            }
            return target;
        } catch (Throwable t) {
            LogUtil.getLogger(PojoConvertUtil.class).error("转换失败:" + t.getMessage());
            throw new RuntimeException(t.getMessage());
        }
    }

    /**
     * 获取适配方法
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Field getTargetField(Class clazz, String fieldName) {
        String classKey = clazz.getName();
        Map<String, Field> fieldMap = CACHE_FIELDS.get(classKey);
        if (fieldMap == null) {
            fieldMap = new HashMap<>(64);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (isStatic(field)) {
                    continue;
                }
                fieldMap.put(field.getName(), field);
            }
            CACHE_FIELDS.put(classKey, fieldMap);
        }
        return fieldMap.get(fieldName);
    }

    /**
     * 确实是否为基础类型
     *
     * @param clazz
     * @return
     */
    public static boolean isBasicType(Class clazz) {
        return clazz.isPrimitive() || BASIC_CLASS.contains(clazz);
    }

    /**
     * 判断变量是否有静态修饰符static
     *
     * @param field
     * @return
     */
    public static boolean isStatic(Field field) {
        return (8 & field.getModifiers()) == 8;
    }

    /**
     * 获取字段值
     *
     * @param field
     * @param obj
     * @return
     */
    private static Object getFiledValue(Field field, Object obj) throws IllegalAccessException {
        //获取原有的访问权限
        boolean access = field.isAccessible();
        try {
            //设置可访问的权限
            field.setAccessible(true);
            return field.get(obj);
        } finally {
            //恢复访问权限
            field.setAccessible(access);
        }
    }

    /**
     * 设置方法值
     *
     * @param field
     * @param obj
     * @param value
     * @throws IllegalAccessException
     */
    private static void setFieldValue(Field field, Object obj, Object value) throws IllegalAccessException {
        //获取原有的访问权限
        boolean access = field.isAccessible();
        try {
            //设置可访问的权限
            field.setAccessible(true);
            field.set(obj, value);
        } finally {
            //恢复访问权限
            field.setAccessible(access);
        }
    }

    /**
     * 设置Map
     *
     * @param value
     * @param origField
     * @param targetField
     * @param targetObject
     * @param <T>
     */
    private static <T> void setMap(Map value, Field origField, Field targetField, T targetObject) throws IllegalAccessException, InstantiationException {
        Type origType = origField.getGenericType();
        Type targetType = targetField.getGenericType();
        int typeLength = 2;
        if (origType instanceof ParameterizedType && targetType instanceof ParameterizedType) {
            ParameterizedType origParameterizedType = (ParameterizedType) origType;
            Type[] origTypes = origParameterizedType.getActualTypeArguments();
            ParameterizedType targetParameterizedType = (ParameterizedType) targetType;
            Type[] targetTypes = targetParameterizedType.getActualTypeArguments();
            if (origTypes != null && origTypes.length == typeLength
                    && targetTypes != null && targetTypes.length == typeLength) {
                Class clazz = (Class) origTypes[1];
                if (!isBasicType(clazz) && !clazz.equals(targetTypes[1])) {
                    Set<Map.Entry> entries = value.entrySet();
                    Map targetMap = value.getClass().newInstance();
                    for (Map.Entry entry : entries) {
                        targetMap.put(entry.getKey(), convertPojo(entry.getValue(), (Class) targetTypes[1]));
                    }
                    setFieldValue(targetField, targetObject, targetMap);
                    return;
                }
            }
        }
        setFieldValue(targetField, targetObject, value);
    }

    /**
     * 设置集合
     *
     * @param value
     * @param origField
     * @param targetField
     * @param targetObject
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static <T> void setCollection(Collection value, Field origField, Field targetField, T targetObject) throws IllegalAccessException, InstantiationException {
        Type origType = origField.getGenericType();
        Type targetType = targetField.getGenericType();
        if (origType instanceof ParameterizedType && targetType instanceof ParameterizedType) {
            ParameterizedType origParameterizedType = (ParameterizedType) origType;
            Type[] origTypes = origParameterizedType.getActualTypeArguments();
            ParameterizedType targetParameterizedType = (ParameterizedType) targetType;
            Type[] targetTypes = targetParameterizedType.getActualTypeArguments();
            if (origTypes != null && origTypes.length == 1 && targetTypes != null && targetTypes.length == 1) {
                Class clazz = (Class) origTypes[0];
                if (!isBasicType(clazz) && !clazz.equals(targetTypes[0])) {
                    Collection collection = value.getClass().newInstance();
                    for (Object obj : value) {
                        collection.add(convertPojo(obj, (Class) targetTypes[0]));
                    }
                    setFieldValue(targetField, targetObject, collection);
                    return;
                }
            }
        }
        setFieldValue(targetField, targetObject, value);
    }

    /**
     * 设置枚举类型
     *
     * @param value
     * @param origField
     * @param targetField
     * @param targetObject
     * @param <T>
     */
    private static <T> void setEnum(Enum value, Field origField, Field targetField, T targetObject) throws Exception {
        if (origField.equals(targetField)) {
            setFieldValue(targetField, targetObject, value);
        } else {
            //枚举类型都具有一个static修饰的valueOf方法
            Method method = targetField.getType().getMethod("valueOf", String.class);
            setFieldValue(targetField, targetObject, method.invoke(null, value.toString()));
        }
    }

    /**
     * 设置日期类型
     *
     * @param value
     * @param targetField
     * @param targetFieldType
     * @param targetObject
     * @param <T>
     */
    private static <T> void setDate(Date value, Field targetField, Class targetFieldType, T targetObject, boolean sameType) throws IllegalAccessException {
        Date date = null;
        if (sameType) {
            date = value;
        } else if (targetFieldType.equals(java.sql.Date.class)) {
            date = new java.sql.Date(value.getTime());
        } else if (targetFieldType.equals(java.util.Date.class)) {
            date = new Date(value.getTime());
        } else if (targetFieldType.equals(java.sql.Timestamp.class)) {
            date = new java.sql.Timestamp(value.getTime());
        }
        setFieldValue(targetField, targetObject, date);
    }

    /**
     * 获取一个类的所有非静态属性
     *
     * @param clazz
     * @return
     */
    public static List<String> getProperties(Class clazz) {
        List<String> list = new ArrayList<>();
        Field[] filedList = clazz.getDeclaredFields();
        if (!isBasicType(clazz) && clazz != null) {
            for (Field filed : filedList) {
                if (!isStatic(filed)) {
                    list.add(filed.getName());
                }
            }
        }
        return list;
    }
}