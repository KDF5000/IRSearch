package org.ucas.document;

import java.util.ArrayList;

/**
 * Created by devin on 15-12-11.
 */
public class Document {
    private long docID; //文档id，由于现在数据源只有数据库，因此此id为数据库主键id
    private ArrayList<Field> fields = null; //文档的域，比如标题，时间，内容

    public Document(){
        fields = new ArrayList<Field>();
    }

    public long getDocID() {
        return docID;
    }

    public void setDocID(long docID) {
        this.docID = docID;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public void addField(Field field){
        if(field == null){
            return ;
        }
        if(this.fields == null){
            this.fields = new ArrayList<Field>();
        }
        this.fields.add(field);
    }


}
