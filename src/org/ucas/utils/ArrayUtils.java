package org.ucas.utils;

import org.ucas.document.PostIndexItem;

import java.util.ArrayList;

/**
 * Created by devin on 15-12-16.
 */
public class ArrayUtils {
    /**
     * 递增顺序插入倒排表 --- 可以优化,使用泛型
     * @param arrayList
     * @param data
     */
    public static void InsertArray(ArrayList<PostIndexItem> arrayList, PostIndexItem data){
        if(arrayList == null){
            return;
        }
        arrayList.add(data);
        int i = arrayList.size()-2;
        while(i >= 0){
            if(data.getDocId() < arrayList.get(i).getDocId()){
                arrayList.set(i+1, arrayList.get(i));
                i--;
            }else{
                break;
            }
        }
        arrayList.set(i+1, data);
    }
}
