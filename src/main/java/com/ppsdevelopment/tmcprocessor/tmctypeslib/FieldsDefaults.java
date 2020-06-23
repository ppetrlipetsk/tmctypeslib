package com.ppsdevelopment.tmcprocessor.tmctypeslib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

/**
 * Класс, хранящий предопределенные значения типов полей таблицы. Например, id должен быть только int.
 * Чтобы не получилось иначе, это значение задается в данном классе.
 * Данные о предопределенных значениях полей загржаются из внешнего файла.
 */
public class FieldsDefaults {

    private static  HashMap<String, FieldType> fields;

    static {
        fields =new HashMap<>();
    }

    public static HashMap<String, FieldType> getFields() {
        return fields;
    }

    public static void setFields(HashMap<String, FieldType> fields) {
        FieldsDefaults.fields = fields;
    }

    public static boolean isFieldExists(String fieldName){
        boolean b=false;
        //TableFieldsRepositoryx fields=tableFields.get(tableName);
        if (fields!=null) b=fields.containsKey(fieldName);
        return b;
    }

    public static FieldType getFieldType(String fieldName){
        boolean b=false;
        if (isFieldExists(fieldName))
        return fields.get(fieldName);
        return null;
    }


    private static Properties getProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(fileName));
        return properties;
    }



    public static boolean loadDefaultFields(String fileName, String tableName) throws IOException {
        Properties properties =getProperties(fileName);
        if (fields==null) fields=new HashMap<>();
        Set<?> keys=properties.keySet();
        boolean ret=true;

        for (Object key:keys){
            String keyName=(String) key;

            if (!properties.containsKey(key)) throw new NoSuchElementException("Неверный ключ! Key="+keyName);

            String value=(String)properties.getProperty(keyName);

            if (checkFieldType(value)) {
                fields.put(keyName, FieldType.valueOf(value));
            }
        }
        return ret;
    }

    private static boolean checkFieldType(String value) {
        boolean res;
        try {
            FieldType f = FieldType.valueOf(value);
            res=true;
        }
        catch (IllegalArgumentException e){
            res=false;
        }
        return res;
    }

}




