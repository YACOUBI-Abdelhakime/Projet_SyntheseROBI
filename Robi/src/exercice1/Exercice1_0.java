package exercice1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

import graphicLayer.GRect;
import graphicLayer.GSpace;

public class Exercice1_0 {
	GSpace space = new GSpace("Exercice 1", new Dimension(200, 150));
	GRect robi = new GRect();
	Random x = new Random();

	public Exercice1_0() {
		space.addElement(robi);
		space.open();

		while (true) {
			robi.getPosition();
			if (robi.getX() < space.getWidth() - robi.getWidth() && robi.getY() == 0) {

				robi.translate(new Point(10, 0));

			} else if (robi.getY() < space.getHeight() - robi.getHeight() && robi.getX() != 0) {

				robi.translate(new Point(0, 10));

			} else if (robi.getX() < space.getWidth() && robi.getX() != 0) {

				robi.translate(new Point(-10, 0));

			} else if (robi.getY() > 0 && robi.getX() == 0) {

				robi.translate(new Point(0, -10));

				if (robi.getY() == 0 && robi.getX() == 0) {
					robi.setColor(new Color((int) (Math.random() * 0x1000000)));
				}

			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		new Exercice1_0();
	}

}