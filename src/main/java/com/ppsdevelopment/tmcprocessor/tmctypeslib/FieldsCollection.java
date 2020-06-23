package com.ppsdevelopment.tmcprocessor.tmctypeslib;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс содержит коллекцию набора полей таблицы БД, типа FieldRecord.
 */
public class FieldsCollection {

    private LinkedHashMap<String, FieldRecord> fields;

    public FieldsCollection(LinkedHashMap<String, FieldRecord> fields) {
        this.fields = fields;
    }

    public FieldsCollection(int i, float v, boolean b) {
        fields=new LinkedHashMap<>(i,v,b);
    }

    public LinkedHashMap<String, FieldRecord> getFields() {
            return fields;
    }

    public void setFields(LinkedHashMap<String, FieldRecord> fields) {
        this.fields = fields;
    }

    public void put(String key, FieldRecord field){
        if ((fields!=null)) fields.put(key,field);
    }

    public FieldRecord get(String key){
        if ((fields!=null)&&(fields.containsKey(key))) return fields.get(key);
        else
            return null;
    }
    public int size(){
        if (fields!=null) return fields.size();
        else
            return -1;
    }

    public Iterator<Map.Entry<String, FieldRecord>> getIterator(){
        if (fields!=null) return fields.entrySet().iterator();
        else
            return null;
    }

    public boolean containsKey(String key){
        if (fields!=null) return fields.containsKey(key);
        else
            return false;
    }


}
