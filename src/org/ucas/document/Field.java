package org.ucas.document;

import java.util.Objects;

/**
 * Created by devin on 15-12-12.
 */
public class Field {
    private String fieldName; //域名
    private Object fieldData; //域值

    public Field(String name, Object data){
        this.fieldName = name;
        this.fieldData = data;
    }
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldData() {
        return fieldData;
    }

    public void setFieldData(Object fieldData) {
        this.fieldData = fieldData;
    }
}