package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ParseTest {

	Polinom p = new Polinom();	
	Monom m1=new Monom(2,2);
	Monom m2=new Monom(3,1);
	Monom m3=new Monom(1,0);
	ArrayList<Monom> m=new ArrayList<Monom>();
	@Test
	public void test() {
		m.add(m1);
		m.add(m2);
		m.add(m3);
		Polinom p1 = new Polinom(2,m);
		Polinom p2=p.parse("3x^2+3x^1+x^0");
		assertEquals("3.0*x^2+3.0*x^1+x^0",p2.toString());
	}
	
	@Test
	public void testFail() {
		m.add(m1);
		m.add(m2);
		m.add(m3);
		Polinom p1 = new Polinom(2,m);
		Polinom p2=p.parse("3x^2+3x^1+x^0");
		assertEquals("25.0*x^2+32.0*x^12",p2.toString());	
	}

}
