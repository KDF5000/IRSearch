package org.ucas.document;

import java.util.Properties;

/**
 * Created by devin on 15-12-16.
 */
public class PostIndexItem {
    private long docId; //文档id
    private int tf; //词项频率

    public PostIndexItem(){

    }

    public PostIndexItem(long docId, int tf){
        this.docId = docId;
        this.tf = tf;
    }

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

}
