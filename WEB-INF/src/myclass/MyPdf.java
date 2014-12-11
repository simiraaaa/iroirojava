package myclass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 *
 * @author yuki
 *
 */
public class MyPdf {

	//出力用の何か
	private ByteArrayOutputStream byteout = new ByteArrayOutputStream();
	private Document doc=null;
	private Font font;
	private PdfWriter pw;
	private String[][] strCells =null;
	private float[][] paddings =null;
	private BaseColor[][] backGrounds =null;
	private BaseColor[][] borderColors =null;
	private float paddingAll =0f;
	private boolean isPaddingAll =false;
	private PdfContentByte pcb =null;
	private float pageSize = 0.0f;


	/**
	 * PDFをつくる
	 * @param pageSize PageSize.A4とか
	 * @param marginLeft
	 * @param marginRight
	 * @param marginTop
	 * @param marginBottom
	 * @param fontSize
	 * @param numColumns
	 */
	public MyPdf(
			final Rectangle pageSize,
			final float marginLeft,
			final float marginRight,
			final float marginTop,
			final float marginBottom,
			final float fontSize
			) {
		init(pageSize, marginLeft, marginRight, marginTop, marginBottom, fontSize);
	}

	/**
	 * テーブルを使う場合設定する。これを設定したあとに背景などを設定できる。
	 * @param scs
	 * @return
	 */
	public MyPdf setStrCells(String[][] scs) {
		this.strCells=scs;
		backGrounds=null;
		paddings=null;
		isPaddingAll=false;
		paddingAll =0f;
		borderColors=null;
		return this;
	}

	public MyPdf setPaddingAll(final float f) {
		paddingAll=f;
		isPaddingAll=true;
		return this;
	}


	/**
	 * 引数には配列の添え字を２つと色をRGBAのVALUEを
	 * 0,0,MyColor.BLACK,
	 * 0,1,MyColor.GRAY,
	 * 0,2,MyColor.PINK,
	 * 1,0,new MyColor(255,255,255).getValue()
	 * など
	 * @param is
	 * @return this
	 */
	public MyPdf setBackGrounds(int... is){
		backGrounds = new BaseColor[strCells.length][strCells[0].length];
		for(int i=0,len=is.length;i<len;){
			backGrounds[is[i++]][is[i++]]=new BaseColor(is[i++]);
		}
		return this;
	}
	/**
	 * 直
	 * @param baseColors
	 * @return
	 */
	public MyPdf setBackGrounds(BaseColor[][] baseColors){
		backGrounds = baseColors;
		return this;
	}
	/**
	 * 直
	 * @param baseColors
	 * @return
	 */
	public MyPdf setBorderColors(BaseColor[][] baseColors){
		borderColors = baseColors;
		return this;
	}

	/**
	 * 引数には配列の添え字を２つと色をRGBAのVALUEを
	 * 0,0,MyColor.BLACK,
	 * 0,1,MyColor.GRAY,
	 * 0,2,MyColor.PINK,
	 * 1,0,new MyColor(255,255,255).getValue()
	 * など
	 * @param is
	 * @return this
	 */
	public MyPdf setBorderColors(int... is){
		borderColors = new BaseColor[strCells.length][strCells[0].length];
		for(int i=0,len=is.length;i<len;){
			borderColors[is[i++]][is[i++]]=new BaseColor(is[i++]);
		}
		return this;
	}

	/**
	 * 引数には配列の添え字を２つとpaddingを
	 * @param fs
	 * @return
	 */
	public MyPdf setPaddings(float... fs) {
		paddings = new float[strCells.length][strCells[0].length];
		for(int i=0,len=fs.length;i<len;){
			paddings[(int)fs[i++]][(int)fs[i++]]=fs[i++];
		}
		return this;
	}

	/**
	 * 色をintで扱うクラスを作って返す
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 * @return
	 */
	public MyColor createMyColor(final int r,final int g,final int b,final int a) {
		return new MyColor(r, g, b, a);
	}

	/**
	 * 色をintで扱うクラスを作って返す
	 */
	public MyColor createMyColor(final int r,final int g,final int b) {
		return new MyColor(r, g, b);
	}

	/**
	 * 色をintで扱うクラスを作って返す
	 */
	public MyColor createMyColor(final int argb) {
		return new MyColor(argb);
	}

	/**
	 * 色をintで扱うクラスを作って返す
	 */
	public MyColor createMyColor() {
		return new MyColor();
	}



	/**
	 * セットされているセルの情報を元テーブルをつくってdocに追加
	 * @return
	 */
	public MyPdf addTable() {
		if(strCells==null){
			System.out.println("セルがセットされていない");
			return this;
		}
		boolean isSetBackGrounds= backGrounds !=null;
		boolean isSetPaddings= paddings !=null;
		boolean isSetBorderColors= borderColors !=null;

		PdfPTable t=new PdfPTable(strCells[0].length);
		for(int i =0,len=strCells.length;i<len;++i){
			for(int j=0,J=strCells[i].length;j<J;++j){
				PdfPCell c= new PdfPCell(new Paragraph(("".equals(strCells[i][j])?" ":strCells[i][j]),font));
				if(isSetBackGrounds){
					if(backGrounds[i][j]!=null){c.setBackgroundColor(backGrounds[i][j]);}
				}
				if(isSetBorderColors){
					if(borderColors[i][j]!=null){c.setBorderColor(borderColors[i][j]);}
				}
				if(isPaddingAll){
					c.setPadding(paddingAll);
				}else if(isSetPaddings){
					if(paddings[i][j]!=0.0f){
						c.setPadding(paddings[i][j]);
					}
				}
				t.addCell(c);
			}
		}

		try {
			doc.add(t);
		} catch (DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("テーブル追加でエラー");
		}
		return this;
	}

	/**
	 * PDFをつくる
	 */
	public MyPdf(){
		init(PageSize.A4, 50f, 50f, 50f, 50f, 11f);
	}
	/**
	 * 開く
	 * @return this
	 */
	public MyPdf open() {
		doc.open();
		return this;
	}
	/**
	 * 閉じる
	 * @return this
	 */
	public MyPdf close() {
		doc.close();
		return this;
	}

	/**
	 * 連続での使用非推奨(遅いから)
	 * @param s
	 * @return this
	 */
	public MyPdf add(String s) {
		try {
			doc.add(new Paragraph(s,font));
		} catch (DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return this;
	}


	private void init(final Rectangle pageSize,
			final float marginLeft,
			final float marginRight,
			final float marginTop,
			final float marginBottom,
			final float fontSize) {

		doc=createDocument(pageSize, marginLeft, marginRight, marginTop, marginBottom);
		font=createFont(fontSize);
		pw=createPdfWriter();

	}

	public Document getDocument() {
		return doc;
	}

	/**
	 * Documentを作って返す
	 * @param pageSize
	 * @param marginLeft
	 * @param marginRight
	 * @param marginTop
	 * @param marginBottom
	 * @return
	 */
	public Document createDocument(final Rectangle pageSize,
			final float marginLeft,
			final float marginRight,
			final float marginTop,
			final float marginBottom) {
		return new Document(pageSize, marginLeft, marginRight, marginTop, marginBottom);

	}
	public PdfPCell createCell(String s) {
		return new PdfPCell(new Paragraph(s,font));
	}

	public PdfWriter createPdfWriter() {
		PdfWriter p=null;
		 try {
			p=PdfWriter.getInstance
			 (doc, byteout);
		} catch (DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		 return p;
	}

	public PdfWriter getPdfWriter() {
		return pw;
	}

	/**
	 * フォントを作って返す
	 * @param fontSize
	 * @return
	 */
	public Font createFont(
			final float fontSize) {
		Font f=null;
		try {
			f=new Font(BaseFont.createFont
					   ("HeiseiKakuGo-W5",
						"UniJIS-UCS2-H",
			           BaseFont.NOT_EMBEDDED)
				       ,fontSize);
		} catch (DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return f;
	}

	private PdfContentByte getPdfContentByte() {
		if(pcb == null){ pcb = pw.getDirectContent(); }
		return pcb;
	}

	public MyPdf nextPage() {
		try {
			doc.add(Chunk.NEXTPAGE);
		} catch (DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return this;
	}

	public MyPdf showText(final float x,final float y,final String text) throws DocumentException, IOException {
		getPdfContentByte();
		pageSize = pw.getPageSize().getHeight();
		BaseFont bf = BaseFont.createFont("HeiseiKakuGo-W5","UniJIS-UCS2-H",BaseFont.NOT_EMBEDDED);
		pcb.setFontAndSize(bf,11);
		pcb.setColorFill(CMYKColor.BLACK);
		pcb.beginText();
		pcb.moveText(x, pageSize-y);
		pcb.showText(text);
		pcb.endText();
		return this;
	}

	public MyPdf showImage(final float x,final float y,final String src) throws MalformedURLException, IOException, DocumentException {
  	    com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(src);
  	    getPdfContentByte();
		pageSize = pw.getPageSize().getHeight();
  	    img.setAbsolutePosition(x,pageSize - y-img.getHeight());
  	    pcb.addImage(img);
		return this;
	}

	public MyPdf sendPdf(HttpServletResponse res) {
		res.setContentType("application/pdf");
		  res.setContentLength(byteout.size());
		try {
			OutputStream out= res.getOutputStream();
			out.write(byteout.toByteArray());
			out.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		  return this;
	}

}
