package com.buleocean_health.springboot.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PushbuttonField;

/**
* @author huyanqiu
* @date 2017年12月12日 下午3:26:04
* @version 版本号：1.0
*/
public class PdfUtils {
	
//	/**
//	 * 利用模板生成pdf
//	 */
//	public static void fillTemplate() {
//		// 模板路径
//		String templatePath = "D:/护理员档案.pdf";
//		// 生成的新文件路径
//		String newPDFPath = "D:/test.pdf";
//		PdfReader reader;
//		FileOutputStream out;
//		ByteArrayOutputStream bos;
//		PdfStamper stamper;
//		try {
//			out = new FileOutputStream(newPDFPath);
//			reader = new PdfReader(templatePath);
//			bos = new ByteArrayOutputStream();
//			stamper = new PdfStamper(reader, bos);
//			AcroFields form = stamper.getAcroFields();
//			System.out.println(form.getFields());
//		} catch (IOException|DocumentException e) {
//			throw new RuntimeException("");
//		} 
//	}
	
	/**
	 * 创建简单的pdf文件示例
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static void createSimplePdfFile() throws FileNotFoundException, DocumentException {
		// 1
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("D:/test.pdf"));
		document.open();
		document.add(new Paragraph("Hello World"));
		document.close();
		// 2
		Document document2 = new Document();
		PdfWriter.getInstance(document2, new FileOutputStream("D:/testa.pdf"));
		document2.open();
		document2.add(new Paragraph("HelloWorld"));
		document2.close();
	}
	
	/**
	 *创建简单的pdf文件示例(设置页面大小，页面背景色，页面空白，Titie，Author，Subject，KeyWords)
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public static void createSimlePdfFile2() throws FileNotFoundException, DocumentException {
		// 页面大小
		Rectangle rect = new Rectangle(PageSize.B5.rotate());
		// 页面背景色
		rect.setBackgroundColor(BaseColor.ORANGE);
		// 创建一个pdf文件
		Document document = new Document(rect);
		// 创建一个队pdf操作的流
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/testb.pdf"));
		// PDF版本(默认1.4)
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
		// 文档属性
		document.addTitle("护理员档案");
		document.addAuthor("胡艳秋");
		document.addSubject("这里表示内容");
		document.addKeywords("keywords");
		document.addCreator("Creator");
		// 页边空白
		document.setMargins(10, 20, 30, 40);
		document.open();
		document.add(new Paragraph("hello world"));
		document.close();
	}
	
	public static void createHasPasswordPdfFile() throws Exception {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/testc.pdf"));     
		// 设置密码为："World"     
		writer.setEncryption("Hello".getBytes(), "World".getBytes(),     
		        PdfWriter.ALLOW_SCREENREADERS,     
		        PdfWriter.STANDARD_ENCRYPTION_128);     
		document.open();     
		document.add(new Paragraph("Hello World"));
	}
	
	/**
	 * 利用模板生成pdf  
	 */
    public static void fillTemplate() {  
        // 模板路径  
        String templatePath = "C:/Users/12240/Desktop/table.pdf";  
        // 生成的新文件路径  
        String newPDFPath = "C:/Users/12240/Desktop/tablenew.pdf";  
        PdfReader reader;  
        FileOutputStream out;  
        ByteArrayOutputStream bos;  
        PdfStamper stamper;  
        try {  
            out = new FileOutputStream(newPDFPath);// 输出流  
            reader = new PdfReader(templatePath);// 读取pdf模板  
            bos = new ByteArrayOutputStream();  
            stamper = new PdfStamper(reader, bos);  
            AcroFields form = stamper.getAcroFields();  
  
            String[] str = { "胡艳秋", "女", "年龄", "36020219931215302X", "江西景德镇" };  
            int i = 0;  
            java.util.Iterator<String> it = form.getFields().keySet().iterator();  
            while (it.hasNext()) {  
                String name = it.next().toString();  
                System.out.println(name);  
                form.setField(name, str[i++]);  
            }  
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true  
            stamper.close();  
  
            Document doc = new Document();  
            PdfCopy copy = new PdfCopy(doc, out);  
            doc.open();  
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);  
            copy.addPage(importPage);  
            doc.close();  
  
        } catch (IOException e) {  
            System.out.println(1);  
        } catch (DocumentException e) {  
            System.out.println(2);  
        }  
  
    }  
    
    public void manipulatePdf(String src, String dest)
    	    throws DocumentException, IOException {
    	    PdfReader reader = new PdfReader(src);
    	    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
    	    PushbuttonField button = new PushbuttonField(
    	        stamper.getWriter(), new Rectangle(36, 700, 72, 730), "post");
    	    button.setText("POST");
    	    button.setBackgroundColor(new GrayColor(0.7f));
    	    button.setVisibility(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
    	    PdfFormField submit = button.getField();
    	    submit.setAction(PdfAction.createSubmitForm(
    	        "http://itextpdf.com:8180/book/request", null,
    	        PdfAction.SUBMIT_HTML_FORMAT | PdfAction.SUBMIT_COORDINATES));
    	    stamper.addAnnotation(submit, 1);
    	    stamper.close();
    	}
	
	public static void main(String[] args) throws Exception {
		new File("D:/test/15132341483367527816.png").renameTo(new File("D:/test/15132166623086579056_head1.png"));
//		createSimplePdfFile();
//		createSimlePdfFile2();
//		createHasPasswordPdfFile();
//		fillTemplate();
//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(UUID.randomUUID().toString().length());
//		System.out.println("128506d852134f7293b42291fcb913b3".length());
//		System.out.println(("TX_WZX_"+IdUtils.getRandomIdByDateTime()));
//		int cishu = 0;
//		for (int i=0; i<1000; i++) {
//			Set<String> set = new HashSet<>();
//			for (int k=0; k<1000; k++) {
//				String p = (System.currentTimeMillis()+MathUtils.randomDigitNumber(7));
//				set.add(p);
////				System.err.println(p);
////				Thread.sleep(1);
////				set.add(IdUtils.getRandomIdByDateTime());
//			}
//			int num=0;
//			if (set.size()!=1000) {
//				num++;
//				System.out.println(set.size());
//			}
//			if (num>0) {
//				cishu++;
//			}
//		}
//		System.out.println("cishu:"+cishu);
	}
}
