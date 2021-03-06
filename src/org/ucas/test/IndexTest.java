package org.ucas.test;

import org.ucas.analysis.Analysis;
import org.ucas.document.Document;
import org.ucas.document.Field;
import org.ucas.index.IndexWriter;

/**
 * Created by devin on 15-12-12.
 */
public class IndexTest {
    public static void main(String []args){
        Analysis analysis = new Analysis();
        IndexWriter indexWriter = new IndexWriter(analysis);
        Document document = new Document();

        String sInput = "东方网12月4日消息:2009年10月21日,辽宁省阜新市委\n" +
                "收到举报信,举报以付玉红为首吸毒、强奸、聚众淫乱,阜新市委政法委副书记于洋等参与\n" +
                "吸毒、强奸、聚众淫乱等。对此,阜新市委高度重视,责成阜新市公安局立即成立调查组,抽\n" +
                "调精干力量展开调查。\n" +
                "调查期间,署名举报人上官宏祥又通过尹东方(女)向阜新市公安\n" +
                "局刑警支队提供书面举报,举报于洋等参与吸毒、强奸、聚众淫乱。11月19日,正义网发表\n" +
                "上官宏祥接受记者专访,再次实名举报于洋等参与吸毒、强奸、聚众淫乱,引起网民广泛关\n" +
                "注。对此辽宁省政法委、省公安厅高度重视。当日,责成有关领导专程赴阜新听取案件调查\n" +
                "情况。为加强对案件的督办和指导,省有关部门迅速成立工作组,赴阜新督办、指导案件调\n" +
                "查工作,并将情况上报有关部门。";
        sInput = sInput.replaceAll("\\s", "");
        System.out.println(sInput.replaceAll("\\s", ""));
        document.addField(new Field("content", sInput));
        document.addField(new Field("time", System.nanoTime()));
        document.setDocID(12);
        indexWriter.addDocument(document);
    }
}
