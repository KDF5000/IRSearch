package org.ucas.analysis;

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
    public HashMap<String, Integer> countWords(String str){
        HashMap<String, Integer> countHash = new HashMap<String, Integer>();
        String []wordsList = str.split("\\b");
        for(int i=0; i< wordsList.length; i++){
            if(!countHash.containsKey(wordsList[i])){
                countHash.put(wordsList[i], 1);
            }else{
                countHash.put(wordsList[i], countHash.get(wordsList[i])+1);
            }
        }
        return countHash;
    }

}
