package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monom {
	double c;
	int g;

	public Monom(double c, int g) {
		this.c = c;
		this.g = g;
	}

	public Monom() {

	}

	public Monom addMon(Monom x, Monom y) {
		Monom m = new Monom();
		m.c = x.c + y.c;
		m.g = x.g;
		return m;
	}

	public Monom subMon(Monom x, Monom y) {
		Monom m = new Monom();
		m.c = x.c - y.c;
		m.g = x.g;
		return m;
	}

	public Monom mulMon(Monom x, Monom y) {
		Monom m = new Monom();
		m.c = x.c * y.c;
		m.g = x.g + y.g;
		return m;
	}

	public Monom divMon(Monom x, Monom y) {
		Monom m = new Monom();
		m.c = x.c / y.c;
		m.g = x.g / y.g;
		return m;
	}

	public Monom derMon(Monom x) {
		Monom m = new Monom();
		m.c = x.c * x.g;
		m.g = x.g - 1;
		return m;
	}

	public Monom intMon(Monom x) {
		Monom m = new Monom();
		m.c = x.c / (x.g + 1);
		m.g = x.g + 1;
		return m;
	}

	public String toString() {
		if (c == 0)
			return "0";
		if (c == 1)
			return "x^" + g;
		if (g == 0)
			return c + "";
		return c + "*x^" + g;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public static int MonoameG(ArrayList<Monom> m) {
		int g = 0;
		for (int i = 0; i < m.size(); i++) {
			if (m.get(i).getG() > g)
				g = m.get(i).getG();
		}
		return g;
	}

}
