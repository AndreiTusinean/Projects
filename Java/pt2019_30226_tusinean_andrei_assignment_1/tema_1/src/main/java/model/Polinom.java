package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {
	private int grad;
	private ArrayList<Monom> monoame;
	Monom m = new Monom();
	static Polinom p = new Polinom();

	public Polinom(int grad, ArrayList<Monom> monoame) {
		this.grad = grad;
		this.monoame = new ArrayList<Monom>();
		this.monoame = monoame;
	}

	public Polinom() {

	}

	public Polinom mulPol(Polinom x, Polinom y) {
		int grad = x.grad, g1 = 0, g2 = 0;
		ArrayList<Monom> mtemp = new ArrayList<Monom>();
		for (int i = 0; i < x.monoame.size(); i++) {
			for (int j = 0; j < y.monoame.size(); j++) {
				double c1 = x.monoame.get(i).getC();
				double c2 = y.monoame.get(j).getC();
				g1 = x.monoame.get(i).getG();
				g2 = y.monoame.get(j).getG();
				mtemp.add(m.mulMon(x.monoame.get(i), y.monoame.get(j)));
			}
		}
		return new Polinom(grad, mtemp);
	}

	public Polinom divPol(Polinom x, Polinom y) {
		int grad = x.grad, g1 = 0, g2 = 0;
		ArrayList<Monom> q = new ArrayList<Monom>();
		ArrayList<Monom> r = new ArrayList<Monom>();
		r = x.monoame;
		Polinom pq = new Polinom(0, q);
		Polinom pr = new Polinom(MonoameG(r), r);
		Monom t = new Monom();
		while (MonoameG(r) != 0 && MonoameG(r) >= y.grad) {
			t.c = r.get(0).getC() / y.monoame.get(0).getC();
			t.g = r.get(0).getG() / y.monoame.get(0).getG();
			ArrayList<Monom> mt = new ArrayList<Monom>();
			mt.get(0).setC(t.c);
			mt.get(0).setG(t.g);
			Polinom pt = new Polinom(t.g, mt);
			pq = p.addPol(pq, pt);
			pr = p.subPol(pr, p.mulPol(pt, y));
		}
		return new Polinom(grad, q);
	}

	public Polinom addPol(Polinom x1, Polinom y1) {
		Polinom x = x1, y = y1;
		if (x1.grad < y1.grad) {
			x = y1;
			y = x1;
		}
		boolean fnd = false;
		int grad = x.grad, g1 = 0, g2 = 0;
		ArrayList<Monom> mtemp = new ArrayList<Monom>();
		for (int i = 0; i < x.monoame.size(); i++) {
			fnd = false;
			for (int j = 0; j < y.monoame.size(); j++) {
				g1 = x.monoame.get(i).getG();
				g2 = y.monoame.get(j).getG();
				if (g1 == g2) {
					mtemp.add(m.addMon(x.monoame.get(i), y.monoame.get(j)));
					fnd = true;
				}
			}
			if (fnd == false && g1 != g2)
				mtemp.add(x.monoame.get(i));
		}
		for (int j = 0; j < y.monoame.size(); j++) {
			fnd = false;
			for (int i = 0; i < x.monoame.size(); i++) {
				g1 = x.monoame.get(i).getG();
				g2 = y.monoame.get(j).getG();
				if (g1 == g2) 
					fnd = true;
			}
			if (fnd == false && g1 != g2)
				mtemp.add(y.monoame.get(j));
		}
		return new Polinom(grad, mtemp);
	}

	public Polinom subPol(Polinom x1, Polinom y1) {
		Polinom x = x1, y = y1;
		if (x1.grad < y1.grad) {
			x = y1;
			y = x1;
			for (int i = 0; i < x.monoame.size(); i++)
				x.monoame.get(i).setC(x.monoame.get(i).getC() * (-1));
			for (int j = 0; j < y.monoame.size(); j++)
				y.monoame.get(j).setC(y.monoame.get(j).getC() * (-1));
		}
		boolean fnd = false;
		int grad = x.grad, g1 = 0, g2 = 0;
		ArrayList<Monom> mtemp = new ArrayList<Monom>();
		for (int i = 0; i < x.monoame.size(); i++) {
			fnd = false;
			for (int j = 0; j < y.monoame.size(); j++) {
				double c1 = x.monoame.get(i).getC();
				double c2 = y.monoame.get(j).getC();
				g1 = x.monoame.get(i).getG();
				g2 = y.monoame.get(j).getG();
				if (g1 == g2) {
					mtemp.add(m.subMon(x.monoame.get(i), y.monoame.get(j)));
					fnd = true;
				}
			}
			if (fnd == false && g1 != g2)
				mtemp.add(x.monoame.get(i));
		}
		for (int j = 0; j < y.monoame.size(); j++) {
			fnd = false;
			for (int i = 0; i < x.monoame.size(); i++) {
				g1 = x.monoame.get(i).getG();
				g2 = y.monoame.get(j).getG();
				if (g1 == g2) 
					fnd = true;
			}
			Monom m = new Monom(y.monoame.get(j).getC()*(-1),y.monoame.get(j).getG()); 
			if (fnd == false && g1 != g2)
				mtemp.add(m);
		}
		return new Polinom(grad, mtemp);
	}

	public Polinom derPol(Polinom x) {
		int grad = x.grad;
		ArrayList<Monom> mtemp = new ArrayList<Monom>();
		for (int i = 0; i < x.monoame.size(); i++)
			mtemp.add(m.derMon(x.monoame.get(i)));
		return new Polinom(grad, mtemp);
	}

	public Polinom intPol(Polinom x) {
		int grad = x.grad;
		ArrayList<Monom> mtemp = new ArrayList<Monom>();
		for (int i = 0; i < x.monoame.size(); i++)
			mtemp.add(m.intMon(x.monoame.get(i)));
		return new Polinom(grad, mtemp);
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < monoame.size(); i++) {
			if (monoame.get(i).getC() != 0)
				if (i < monoame.size() - 1)
					s += monoame.get(i).toString() + "+";
				else
					s += monoame.get(i).toString();
		}
		return s;
	}

	public Polinom parse(String input) {
		Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
		Matcher matcher = pattern.matcher(input);
		ArrayList<Double> d = new ArrayList<Double>();
		ArrayList<Monom> mtemp = new ArrayList<Monom>();
		int x = 0, gp;
		while (matcher.find()) {
			String[] arr = matcher.group(0).split("x\\^\\+?");
			for (String a : arr) {
				if (a.charAt(0) == '+')
					a = a.substring(1);
				if (a.charAt(0) == '-' && a.charAt(1) == 'x')
					a = "-1";
				if (a.charAt(0) == 'x')
					a = "1";
				d.add(Double.valueOf(a));
			}
		}
		for (int i = 0; i < d.size() - 1; i += 2) {
			double cm = d.get(i);
			int gm = d.get(i + 1).intValue();
			Monom maux = new Monom(cm, gm);
			mtemp.add(maux);
		}
		if (d.size() % 2 != 0) {
			Monom maux = new Monom(d.get(d.size() - 1), 0);
			mtemp.add(maux);
		}
		gp = d.size() / 2;
		return new Polinom(gp, mtemp);
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
