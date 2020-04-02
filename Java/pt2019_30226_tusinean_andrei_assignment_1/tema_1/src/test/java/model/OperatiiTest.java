package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperatiiTest {

	
	Polinom p1,p2,p3;
	Polinom p = new Polinom();
	
	@Test
	public void testAdunare() {
		p1 = p.parse("x^3+3x^2+4x^1+2");
		p2 = p.parse("2x^2+1x^1");
		assertEquals("x^3+x^2+3.0*x^1+2.0",p.subPol(p1, p2).toString());//Caz pozitiv
	}
	
	@Test
	public void testAdunareFail() {
		p1 = p.parse("x^3+3x^2+4x^1+2");
		p2 = p.parse("2x^2+1x^1");
		assertEquals("x^2+3.0*x^1+2.0",p.subPol(p1, p2).toString());//Caz negativ
	}
	
	@Test
	public void testScadere() {
		p1 = p.parse("x^3+3x^2+4x^1+2");
		p2 = p.parse("2x^2+1x^1");
		assertEquals("x^3+x^2+3.0*x^1+2.0",p.subPol(p1, p2).toString());//Caz pozitiv
	}
	
	@Test
	public void testScadereFail() {
		p1 = p.parse("x^3+3x^2+4x^1+2");
		p2 = p.parse("2x^2+1x^1");
		assertEquals("x^2+3.0*x^1+2.0",p.subPol(p1, p2).toString());//Caz negativ
	}
	
	@Test
	public void testInmultire() {
		p2 = p.parse("2x^2+1x^1");
		p3 = p.parse("4x^2+3x^1");
		assertEquals("8.0*x^4+6.0*x^3+4.0*x^3+3.0*x^2",p.mulPol(p2, p3).toString());
	}
	
	@Test
	public void testInmultireFail() {
		p2 = p.parse("2x^2+1x^1");
		p3 = p.parse("4x^2+3x^1");
		assertEquals("x^2+3.0*x^1+2.0",p.mulPol(p2, p3).toString());
	}
	
	@Test
	public void testDerivare() {
		p1 = p.parse("x^3+3x^2+4x^1+2");
		assertEquals("3.0*x^2+6.0*x^1+4.0+",p.derPol(p1).toString());
	}
	
	@Test
	public void testDerivareFail() {
		p1 = p.parse("x^3+3x^2+4x^1+2");
		assertEquals("10.0*x^2+6.0*x^4+1.0+",p.derPol(p1).toString());
	}
	
	@Test
	public void testIntegrare() {
		p1 = p.parse("x^3+3x^2+4x^1+2");
		assertEquals("0.25*x^4+x^3+2.0*x^2+2.0*x^1",p.intPol(p1).toString());
	}
	
	@Test
	public void testIntegrareFail() {
		p1 = p.parse("x^3+3x^2+4x^1+2");
		assertEquals("x^2+3.0*x^1+2.0",p.intPol(p1).toString());
	}
	
}
