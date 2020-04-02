package mvc;

import controller.Controller;
import model.Polinom;
import view.View;

public class MVC {

	public static void main(String[] args) {
		Polinom p = new Polinom();
		View v = new View();
		Controller c = new Controller(p, v);
		v.buidUI();
		
		//p1 = p.parse("4x^3+2x^2+3x^1+2");
		//p2 = p.parse("4x^2+5x^1+4");
		//p3 = p.parse("2x^3+4x^2+5x^1-42");
		//p4 = p.parse("3x^2+4x^1+3");
		//p5 = p.parse("2x^2+1x^1");
		
		//p6 = p.parse("x^3+12x^2+42");
		//p7 = p.parse("x^2-2x^1+1");
	}

}
