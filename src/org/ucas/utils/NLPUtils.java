package org.ucas.utils;

import com.sun.jna.Native;

/**
 * Created by devin on 15-12-11.
 */
public class NLPUtils{
    private String argu = "";
    // String system_charset = "GBK";//GBK----0
    private String system_charset = "GBK";
    //定义并初始化接口的静态变量 这一个语句是来加载 dll 的,
    //注意 dll 文件的路径可以是绝对路径也可以是相对路径,只需要填写 dll 的文件名,不能加后缀
    private NLPLibrary nlpLibrary = (NLPLibrary) Native.loadLibrary("bin/libNLPIR.so", NLPLibrary.class);

    private int charset_type = 1;
    // int charset_type = 0;
    // 调用printf打印信息

    private volatile static NLPUtils instance = null;

    private NLPUtils(){}

    public static NLPUtils getInstance(){
        if(instance == null){
            synchronized (NLPUtils.class){
                if(instance == null){
                    instance = new NLPUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化
     */

    public void init(){
        int init_flag = this.nlpLibrary.NLPIR_Init(argu, charset_type, "0");
        if (0 == init_flag) {
            System.err.println("初始化失败!");
            return;
        }
    }

    /**
     * 对字符串进行分词
     * @param sSrc
     * @param bPOSTagged
     * @return String
     */
    public String paragraphProcess(String sSrc, int bPOSTagged){
        return this.nlpLibrary.NLPIR_ParagraphProcess(sSrc, bPOSTagged);
    }

    /**
     * 对TXT文件内容进行分词
     * @param sSourceFilename
     * @param sResultFilename
     * @param bPOStagged
     * @return double
     */
    public double fileProcess(String sSourceFilename,String sResultFilename, int bPOStagged){
        return this.nlpLibrary.NLPIR_FileProcess(sSourceFilename, sResultFilename, bPOStagged);
    }

    /**
     * 从字符串中提取关键词
     * @param sLine
     * @param nMaxKeyLimit
     * @param bWeightOut
     * @return String
     */
    public String getKeyWords(String sLine, int nMaxKeyLimit,boolean bWeightOut){
        return this.nlpLibrary.NLPIR_GetKeyWords(sLine, nMaxKeyLimit, bWeightOut);
    }

    /**
     * 从TXT文件中提取关键词
     * @param sLine
     * @param nMaxKeyLimit
     * @param bWeightOut
     * @return String
     */
    public String getFileKeyWords(String sLine, int nMaxKeyLimit,boolean bWeightOut){
        return this.nlpLibrary.NLPIR_GetFileKeyWords(sLine, nMaxKeyLimit, bWeightOut);
    }

    /**
     * 添加单条用户词典
     * @param sWord
     * @return int
     */
    public int addUserWord(String sWord){
        return this.nlpLibrary.NLPIR_AddUserWord(sWord);
    }

    /**
     * 删除单条用户词典
     * @param sWord
     * @return int
     */
    public int delUsrWord(String sWord){
        return this.nlpLibrary.NLPIR_DelUsrWord(sWord);
    }

    /**
     * 从TXT文件中导入用户词典
     * @param sFilename
     * @return int
     */
    public int importUserDict(String sFilename){
        return this.nlpLibrary.NLPIR_ImportUserDict(sFilename);
    }


    /**
     * 将用户词典保存至硬盘
     * @return int
     */
    public int saveTheUsrDic(){
        return this.nlpLibrary.NLPIR_SaveTheUsrDic();
    }

    /**
     * 从字符串中获取新词
     * @param sLine
     * @param nMaxKeyLimit
     * @param bWeightOut
     * @return String
     */
    public String getNewWords(String sLine, int nMaxKeyLimit, boolean bWeightOut){
        return this.nlpLibrary.NLPIR_GetKeyWords(sLine, nMaxKeyLimit, bWeightOut);
    }

    /**
     * 从TXT文件中获取新词
     * @param sTextFile
     * @param nMaxKeyLimit
     * @param bWeightOut
     * @return String
     */
    public String getFileNewWords(String sTextFile,int nMaxKeyLimit, boolean bWeightOut){
        return this.nlpLibrary.NLPIR_GetFileNewWords(sTextFile, nMaxKeyLimit, bWeightOut);
    }

    /**
     * 获取一个字符串的指纹值
     * @param sLine
     * @return long
     */
    public long fingerPrint(String sLine){
        return this.nlpLibrary.NLPIR_FingerPrint(sLine);
    }

    /**
     * 设置要使用的POS map
     * @param nPOSmap
     * @return int
     */
    public int setPOSmap(int nPOSmap){
        return this.nlpLibrary.NLPIR_SetPOSmap(nPOSmap);
    }

    /**
     * 获取报错日志
     * @return String
     */
    public String getLastErrorMsg(){
        return this.nlpLibrary.NLPIR_GetLastErrorMsg();
    }

    /**
     * 退出
     */
    public void exit(){
        this.nlpLibrary.NLPIR_Exit();
    }

}
