package org.ucas.analysis;

import org.ucas.document.PostIndexItem;
import org.ucas.utils.NLPUtils;

import java.util.HashMap;

/**
 * 分析处理
 * Created by devin on 15-12-11.
 */
public class Analysis{
    private NLPUtils nlpUtils;

    public Analysis(){
        nlpUtils = NLPUtils.getInstance();
        nlpUtils.init();
    }

    /**
     * 对字符串进行分词
     * @param str
     * @return
     */
    public String processParagraph(String str){
        return nlpUtils.paragraphProcess(str, 0);//没有词性标注
    }

    /**
     * 统计字符串中单词出现的次数
     * @param str 分好词已空格隔开的string
     * @return HashMap(word,count)
     */
    public HashMap<String, PostIndexItem> countWords(String str, long docId){
        HashMap<String, PostIndexItem> countHash = new HashMap<String, PostIndexItem>();
        String []wordsList = str.split("\\b");
        for(int i=0; i< wordsList.length; i++){
            if(!countHash.containsKey(wordsList[i])){
                PostIndexItem item = new PostIndexItem(docId, 1);
                countHash.put(wordsList[i], item);
            }else{
                PostIndexItem item = countHash.get(wordsList[i]);
                int tf = item.getTf();
                item.setTf(tf+1);
                countHash.put(wordsList[i], item);
            }
        }
        return countHash;
    }

}
