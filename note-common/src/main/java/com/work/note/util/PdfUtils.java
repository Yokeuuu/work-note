package com.work.note.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName PdfUtils
 * @Description
 * @Author LiuZhao
 * @Date 2021/7/5 15:48
 * @Version 1.0
 **/
public class PdfUtils {

    /**
     * @description word转pdf
     * @param word
     * @return void
     * @author LiuZhao
     * @date 2021/7/5 16:00
     **/
    public static void word2Pdf(File word){
        Writer writer = null;
        PDDocument doc = null;
        try {
            doc = PDDocument.load(word);
            int pagenumber = doc.getNumberOfPages();
            String pdfFile = word.getAbsolutePath().substring(0, word.getAbsolutePath().lastIndexOf("."));
            String fileName = pdfFile + ".doc";
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(fileName);
            writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            PDFTextStripper stripper = new PDFTextStripper();
            // 排序
            stripper.setSortByPosition(true);
            // 设置转换的开始页
            stripper.setStartPage(1);
            // 设置转换的结束页
            stripper.setEndPage(pagenumber);
            stripper.writeText(doc, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(doc!=null){
                try {
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
