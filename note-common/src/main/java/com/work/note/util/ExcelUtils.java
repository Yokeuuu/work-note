package com.work.note.util;
//
///**
// * @ClassName ExcelUtil
// * @Deacription TODO
// * @Author rayeye
// * @Date 2021/3/26 15:29
// * @Version 1.0
// **/
//public class ExcelUtil {
//    public static void exportExcel(){
//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet = wb.createSheet();
//        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//
//        BufferedImage bufferImg = ImageIO.read(new File("D://fruit.PNG"));
//        ImageIO.write(bufferImg,"PNG",byteArrayOut);
//
//        HSSFClientAnchor anchor =
//                new HSSFClientAnchor(5,0,500,122,(short) 0, 5,(short)10,15);
//        HSSFPatriarch patri = sheet.createDrawingPatriarch();
//        patri.createPicture(anchor ,
//                wb.addPicture(byteArrayOut.toByteArray(),
//                        HSSFWorkbook.PICTURE_TYPE_PNG));
//
//        ByteArrayOutputStream  outStream = new ByteArrayOutputStream();
//        wb.write(outStream);
//    }
//}
