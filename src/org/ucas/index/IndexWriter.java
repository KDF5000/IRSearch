package org.ucas.index;

import com.sun.deploy.util.ArrayUtil;
import javafx.geometry.Pos;
import org.ucas.analysis.Analysis;
import org.ucas.document.Document;
import org.ucas.document.Field;
import org.ucas.document.PostIndexItem;
import org.ucas.utils.ArrayUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * 索引建立
 * Created by devin on 15-12-11.
 */
public class IndexWriter {

    //分析器，可以分词，统计词的，去停用词等功能
    private Analysis analysisProcess = null;
    private HashMap<String,PostIndexItem> tokenStream;

    public IndexWriter(Analysis analysis){
        this.analysisProcess = analysis;
        this.tokenStream = new HashMap<String, PostIndexItem>();
    }

    /**
     * 添加一个document进行索引，可以用多线程
     * @param document
     * @return
     */
    public boolean addDocument(Document document){
        //简单测试策略，将文档的各个域合并然后进行索引
        ArrayList<Field> docFileds = document.getFields();
        long docId = document.getDocID();//文档id
        String indexContent = "";
        for(Iterator<Field> it = docFileds.iterator();it.hasNext();){
            Field field = it.next();
            indexContent += field.getFieldData().toString();
        }
        System.out.println(indexContent);
        String tmp = this.analysisProcess.processParagraph(indexContent);
//        System.out.println(tmp);
        coutWords(tmp, docId);
//        System.out.println(tokenStream.toString());

        return true;
    }

    /**
     * 统计分词结果中的词对
     * @param stream
     * @param docId
     */
    private void coutWords(String stream, long docId){
        String []wordsList = stream.split("\\b");
        for(int i=0; i< wordsList.length; i++){
            if(!tokenStream.containsKey(wordsList[i])){
                PostIndexItem item = new PostIndexItem(docId, 1);
                tokenStream.put(wordsList[i], item);
            }else{
                PostIndexItem item = tokenStream.get(wordsList[i]);
                int tf = item.getTf();
                item.setTf(tf+1);
                tokenStream.put(wordsList[i], item);
            }
        }
        spimi();
    }

    /**
     *
     */
    private void spimi(){
        HashMap<String, ArrayList<PostIndexItem>> dicPost = new HashMap<String, ArrayList<PostIndexItem>>();
        Iterator iter = tokenStream.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry obj = (Map.Entry)iter.next();
            String key = (String)obj.getKey();
            PostIndexItem item = (PostIndexItem)obj.getValue();
            if(dicPost.containsKey(key)){
                ArrayList<PostIndexItem> list = dicPost.get(key);
                ArrayUtils.InsertArray(list, item);
                dicPost.put(key, list);
            }else{
                ArrayList<PostIndexItem> list = new ArrayList<PostIndexItem>();
                list.add(item);
                dicPost.put(key, list);
            }
        }
        write2Disk(dicPost);
    }

    /**
     * 创建索引
     */
    private void write2Disk(HashMap<String, ArrayList<PostIndexItem>> dicPost){
        DataOutputStream fDictionary = null;
        DataOutputStream fPostList = null;
        DataOutputStream fDPMap = null;
        try{
            fDictionary = new DataOutputStream(new FileOutputStream("dic_1.dic"));
            fPostList = new DataOutputStream(new FileOutputStream("pl_1.pli"));
            fDPMap = new DataOutputStream(new FileOutputStream("dpm_1.dpm"));
            Object[] keySet = dicPost.keySet().toArray();
            Arrays.sort(keySet);
            int dicPos = 0;
            int pliPos = 0;
            int dpmPos = 0;
            for(int i=0; i < keySet.length; i++){
                String key = (String)keySet[i];
//                System.out.println(key);
                int keyLen = key.getBytes().length;
//                fDictionary.write(keyLen); //int 4个字节
                //写词典
                fDictionary.writeBytes(key);
                //写倒排表
                ArrayList<PostIndexItem> postList = dicPost.get(key);
                for(int j=0; j < postList.size(); j++){
                    long id = postList.get(j).getDocId();//8
                    int tf = postList.get(j).getTf();//4
//                    fPostList.writeInt((int)(id >> 32)); //high
//                    fPostList.write((int)(id & 0xffffffff));//low
                    fPostList.writeLong(id);
                    fPostList.writeInt(tf);
                }
                //写映射
                fDPMap.writeInt(dicPos); //4
                fDPMap.writeInt(postList.size());//4
                fDPMap.writeInt(pliPos);//4
                System.out.println(key+":"+dicPos+","+postList.size()+","+pliPos);

                dicPos += keyLen;
                pliPos += 12 * postList.size();
                dpmPos += 12;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                fDictionary.close();
                fPostList.close();
                fDPMap.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
