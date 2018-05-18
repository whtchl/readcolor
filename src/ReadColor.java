
 
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
 
import javax.imageio.ImageIO;
 
//java �汾���г�һ�������������ص�rgb
public class ReadColor {
	/**
	 * ��ȡһ��ͼƬ��RGBֵ
	 * @throws Exception
	 */
	public void getImagePixel(String image) throws Exception {
		int[] rgb = new int[3];
		File file = new File(image);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		System.out.println("width=" + width + ",height=" + height + ".");
		System.out.println("minx=" + minx + ",miniy=" + miny + ".");
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int pixel = bi.getRGB(i, j); //�������д��뽫һ������ת��ΪRGB����
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				System.out.println("i="+i+",j="+j+":("+rgb[0]+","+rgb[1]+","+rgb[2]+")");
			}
		}
	}
 
	/**
	 * ������Ļɫ��ֵ
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws AWTException
	 */
	public int getScreenPixel(int x, int y) throws AWTException { // ��������ֵΪ��ɫ��RGBֵ��
		Robot rb = null; // java.awt.image���е��࣬��������ץȡ��Ļ����������
		rb = new Robot();
		Toolkit tk = Toolkit.getDefaultToolkit(); // ��ȡȱʡ���߰�
		Dimension di = tk.getScreenSize(); // ��Ļ�ߴ���
		System.out.println(di.width);
		System.out.println(di.height);
		Rectangle rec = new Rectangle(0, 0, di.width, di.height);
		BufferedImage bi = rb.createScreenCapture(rec);
		int pixelColor = bi.getRGB(x, y);
 
		return 16777216 + pixelColor; // pixelColor��ֵΪ��������ʵ���ó���������ɫ���ֵ����ʵ����ɫֵ��
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		int x = 0;
		ReadColor rc = new ReadColor();
		x = rc.getScreenPixel(100, 345);
		System.out.println(x + " - ");
			
		rc.getImagePixel("C:\\Users\\happen\\Desktop\\readcolor\\12.png");
	}
 
}