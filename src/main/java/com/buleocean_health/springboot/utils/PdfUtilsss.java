package com.buleocean_health.springboot.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtilsss {
	public static void main(String[] args)throws Exception{
//		tablePdf();
		b();
	}
	
	/**
	 * table布局方式
	 */
	public static void tablePdf() throws Exception{
		 Document document = new Document(PageSize.A4); 
         PdfWriter.getInstance(document, new FileOutputStream("C:/Users/12240/Desktop/test.pdf"));
       //设置字体
       BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);   
       com.itextpdf.text.Font FontChinese24 = new com.itextpdf.text.Font(bfChinese, 24, com.itextpdf.text.Font.BOLD);
       com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese, 18, com.itextpdf.text.Font.BOLD); 
       com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.BOLD);
       com.itextpdf.text.Font FontChinese12 = new com.itextpdf.text.Font(bfChinese, 12, com.itextpdf.text.Font.NORMAL);
       com.itextpdf.text.Font FontChinese11Bold = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.BOLD);
       com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
       com.itextpdf.text.Font FontChinese11Normal = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.NORMAL); 
       document.open();
       // table1 创建1列的表
       PdfPTable table1 = new PdfPTable(1);
       PdfPCell cell11 = new PdfPCell(new Paragraph("护理员档案", FontChinese18));
       cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
       cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
       cell11.setBorder(0);
       table1.addCell(cell11);
       document.add(table1);
       // 加入划线
       document.add(new Paragraph(18f, "----------------------------------------------------------------------------------------------------------------------------------"));
       
       // 添加下一页
       document.newPage();
       document.add(new Paragraph("second page"));
       
       // 添加下一页
       document.newPage();
       document.add(new Paragraph("third page"));
       
//       PdfFormField
//       PdfAcroForm
       
       // 加入空行
       //table3
//         PdfPTable table3 = new PdfPTable(3);
//         int width3[] = {40,35,25};
//         table3.setWidths(width3); 
//         PdfPCell cell31 = new PdfPCell(new Paragrah("申请人：XXX", FontChinese18));
//         PdfPCell cell32 = new PdfPCell(new Paragraph("日期：2011-11-11"));
//         PdfPCell cell33 = new PdfPCell(new Paragraph("报销单号：123456789"));
//         cell31.setBorder(0);
//         cell32.setBorder(0);
//         cell33.setBorder(0);
//         table3.addCell(cell31);
//         table3.addCell(cell32);
//         table3.addCell(cell33);
//         document.add(table3);
//         //加入空行
//         Paragraph blankRow31 = new Paragraph(18f, " "); 
//         document.add(blankRow31);
         document.close();
         
	}
	
	/**
	 * 842×595
	 * PageSize.A4.rotate()
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void a() throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("C:/Users/12240/Desktop/test.pdf"));
		// 设置字体
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		document.open();
		PdfContentByte cb = writer.getDirectContent();
		cb.beginText();
		cb.setFontAndSize(bf, 12);
		cb.setTextMatrix(0, 830);
		cb.showText("Text at position 0,830.");
		cb.endText();
		
		PdfContentByte cb1 = writer.getDirectContent();
		cb1.beginText();
		cb1.setFontAndSize(bf, 12);
		cb1.setTextMatrix(585, 100);
		cb1.showText("Text at position 585,100.");
		cb1.endText();
		
		document.close();
	}
	
	/**
	 *	坐标轴
	 * @throws Exception
	 */
	public static void b() throws Exception {
		Document document = new Document(PageSize.A4); // 默认为72px的大小， 595*842   2480*3508
		PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("C:/Users/12240/Desktop/test.pdf"));
		// 设置字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//		BaseFont bfChinese = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
      
		document.open();
		// 第一行
		PdfContentByte cb = writer.getDirectContent();
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(240, 780);
		cb.showText("护理员档案");
		cb.endText();
		
		// 第一行
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(250, 750);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(260, 720);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(270, 690);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(280, 660);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(290, 630);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(300, 600);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(310, 570);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(320, 540);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(330, 510);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(340, 480);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(370, 470);
		cb.showText("护理员档案");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(390, 460);
		cb.showText("390");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(410, 450);
		cb.showText("410");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(420, 440);
		cb.showText("420");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(440, 430);
		cb.showText("440");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(500, 420);
		cb.showText("500");
		cb.endText();
		
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		cb.setTextMatrix(590, 420);
		cb.showText("600");
		cb.endText();
		
//		// 第二行
//		PdfContentByte cb1 = writer.getDirectContent();
//		cb1.beginText();
//		cb.setFontAndSize(bfChinese, 12);
//		cb1.setTextMatrix(289, 760);
//		cb1.showText("------------------------------------------------------------------------------------------------------------------");
//		cb1.endText();
		
//		// 第三行
//		PdfContentByte cb2 = writer.getDirectContent();
//		cb2.beginText();
//		cb2.setFontAndSize(bfChinese, 10);
//		cb2.setTextMatrix(40, 740);
//		cb2.showText("姓名：李时珍");
//		cb2.endText();
//		
//		PdfContentByte cb3 = writer.getDirectContent();
//		cb3.beginText();
//		cb3.setFontAndSize(bfChinese, 10);
//		cb3.setTextMatrix(165, 740);
//		cb3.showText("性别：男");
//		cb3.endText();
//		
//		PdfContentByte cb4 = writer.getDirectContent();
//		cb4.beginText();
//		cb4.setFontAndSize(bfChinese, 10);
//		cb4.setTextMatrix(290, 740);
//		cb4.showText("入职日期：2017-01-01");
//		cb4.endText();
//		
//		// 头像
////		Image jpg = Image.getInstance("D:/图片/头像.png");//读取图片
//		Image jpg = Image.getInstance("D:/图片/健康证2.png");//读取图片
//		jpg.scaleToFit(100, 120);//转换大小  宽度剩下 120 -- 高度130
//		jpg.setAbsolutePosition(415, 630);//图片坐标
//		jpg.setAlignment(Image.ALIGN_CENTER); // 不起作用
//		document.add(jpg);
//		
//		// 第四行
//		PdfContentByte cb5 = writer.getDirectContent();
//		cb5.beginText();
//		cb5.setFontAndSize(bfChinese, 10);
//		cb5.setTextMatrix(40, 715);
//		cb5.showText("身高：180cm");
//		cb5.endText();
//		
//		PdfContentByte cb6 = writer.getDirectContent();
//		cb6.beginText();
//		cb6.setFontAndSize(bfChinese, 10);
//		cb6.setTextMatrix(165, 715);
//		cb6.showText("体重：75kg");
//		cb6.endText();
//		
//		PdfContentByte cb7 = writer.getDirectContent();
//		cb7.beginText();
//		cb7.setFontAndSize(bfChinese, 10);
//		cb7.setTextMatrix(290, 715);
//		cb7.showText("工作年限：5年");
//		cb7.endText();
//		
//		// 第五行
//		PdfContentByte cb8 = writer.getDirectContent();
//		cb8.beginText();
//		cb8.setFontAndSize(bfChinese, 10);
//		cb8.setTextMatrix(40, 690);
//		cb8.showText("电话：15811597346");
//		cb8.endText();
//		
//		// 第六行
//		PdfContentByte cb9 = writer.getDirectContent();
//		cb9.beginText();
//		cb9.setFontAndSize(bfChinese, 10);
//		cb9.setTextMatrix(40, 665);
//		cb9.showText("身份证：13115919920909111781");
//		cb9.endText();
//		
//		// 第七行
//		PdfContentByte cb10 = writer.getDirectContent();
//		cb10.beginText();
//		cb10.setFontAndSize(bfChinese, 10);
//		cb10.setTextMatrix(40, 640);
//		cb10.showText("籍贯：北京市昌平区沙河松兰堡村");
//		cb10.endText();
//		
//		// 第八行
//		PdfContentByte cb11 = writer.getDirectContent();
//		cb11.beginText();
//		cb11.setFontAndSize(bfChinese, 10);
//		cb11.setTextMatrix(40, 615);
//		cb11.showText("紧急联系人：李白");
//		cb11.endText();
//		
//		PdfContentByte cb12 = writer.getDirectContent();
//		cb12.beginText();
//		cb12.setFontAndSize(bfChinese, 10);
//		cb12.setTextMatrix(290, 615);
//		cb12.showText("联系人电话：15811597346");
//		cb12.endText();
//		
//		// 第九行
//		PdfContentByte cb13 = writer.getDirectContent();
//		cb13.beginText();
//		cb13.setFontAndSize(bfChinese, 10);
//		cb13.setTextMatrix(40, 590);
//		cb13.showText("护理技能：擅长心胸科");
//		cb13.endText();
//		
//		// 身份证正面
//		Image jpg1 = Image.getInstance("D:/图片/身份证正面.png");//读取图片
////		Image jpg1 = Image.getInstance("D:/图片/身份证正面02.png");//读取图片
//		jpg1.scaleToFit(250, 270);//设置图片的显示大小   长250  宽 300
//		jpg1.setAbsolutePosition(40,315);//图片坐标
//		document.add(jpg1);
//		
//		// 健康证
//		Image jpg2 = Image.getInstance("D:/图片/身份证正面.png");//读取图片
////		Image jpg2 = Image.getInstance("D:/图片/健康证01.png");//读取图片
//		jpg2.scaleToFit(250, 270);//设置图片的显示大小 长250  宽 300
//		jpg2.setAbsolutePosition(290,315);//图片坐标
//		document.add(jpg2);
//		
//		// 毕业证
////		Image jpg3 = Image.getInstance("D:/图片/毕业证.png");//读取图片
//		Image jpg3 = Image.getInstance("D:/图片/身份证正面.png");//读取图片
//		jpg3.scaleToFit(500, 270);//设置图片的显示大小  长500  宽 300
//		jpg3.setAbsolutePosition(40,35);//图片坐标
//		jpg3.setAlignment(Image.ALIGN_CENTER); // 图片居中对齐
//		document.add(jpg3);
//		
//		document.newPage();
//		PdfContentByte c = writer.getDirectContent();
//		c.beginText();
//		c.setFontAndSize(bfChinese, 10);
//		c.setTextMatrix(290, 640);
//		c.showText("联系人电话：15811597346");
//		c.endText();
		
		document.close();
	}
	
	/**
	 * 坐标轴
	 * @throws Exception
	 */
	public static void all01() throws Exception {
		Document document = new Document(PageSize.A4);
		PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("C:/Users/12240/Desktop/test.pdf"));
		// 设置字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//		BaseFont bfChinese = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
      
		document.open();
		PdfContentByte cb = writer.getDirectContent();
		cb.beginText();
		cb.setFontAndSize(bfChinese, 12);
		cb.setTextMatrix(240, 820);
		cb.showText("护理员档案");
		cb.endText();
		
		PdfContentByte cb1 = writer.getDirectContent();
		cb1.beginText();
		cb.setFontAndSize(bfChinese, 12);
		cb1.setTextMatrix(30, 810);
		cb1.showText("----------------------------------------------------------------------------------------------------------------------");
		cb1.endText();
		
		PdfContentByte cb2 = writer.getDirectContent();
		cb2.beginText();
		cb2.setFontAndSize(bfChinese, 10);
		cb2.setTextMatrix(30, 790);
		cb2.showText("姓名：李时珍");
		cb2.endText();
		
		PdfContentByte cb3 = writer.getDirectContent();
		cb3.beginText();
		cb3.setFontAndSize(bfChinese, 10);
		cb3.setTextMatrix(160, 790);
		cb3.showText("性别：男");
		cb3.endText();
		
		PdfContentByte cb4 = writer.getDirectContent();
		cb4.beginText();
		cb4.setFontAndSize(bfChinese, 10);
		cb4.setTextMatrix(290, 790);
		cb4.showText("入职日期：2017-01-01");
		cb4.endText();
		
		// 头像
		Image jpg = Image.getInstance("D:/图片/头像.png");//读取图片
		jpg.scaleToFit(100, 200);//转换大小
		jpg.setAbsolutePosition(420,680);//图片坐标
		document.add(jpg);
		
		PdfContentByte cb5 = writer.getDirectContent();
		cb5.beginText();
		cb5.setFontAndSize(bfChinese, 10);
		cb5.setTextMatrix(30, 770);
		cb5.showText("身高：180cm");
		cb5.endText();
		
		PdfContentByte cb6 = writer.getDirectContent();
		cb6.beginText();
		cb6.setFontAndSize(bfChinese, 10);
		cb6.setTextMatrix(160, 770);
		cb6.showText("体重：75kg");
		cb6.endText();
		
		PdfContentByte cb7 = writer.getDirectContent();
		cb7.beginText();
		cb7.setFontAndSize(bfChinese, 10);
		cb7.setTextMatrix(290, 770);
		cb7.showText("工作年限：5年");
		cb7.endText();
		
		PdfContentByte cb8 = writer.getDirectContent();
		cb8.beginText();
		cb8.setFontAndSize(bfChinese, 10);
		cb8.setTextMatrix(30, 750);
		cb8.showText("电话：15811597346");
		cb8.endText();
		
		PdfContentByte cb9 = writer.getDirectContent();
		cb9.beginText();
		cb9.setFontAndSize(bfChinese, 10);
		cb9.setTextMatrix(30, 730);
		cb9.showText("籍贯：北京市昌平区沙河松兰堡村");
		cb9.endText();
		
		PdfContentByte cb10 = writer.getDirectContent();
		cb10.beginText();
		cb10.setFontAndSize(bfChinese, 10);
		cb10.setTextMatrix(30, 710);
		cb10.showText("紧急联系人：李白");
		cb10.endText();
		
		PdfContentByte cb11 = writer.getDirectContent();
		cb11.beginText();
		cb11.setFontAndSize(bfChinese, 10);
		cb11.setTextMatrix(290, 710);
		cb11.showText("联系人电话：15811597346");
		cb11.endText();
		
		PdfContentByte cb12 = writer.getDirectContent();
		cb12.beginText();
		cb12.setFontAndSize(bfChinese, 10);
		cb12.setTextMatrix(30, 690);
		cb12.showText("护理技能：擅长心胸科");
		cb12.endText();
		
		// 身份证正面
		Image jpg1 = Image.getInstance("D:/图片/身份证正面.png");//读取图片
		jpg1.scaleToFit(200, 450);//设置图片的显示大小
		jpg1.setAbsolutePosition(60,530);//图片坐标
		document.add(jpg1);
		
		// 健康证
		Image jpg2 = Image.getInstance("D:/图片/健康证.png");//读取图片
		jpg2.scaleToFit(200, 450);//设置图片的显示大小
		jpg2.setAbsolutePosition(280,530);//图片坐标
		document.add(jpg2);
		
		// 毕业证
		Image jpg3 = Image.getInstance("D:/图片/毕业证.png");//读取图片
		jpg3.scaleToFit(300, 600);//设置图片的显示大小
		jpg3.setAbsolutePosition(60,310);//图片坐标
		jpg3.setAlignment(Image.ALIGN_CENTER); // 图片居中对齐
		document.add(jpg3);
		
		document.close();
	}
	
	/**
	 * 坐标轴--在all1()上面调整了一下
	 * @throws Exception
	 */
	public static void all2() throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("C:/Users/12240/Desktop/test.pdf"));
		// 设置字体
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//		BaseFont bfChinese = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
      
		document.open();
		PdfContentByte cb = writer.getDirectContent();
		cb.beginText();
		cb.setFontAndSize(bfChinese, 12);
		cb.setTextMatrix(240, 800);
		cb.showText("护理员档案");
		cb.endText();
		
		PdfContentByte cb1 = writer.getDirectContent();
		cb1.beginText();
		cb.setFontAndSize(bfChinese, 12);
		cb1.setTextMatrix(40, 780);
		cb1.showText("--------------------------------------------------------------------------------------------------------------------");
		cb1.endText();
		
		PdfContentByte cb2 = writer.getDirectContent();
		cb2.beginText();
		cb2.setFontAndSize(bfChinese, 10);
		cb2.setTextMatrix(60, 760);
		cb2.showText("姓名：李时珍");
		cb2.endText();
		
		PdfContentByte cb3 = writer.getDirectContent();
		cb3.beginText();
		cb3.setFontAndSize(bfChinese, 10);
		cb3.setTextMatrix(190, 760);
		cb3.showText("性别：男");
		cb3.endText();
		
		PdfContentByte cb4 = writer.getDirectContent();
		cb4.beginText();
		cb4.setFontAndSize(bfChinese, 10);
		cb4.setTextMatrix(320, 760);
		cb4.showText("入职日期：2017-01-01");
		cb4.endText();
		
		// 头像
		Image jpg = Image.getInstance("D:/图片/头像.png");//读取图片
		jpg.scaleToFit(80, 120);//转换大小
		jpg.setAbsolutePosition(450,650);//图片坐标
		jpg.setAlignment(Image.ALIGN_CENTER); // 不起作用
		document.add(jpg);
		
		PdfContentByte cb5 = writer.getDirectContent();
		cb5.beginText();
		cb5.setFontAndSize(bfChinese, 10);
		cb5.setTextMatrix(60, 735);
		cb5.showText("身高：180cm");
		cb5.endText();
		
		PdfContentByte cb6 = writer.getDirectContent();
		cb6.beginText();
		cb6.setFontAndSize(bfChinese, 10);
		cb6.setTextMatrix(190, 735);
		cb6.showText("体重：75kg");
		cb6.endText();
		
		PdfContentByte cb7 = writer.getDirectContent();
		cb7.beginText();
		cb7.setFontAndSize(bfChinese, 10);
		cb7.setTextMatrix(320, 735);
		cb7.showText("工作年限：5年");
		cb7.endText();
		
		PdfContentByte cb8 = writer.getDirectContent();
		cb8.beginText();
		cb8.setFontAndSize(bfChinese, 10);
		cb8.setTextMatrix(60, 710);
		cb8.showText("电话：15811597346");
		cb8.endText();
		
		PdfContentByte cb9 = writer.getDirectContent();
		cb9.beginText();
		cb9.setFontAndSize(bfChinese, 10);
		cb9.setTextMatrix(60, 685);
		cb9.showText("籍贯：北京市昌平区沙河松兰堡村");
		cb9.endText();
		
		PdfContentByte cb10 = writer.getDirectContent();
		cb10.beginText();
		cb10.setFontAndSize(bfChinese, 10);
		cb10.setTextMatrix(60, 660);
		cb10.showText("紧急联系人：李白");
		cb10.endText();
		
		PdfContentByte cb11 = writer.getDirectContent();
		cb11.beginText();
		cb11.setFontAndSize(bfChinese, 10);
		cb11.setTextMatrix(320, 660);
		cb11.showText("联系人电话：15811597346");
		cb11.endText();
		
		PdfContentByte cb12 = writer.getDirectContent();
		cb12.beginText();
		cb12.setFontAndSize(bfChinese, 10);
		cb12.setTextMatrix(60, 635);
		cb12.showText("护理技能：擅长心胸科");
		cb12.endText();
		
		// 身份证正面
		Image jpg1 = Image.getInstance("D:/图片/身份证正面.png");//读取图片
		jpg1.scaleToFit(100, 450);//设置图片的显示大小
		jpg1.setAbsolutePosition(90,480);//图片坐标
		document.add(jpg1);
		
		// 健康证
		Image jpg2 = Image.getInstance("D:/图片/健康证.png");//读取图片
		jpg2.scaleToFit(200, 450);//设置图片的显示大小
		jpg2.setAbsolutePosition(310,480);//图片坐标
		document.add(jpg2);
		
		// 毕业证
		Image jpg3 = Image.getInstance("D:/图片/毕业证.png");//读取图片
		jpg3.scaleToFit(300, 600);//设置图片的显示大小
		jpg3.setAbsolutePosition(90,260);//图片坐标
		jpg3.setAlignment(Image.ALIGN_CENTER); // 图片居中对齐
		document.add(jpg3);
		
		document.close();
	
	}
	
	public static void test() throws Exception{
		try 
        {
             Document document = new Document(PageSize.A4.rotate()); 
             PdfWriter.getInstance(document, new FileOutputStream("C:/Users/12240/Desktop/tablenew.pdf"));

            //设置字体
//            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);   
//            com.itextpdf.text.Font FontChinese24 = new com.itextpdf.text.Font(bfChinese, 24, com.itextpdf.text.Font.BOLD);
//            com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese, 18, com.itextpdf.text.Font.BOLD); 
//            com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.BOLD);
//            com.itextpdf.text.Font FontChinese12 = new com.itextpdf.text.Font(bfChinese, 12, com.itextpdf.text.Font.NORMAL);
//            com.itextpdf.text.Font FontChinese11Bold = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.BOLD);
//            com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
//            com.itextpdf.text.Font FontChinese11Normal = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.NORMAL);

            document.open();
            //table1
            PdfPTable table1 = new PdfPTable(3);
            PdfPCell cell11 = new PdfPCell(new Paragraph("费用报销"));
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell11.setBorder(0);
            String imagePath = "D:/alibaba.png";
            Image image1 = Image.getInstance(imagePath); 

            Image image2 = Image.getInstance(imagePath); 
            //设置每列宽度比例   
            int width11[] = {35,40,25};
            table1.setWidths(width11); 
            table1.getDefaultCell().setBorder(0);
            table1.addCell(image1);  
            table1.addCell(cell11);  
            table1.addCell(image2);
            document.add(table1);
            //加入空行
            Paragraph blankRow1 = new Paragraph(18f, " "); 
            document.add(blankRow1);

            //table2
            PdfPTable table2 = new PdfPTable(2);
            //设置每列宽度比例   
            int width21[] = {2,98};
            table2.setWidths(width21); 
            table2.getDefaultCell().setBorder(0);
            PdfPCell cell21 = new PdfPCell(new Paragraph("报销概要"));
            String imagePath2 = "D:/boder.png";
            Image image21 = Image.getInstance(imagePath2); 
            cell21.setBorder(0);
            table2.addCell(image21);
            table2.addCell(cell21); 
            document.add(table2);
            //加入空行
            Paragraph blankRow2 = new Paragraph(18f, " "); 
            document.add(blankRow2);

            //table3
            PdfPTable table3 = new PdfPTable(3);
            int width3[] = {40,35,25};
            table3.setWidths(width3); 
            PdfPCell cell31 = new PdfPCell(new Paragraph("申请人："+"XXX"));
            PdfPCell cell32 = new PdfPCell(new Paragraph("日期："+"2011-11-11"));
            PdfPCell cell33 = new PdfPCell(new Paragraph("报销单号："+"123456789"));
            cell31.setBorder(0);
            cell32.setBorder(0);
            cell33.setBorder(0);
            table3.addCell(cell31);
            table3.addCell(cell32);
            table3.addCell(cell33);
            document.add(table3);
            //加入空行
            Paragraph blankRow31 = new Paragraph(18f, " "); 
            document.add(blankRow31);

            //table4
            PdfPTable table4 = new PdfPTable(2);
            int width4[] = {40,60};
            table4.setWidths(width4); 
            PdfPCell cell41 = new PdfPCell(new Paragraph("公司："+"XXX"));
            PdfPCell cell42 = new PdfPCell(new Paragraph("部门："+"XXX"));
            cell41.setBorder(0);
            cell42.setBorder(0);
            table4.addCell(cell41);
            table4.addCell(cell42);
            document.add(table4);
            //加入空行
            Paragraph blankRow41 = new Paragraph(18f, " "); 
            document.add(blankRow41);

            //table5
            PdfPTable table5 = new PdfPTable(1);
            PdfPCell cell51 = new PdfPCell(new Paragraph("报销说明："+"XXX"));
            cell51.setBorder(0);
            table5.addCell(cell51);
            document.add(table5);
            //加入空行
            Paragraph blankRow51 = new Paragraph(18f, " "); 
            document.add(blankRow51);

            //table6
            PdfPTable table6 = new PdfPTable(2);
            table6.getDefaultCell().setBorder(0);
            table6.setWidths(width21); 
            PdfPCell cell61 = new PdfPCell(new Paragraph("报销明细"));
            cell61.setBorder(0);
            table6.addCell(image21);
            table6.addCell(cell61); 
            document.add(table6);
            //加入空行
            Paragraph blankRow4 = new Paragraph(18f, " "); 
            document.add(blankRow4);

            //table7
            PdfPTable table7 = new PdfPTable(6);
            BaseColor lightGrey = new BaseColor(0xCC,0xCC,0xCC);
            int width7[] = {20,18,13,20,14,15};
            table7.setWidths(width7); 
            PdfPCell cell71 = new PdfPCell(new Paragraph("费用类型"));
            PdfPCell cell72 = new PdfPCell(new Paragraph("费用发生时间"));
            PdfPCell cell73 = new PdfPCell(new Paragraph("详细信息"));
            PdfPCell cell74 = new PdfPCell(new Paragraph("消费金币/币种"));
            PdfPCell cell75 = new PdfPCell(new Paragraph("报销汇率"));
            PdfPCell cell76 = new PdfPCell(new Paragraph("报销金额"));
            //表格高度
            cell71.setFixedHeight(25);
            cell72.setFixedHeight(25);
            cell73.setFixedHeight(25);
            cell74.setFixedHeight(25);
            cell75.setFixedHeight(25);
            cell76.setFixedHeight(25);
            //水平居中
            cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell74.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell75.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell76.setHorizontalAlignment(Element.ALIGN_CENTER);
            //垂直居中
            cell71.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell72.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell73.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell74.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell75.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell76.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //边框颜色
            cell71.setBorderColor(lightGrey);
            cell72.setBorderColor(lightGrey);
            cell73.setBorderColor(lightGrey);
            cell74.setBorderColor(lightGrey);
            cell75.setBorderColor(lightGrey);
            cell76.setBorderColor(lightGrey);
            //去掉左右边框
            cell71.disableBorderSide(8);
            cell72.disableBorderSide(4);
            cell72.disableBorderSide(8);
            cell73.disableBorderSide(4);
            cell73.disableBorderSide(8);
            cell74.disableBorderSide(4);
            cell74.disableBorderSide(8);
            cell75.disableBorderSide(4);
            cell75.disableBorderSide(8);
            cell76.disableBorderSide(4);
            table7.addCell(cell71);
            table7.addCell(cell72);
            table7.addCell(cell73);
            table7.addCell(cell74);
            table7.addCell(cell75);
            table7.addCell(cell76);
            document.add(table7);

                    //table8
                    PdfPTable table8 = new PdfPTable(6);
                    int width8[] = {20,18,13,20,14,15};
                    table8.setWidths(width8); 
                    PdfPCell cell81 = new PdfPCell(new Paragraph("差旅报销"));
                    PdfPCell cell82 = new PdfPCell(new Paragraph("2011-11-11"));
                    PdfPCell cell83 = new PdfPCell(new Paragraph("XXX"));
                    PdfPCell cell84 = new PdfPCell(new Paragraph("XXX"));
                    PdfPCell cell85 = new PdfPCell(new Paragraph("XXX"));
                    PdfPCell cell86 = new PdfPCell(new Paragraph("XXX"));
                    //表格高度
                    cell81.setFixedHeight(25);
                    cell82.setFixedHeight(25);
                    cell83.setFixedHeight(25);
                    cell84.setFixedHeight(25);
                    cell85.setFixedHeight(25);
                    cell86.setFixedHeight(25);
                    //水平居中
                    cell81.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell84.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell85.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell86.setHorizontalAlignment(Element.ALIGN_CENTER);
                    //垂直居中
                    cell81.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell82.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell83.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell84.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell85.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell86.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    //边框颜色
                    cell81.setBorderColor(lightGrey);
                    cell82.setBorderColor(lightGrey);
                    cell83.setBorderColor(lightGrey);
                    cell84.setBorderColor(lightGrey);
                    cell85.setBorderColor(lightGrey);
                    cell86.setBorderColor(lightGrey);
                    //去掉左右边框
                    cell81.disableBorderSide(8);
                    cell82.disableBorderSide(4);
                    cell82.disableBorderSide(8);
                    cell83.disableBorderSide(4);
                    cell83.disableBorderSide(8);
                    cell84.disableBorderSide(4);
                    cell84.disableBorderSide(8);
                    cell85.disableBorderSide(4);
                    cell85.disableBorderSide(8);
                    cell86.disableBorderSide(4);
                    table8.addCell(cell81);
                    table8.addCell(cell82);
                    table8.addCell(cell83);
                    table8.addCell(cell84);
                    table8.addCell(cell85);
                    table8.addCell(cell86);
                    document.add(table8);
              //加入空行
              Paragraph blankRow5 = new Paragraph(18f, " "); 
              document.add(blankRow5);

            //table9
            PdfPTable table9 = new PdfPTable(3);
            int width9[] = {30,50,20};
            table9.setWidths(width9);
            PdfPCell cell91 = new PdfPCell(new Paragraph(""));
            PdfPCell cell92 = new PdfPCell(new Paragraph("收到的报销金额"));
            PdfPCell cell93 = new PdfPCell(new Paragraph("1000"));
            cell92.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell92.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell93.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell93.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell91.setBorder(0);
            cell92.setBorder(0);
            cell93.setBorder(0);
            table9.addCell(cell91); 
            table9.addCell(cell92); 
            table9.addCell(cell93); 
            document.add(table9);

             document.close();

        } catch (Exception ex) 
        {
          ex.printStackTrace();
        }
	}
}
