package data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import business.Order;


public class FileWriter {

	public static void bill(Order o,String items,int price) {
		String s = "Order_";
		s += Integer.toString(o.getOrderID());
		s += ".txt";
		PrintWriter w = null;
		try {
			w = new PrintWriter(s, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		w.println(o.toString()+", items= "+items+", total price= "+price);
		w.close();
	}
}
