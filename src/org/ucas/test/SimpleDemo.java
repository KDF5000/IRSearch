package org.ucas.test;

import java.io.*;

/**
 * Created by devin on 15-12-14.
 */
public class SimpleDemo {

    public static void readFile(){
        FileInputStream fis = null;
        try{
            //创建流对象
            fis = new FileInputStream("src/org/ucas/test/demo.txt");
            //读取数据到数组中
            byte[] data = new byte[1204];// 数据存储的数组
            int i = 0;//当前小标
            //读取流中的第一个字节数据
            int n = fis.read();
//            fis.read(data);
//            fis.read(data,1,2);
            //依次读取后续的数据
            while (n!=-1){
                data[i] = (byte)n;
                i++;
                n = fis.read(); //读一个字节
            }
            //解析数据
            String s = new String(data,0,i);
            System.out.println(s);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                fis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void writeFile(){
        FileOutputStream fos = null;
        String s = "123";
        int n = 100;
        try{
            fos = new FileOutputStream("out.txt");
            byte[] b1 = s.getBytes();
            byte[] b2 = "\r\n".getBytes();
            byte[] b3 = String.valueOf(n).getBytes();
            fos.write(b1);
            fos.write(b2);
            fos.write(b3);
            long a = 212;
            int high = (int)(a >> 32);
            fos.write(4);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void writeLong(){
        long a = 1233131;
        System.out.println((int)(a & 0xffffffff ));
        FileOutputStream fo = null;
        try{
            fo = new FileOutputStream("dd.pli");
            fo.write(112);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fo!=null){
                try{
                    fo.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    public static void writeBinary(){
        DataOutputStream fo = null;
        try{
            fo = new DataOutputStream(new FileOutputStream("binary.bi"));
            fo.write("你是我".getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fo!=null){
                try{
                    fo.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static void readBinary(){
        RandomAccessFile fi = null;
        DataInputStream di = null;
        try{
            fi = new RandomAccessFile("binary.bi", "r");
            fi.seek(3);
            byte[] demo = new byte[3];
            fi.read(demo, 0, 3);
            System.out.println(new String(demo));
            fi.seek(6);
            fi.read(demo, 0, 3);
            System.out.println(new String(demo));
//            di = new DataInputStream(new FileInputStream("binary.bi"));
//            di.skipBytes(3);
//            di.read(demo, 0, 6);
//            System.out.println(new String(demo));

        }catch (Exception E){
            E.printStackTrace();
        }
    }


    public static void main(String []args){
//        SimpleDemo.readFile();
//        SimpleDemo.writeFile();

        SimpleDemo.writeBinary();

        SimpleDemo.readBinary();
    }
}
