package com.Talent4Assure.WeBlogger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StringUtils 
{
	public static boolean isEmpty(String data)
	{
		return data==null || data.trim().equals("");
	}
	public static String formatDate(Date date, String formatString)
	{
		try
		{
			SimpleDateFormat format=new SimpleDateFormat(formatString);
			return format.format(date);
		}catch(Exception e)
		{
		}
		return date.toString();
	}
	
	public static Date parseDate(String format, String dateString)
	{
		Date date=null;
		try
		{
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			date=sdf.parse(dateString);
		}catch(Exception e)
		{
			
		}
		return date;
	}
	public static String formatTime(long timeInMillis)
	{
		long hh=TimeUnit.MILLISECONDS.toHours(timeInMillis);
		long mm=TimeUnit.MILLISECONDS.toMinutes(timeInMillis-(hh*60*60*1000));
		long ss=TimeUnit.MILLISECONDS.toSeconds(timeInMillis-(hh*60*60*1000)-(mm*60*1000));
		return String.format("%2s", hh).replace(' ', '0')+":"+String.format("%2s", mm).replace(' ', '0')+":"+String.format("%2s", ss).replace(' ', '0');
	}
	public static void main(String[] args) {
	}
	
	public static String padLeft(int number, int size)
	{
		return String.format("%0"+size+"d", number);
	}
	public static String padLeft(String number, int size)
	{
		//return String.format("%0"+size+"s", number);
		return number;
	}
	public static String capitalizeFullString(String string) {
		  char[] chars = string.toLowerCase().toCharArray();
		  boolean found = false;
		  for (int i = 0; i < chars.length; i++) {
		    if (!found && Character.isLetter(chars[i])) {
		      chars[i] = Character.toUpperCase(chars[i]);
		      found = true;
		    } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
		      found = false;
		    }
		  }
		  return String.valueOf(chars);
		}
}
