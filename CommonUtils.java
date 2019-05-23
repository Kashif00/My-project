package com.Talent4Assure.WeBlogger.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;

import com.Talent4Assure.WeBlogger.utils.CommonUtils;
import com.Talent4Assure.WeBlogger.utils.DataEncryption;
import com.Talent4Assure.WeBlogger.utils.StringUtils;

public class CommonUtils
{
	//aspect ratio 16:10
	public static final int STANDARD_WIDTH		=	1280;
	public static final int STANDARD_HEIGHT		=	800;
	
	//private static XStream xstream;
	private static Border border;
	private static Dimension screenResolution;
	private static final long START_TIME	=	1375295400000L; // 01/08/2013
	
	private static final char[] VALID_PASSWORD_CHARS=new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7'
		, '8', '9', '$', '@', '#'};
	private static final int MIN_PASSWORD_LENGTH=6;
	private static final int MAX_PASSWORD_LENGTH=12;
	int maxLengthg=15;
	
	public static Object instantiateClass(String className)
	{
		Object object = null;
		try
		{
			Class c = Class.forName(className.trim());
			object = c.newInstance();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return object;
	}

	public static byte[] getImageData(String filePath)
	{
		byte data[] = null;
		// getClass().getResourceAsStream(filePath);
		return data;
	}
	
	public static int convertToIntAndRound(String floatString)
	{
		int number=0;
		try
		{
			float f=Float.parseFloat(floatString);
			number=Math.round(f);
		}catch(Exception e)
		{
			
		}
		return number;
	}

	public static String getFileExtension(String fileName)
	{
		if (fileName != null)
		{
			if (fileName.contains("."))
			{
				return fileName.substring(fileName.lastIndexOf(".") + 1);
			} else
			{
				return "";
			}
		} else
		{
			return "";
		}
	}
	
	public static synchronized String generateUniqueOrderId(String userInfo)
	{
		String orderId=null;
		orderId=userInfo+Long.toString(System.nanoTime(), Character.MAX_RADIX).toUpperCase();
		return orderId;
	}

	public static byte[] serializeObject(Object object)
	{
		byte[] data = null;
		if (object != null)
		{
			try
			{
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(baos);
				out.writeObject(object);
				out.close();
				data = baos.toByteArray();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public static Date getISTdate(){
		Calendar calendar = Calendar.getInstance();
        TimeZone fromTimeZone = calendar.getTimeZone();
        TimeZone toTimeZone = TimeZone.getTimeZone("IST");

        calendar.setTimeZone(fromTimeZone);
        calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
        if (fromTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
        }

        calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
        if (toTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
        }

        return calendar.getTime();
	}

	public static Date getCSTdate(){
		
		Calendar calendar = Calendar.getInstance();
        TimeZone fromTimeZone = calendar.getTimeZone();
        TimeZone toTimeZone = TimeZone.getTimeZone("CST");

        calendar.setTimeZone(fromTimeZone);
        calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
        if (fromTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
        }

        calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
        if (toTimeZone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
        }

        return calendar.getTime();
	}
	
	
	
	public static int convertToInt(String str)
	{
		int value = 0;
		try
		{
			if (!StringUtils.isEmpty(str))
			{
				value = Integer.parseInt(str.trim());
			}
		} catch (Exception e)
		{

		}
		return value;
	}
	
	public static int convertHexToInt(String hexString)
	{
		try
		{
			return Integer.parseInt(hexString, 16);
		}catch(Exception e)
		{
			return 0;
		}
	}

	public static BufferedImage getScaledImage(BufferedImage image, int width,
			int height)
	{
		try
		{
			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();
			double scaleX = (double) width / imageWidth;
			double scaleY = (double) height / imageHeight;
			AffineTransform scaleTransform = AffineTransform.getScaleInstance(
					scaleX, scaleY);
			AffineTransformOp bilinearScaleOp = new AffineTransformOp(
					scaleTransform, AffineTransformOp.TYPE_BILINEAR);
			return bilinearScaleOp.filter(image, new BufferedImage(width,
					height, image.getType()));
		} catch (Exception e)
		{
		}
		return image;
	}

	public static BufferedImage getProfileImage(String url)
	{
		BufferedImage image=null;
		try 
		{
			image=ImageIO.read(new URL(url));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(image==null)
		{
			try
			{
				InputStream input=CommonUtils.class.getResourceAsStream("/images/default-profile.png");
				image=ImageIO.read(input);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(image!=null)
		{
			image=getScaledImage(image, 50, 50);
		}
		return image;
	}
	
	public static BufferedImage getTransformedImage(BufferedImage image,
			int scalePercentage)
	{
		try
		{
			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();

			int width = (imageWidth * scalePercentage) / 100;
			int height = (imageHeight * scalePercentage) / 100;

			double scaleX = (double) width / imageWidth;
			double scaleY = (double) height / imageHeight;

			AffineTransform scaleTransform = AffineTransform.getScaleInstance(
					scaleX, scaleY);
			AffineTransformOp bilinearScaleOp = new AffineTransformOp(
					scaleTransform, AffineTransformOp.TYPE_BILINEAR);
			return bilinearScaleOp.filter(image, new BufferedImage(width,
					height, image.getType()));
		} catch (Exception e)
		{
		}
		return image;
	}

	public static BufferedImage trimImage(BufferedImage image)
	{
		BufferedImage newImg=image;
		try
		{
			Rectangle rectangle=getTrimmedRectangle(image);
			newImg = image.getSubimage(rectangle.x, rectangle.y, rectangle.width,rectangle.height);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return newImg;
	}

	private static Rectangle getTrimmedRectangle(BufferedImage image)
	{
		Rectangle rectangle;
		int x, x1;

		int height = image.getHeight();
		int width = image.getWidth();

		int y = 0, y1 = 0;
		boolean found = false;
		for (int i = 0; i < height&&!found; i++)
		{
			for (int j = 0; j < width; j++)
			{
				if (image.getRGB(j, i) != Color.WHITE.getRGB())
				{
					found=true;
					y=i;
					break;
				}						
			}
		}
		found = false;
		for (int i = height-1; i > 0&&!found; i--)
		{
			for (int j = 0; j < width; j++)
			{
				if (image.getRGB(j, i) != Color.WHITE.getRGB())
				{
					found=true;
					y1=i+1;
					break;
				}						
			}
		}
		
		rectangle=new Rectangle(0, y, width, y1-y);
		return rectangle;
	}

	/*public static XStream getXStream()
	{
		if (xstream == null)
		{
			xstream = new XStream();
		}
		return xstream;
	}*/

	public static byte[] getFileData(File file)
	{
		InputStream is = null;
		try
		{
			is = new FileInputStream(file);
			// Get the size of the file
			long length = file.length();
			if (length > Integer.MAX_VALUE)
			{
				}
			byte[] bytes = new byte[(int) length];
			// Read in the bytes
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
			{
				offset += numRead;
			}
			// Ensure all the bytes have been read in
			if (offset < bytes.length)
			{
			} else
			{
				return bytes;
			}
		} catch (Exception e)
		{

		} finally
		{
			if (is != null)
				try
				{
					is.close();
				} catch (IOException e)
				{
				}
		}
		return null;
	}

	public static void printStackTrace()
	{
		try
		{
			StackTraceElement element[] = Thread.currentThread()
					.getStackTrace();
			for (StackTraceElement e : element)
			{
			}
		} catch (Exception e)
		{

		}
	}

	public static void main(String[] args)
	{
	}

	public static Border getTextFieldBorder()
	{
		if (border == null)
		{
			border = new LineBorder(new Color(80, 80, 80));
		}
		return border;
	}
	
	public static synchronized String  generateUniqueFileName()
	{
		String uniqueFileName=null;
		try
		{
			long timeInMillis=System.currentTimeMillis();
			uniqueFileName=Long.toString(timeInMillis, Character.MAX_RADIX).toUpperCase();
			Thread.sleep(10);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return uniqueFileName;
	}
	
	public static synchronized String  generateUniqueId()
	{
		String uniqueFileName=null;
		try
		{
			long timeInMillis=System.currentTimeMillis()-START_TIME;
			uniqueFileName=Long.toString(timeInMillis, Character.MAX_RADIX).toUpperCase();
			Thread.sleep(10);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return uniqueFileName;
	}

	
	public static String getMimeType(File file)
	{
		String mimeType=null;
		mimeType= URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType==null)
		{
			InputStream is=null;
			try
			{
				is = new BufferedInputStream(new FileInputStream(file));
				mimeType = URLConnection.guessContentTypeFromStream(is);
			}catch(Exception e)
			{
				
			}finally
			{
				try{if(is!=null){is.close();}}catch(Exception e){}
			}
			if(mimeType==null)
			{
				mimeType="application/octet-stream";
			}
		}
		return mimeType;
	}

	/*public static Object cloneObject(Object object)
	{
		if(object!=null)
		{
			return deserializeObject(serializeObject(object));
		}
		return null;
	}*/
	
	/*This method implements the password encryption rule..
	 * Currently..it concatenates the userid in upper case, a '#' and password and encrypts it 
	 */
	public static String getEncryptedPassword(String userId, String password)
	{
		String encryptedPassword=null;
		encryptedPassword=DataEncryption.encryptPassword(userId.toUpperCase()+"#"+password);
		return encryptedPassword;
	}
		
	public static Dimension getSize(int width, int height)
	{
		Dimension d=null;
		if(screenResolution==null)
		{
			screenResolution=Toolkit.getDefaultToolkit().getScreenSize();
		}
		width=(screenResolution.width*width)/STANDARD_WIDTH;
		height=(screenResolution.height*height)/STANDARD_HEIGHT;
		d=new Dimension(width, height);
		return d;
	}
	
	public static void repaintComponent(Component component)
	{
		if(component!=null)
		{
			if(!(component instanceof JTable))
			{
				component.validate();
				component.repaint();
			}else
			{
				((DefaultTableModel)(((JTable)component).getModel())).fireTableDataChanged();
			}
		}
	}
	
	public static String generateRandomPassword()
	{
		StringBuilder builder=new StringBuilder();
		int passwordLength=MIN_PASSWORD_LENGTH + (int)(Math.random() * ((MAX_PASSWORD_LENGTH - MIN_PASSWORD_LENGTH) + 1));
		for(int i=0; i<passwordLength; i++)
		{
			int index=(int)Math.round((Math.random()*(VALID_PASSWORD_CHARS.length-1)));
			char c=VALID_PASSWORD_CHARS[index];
			builder.append(c);
		}
		return builder.toString();
	}
	
	public List<String> getRunningProcessList()
	{
		List<String> processList=null;
		return processList;
	}
	
	public static String convertIdToString(int id)
	{
		return Integer.toString(id, Character.MAX_RADIX).toUpperCase();
	}
	
	public static final String format(Color c) {
        String r = (c.getRed() < 16) ? "0" + Integer.toHexString(c.getRed()) : Integer.toHexString(c.getRed());
        String g = (c.getGreen() < 16) ? "0" + Integer.toHexString(c.getGreen()) : Integer.toHexString(c.getGreen());
        String b = (c.getBlue() < 16) ? "0" + Integer.toHexString(c.getBlue()) : Integer.toHexString(c.getBlue());
        return "#" + r + g + b;
    }
    
    /**
     * Utility method to convert HTML to text.
     * @param html The string containing HTML.
     * @return a String containing the derived text .
     */
    public static final String html2text(String html) {
        EditorKit kit = new HTMLEditorKit();
        Document doc = kit.createDefaultDocument();
        doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        try {
            Reader reader = new StringReader(html);
            kit.read(reader, doc, 0);
            return doc.getText(0, doc.getLength());
        } catch (Exception e) {
          
            return "";
        }
    }
	
	
	private static List<String> getRunningProcessesOnLinux()
	{
		List<String> runningProcesses=null;
		return runningProcesses;
	}
	
	private static boolean isWindows()
	{
		String OS;
		OS = System.getProperty("os.name"); 
		return OS.startsWith("Windows");
	}
	
	public static boolean isNumeric(String str)
	{
		return str!=null && str.matches("[+]?\\d*?\\d+");
	}
	
	public static byte[] readData(InputStream inputStream)
	{
		byte readData[]=null;
		try 
		{
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[1024];

			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
			  buffer.write(data, 0, nRead);
			}
			buffer.flush();
			readData=buffer.toByteArray();	
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return readData;
	}
	
}