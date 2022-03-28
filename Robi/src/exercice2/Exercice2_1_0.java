package exercice2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;
import stree.parser.SParser;
import tools.Tools;


public class Exercice2_1_0 {
	GSpace space = new GSpace("Exercice 2_1", new Dimension(200, 100));
	GRect robi = new GRect();
	String script = "(space setColor blue) (robi setColor red) (robi translate 20 0) (space sleep 1000) (space setColor black)";

	public Exercice2_1_0() {
		space.addElement(robi);
		space.open();
		this.runScript();
	}

	private void runScript() {
		SParser<SNode> parser = new SParser<>();
		List<SNode> rootNodes = null;
		try {
			rootNodes = parser.parse(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<SNode> itor = rootNodes.iterator();
		while (itor.hasNext()) {
			SNode tmp = itor.next();
			this.run(tmp);
		}
	}
	
	private void run(SNode expr) {
		//LES Scripts De SPACE
		if(expr.get(0).contents().equals("space")){
            if(expr.get(1).contents().equals("setColor")) {
                String couleur= expr.get(2).contents().toString();
                Color c=Tools.getColorByName(couleur);
                space.setColor(c);
            }
            else if(expr.get(1).contents().equals("sleep")) {
                String tStr= expr.get(2).contents().toString();
                int temps = Integer.parseInt(tStr);
                try {
    				Thread.sleep(temps);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
            }

        }
		
		//LES Scripts De ROBI
		else if(expr.get(0).contents().equals("robi")){
            if(expr.get(1).contents().equals("setColor")) {
                String couleur= expr.get(2).contents().toString();
                Color c=Tools.getColorByName(couleur);
                robi.setColor(c);
            }
            else if(expr.get(1).contents().equals("translate")) {
                String strX= expr.get(2).contents().toString();
                String strY= expr.get(3).contents().toString();
                int x = Integer.parseInt(strX);
                int y = Integer.parseInt(strY);
                robi.translate(new Point(x, y));
            }

        }
	}

	public static void main(String[] args) {
		new Exercice2_1_0();
	}

}