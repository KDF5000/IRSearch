package org.ucas.index;

import org.ucas.analysis.Analysis;
import org.ucas.document.Document;
import org.ucas.document.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 索引建立
 * Created by devin on 15-12-11.
 */
public class IndexWriter {

    //分析器，可以分词，统计词的，去停用词等功能
    private Analysis analysisProcess = null;

    public IndexWriter(Analysis analysis){
        this.analysisProcess = analysis;
    }

    /**
     * 添加一个document进行索引，可以用多线程
     * @param document
     * @return
     */
    public boolean addDocument(Document document){
        //简单测试策略，将文档的各个域合并然后进行索引
        ArrayList<Field> docFileds = document.getFields();
        String indexContent = "";
        for(Iterator<Field> it = docFileds.iterator();it.hasNext();){
            Field field = it.next();
            indexContent += field.getFieldData().toString();
        }
        System.out.println(indexContent);
        String tmp = this.analysisProcess.processParagraph(indexContent);
        System.out.println(tmp);
        HashMap<String, Integer> cout = this.analysisProcess.countWords(tmp);
        System.out.println(cout.toString());
        return true;
    }
}
