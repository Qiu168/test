package org.example;

import java.io.*;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author 14629
 */
public class Input {

    public void input(){
        while(true) {
            System.out.println("文件路径名示例：C:\\a\\b\\c.txt");
            System.out.println("输入想要复制的文件的地址要写文件名及其后缀");
            Scanner scanner=new Scanner(System.in);
            String inputPath=scanner.next();
            System.out.println("输入想要下载到的地址，要写下载后的文件的文件名及其后缀");
            String  outputPath= scanner.next();
            String regex="^([a-zA-Z]:)\\\\([^\\\\]+\\\\)*[^/:*?'\"<>|]+\\.[a-zA-Z]+$";
            //C:\\PotPlayer\AviSynth\sd.asd
            if (Pattern.matches(regex,inputPath)&&Pattern.matches(regex,outputPath)){
                try {
                    readFile(inputPath,outputPath);
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("输入文件地址格式错误！请重新输入");
            }
        }
    }

    public void readFile(String inputPath,String outputPath) throws IOException {
        File file=new File(outputPath);
        boolean newFile = file.createNewFile();
        if(!newFile){
            System.out.println("文件创建失败！");
            return;
        }
        long start=System.currentTimeMillis();
        BufferedInputStream bis;
        BufferedOutputStream bos;
        bis=new BufferedInputStream(new FileInputStream(inputPath));
        bos=new BufferedOutputStream(new FileOutputStream(outputPath));
        byte[] bytes=new byte[1024];
        int len;
        while((len=bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
        }
        bis.close();
        bos.close();
        long end=System.currentTimeMillis();
        System.out.println("一共用时"+(end-start)+"毫秒");
        //mn
    }
}
