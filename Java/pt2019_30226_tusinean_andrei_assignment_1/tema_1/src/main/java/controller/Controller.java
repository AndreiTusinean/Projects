package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Polinom;
import view.View;

public class Controller {
	static Polinom p = new Polinom();
	static View v = new View();

	public Controller(Polinom p, View v) {
		this.p = p;
		this.v = v;
		v.addIntL1();
		v.addIntL2();
		v.addDerL1();
		v.addDerL2();
		v.addAddL();
		v.addSubL();
		v.addMulL();
		v.addDivL();
	}	
}