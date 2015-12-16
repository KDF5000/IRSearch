package org.ucas.test;

import org.ucas.document.PostIndexItem;
import org.ucas.utils.ArrayUtils;

import java.util.ArrayList;

/**
 * Created by devin on 15-12-16.
 */
public class UtilsTest {
    public static void main(String []args){
        PostIndexItem item1 = new PostIndexItem(5, 1);
        PostIndexItem item2 = new PostIndexItem(1, 1);
        PostIndexItem item3 = new PostIndexItem(4, 1);
        PostIndexItem item4 = new PostIndexItem(3, 1);
        PostIndexItem item5 = new PostIndexItem(0, 1);

        ArrayList<PostIndexItem> list = new ArrayList<PostIndexItem>();
        ArrayUtils.InsertArray(list, item1);
        ArrayUtils.InsertArray(list, item2);
        ArrayUtils.InsertArray(list, item3);
        ArrayUtils.InsertArray(list, item4);
        ArrayUtils.InsertArray(list, item5);
        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i).getDocId());
        }

    }
}
