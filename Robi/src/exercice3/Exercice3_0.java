package exercice3;

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

public class Exercice3_0 {
	GSpace space = new GSpace("Exercice 3", new Dimension(200, 100));
	GRect robi = new GRect();
	String script = "" +
	"   (space setColor black) " +
	"   (robi setColor yellow)" +
	"   (space sleep 1000)" +
	"   (space setColor white)\n" + 
	"   (space sleep 1000)" +
	"	(robi setColor red) \n" + 
	"   (space sleep 1000)" +
	"	(robi translate 100 0)\n" + 
	"	(space sleep 1000)\n" + 
	"	(robi translate 0 50)\n" + 
	"	(space sleep 1000)\n" + 
	"	(robi translate -100 0)\n" + 
	"	(space sleep 1000)\n" + 
	"	(robi translate 0 -40)";

	public Exercice3_0() {
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
			this.run(itor.next());
		}
	}

	private void run(SNode expr) {
		Command cmd = getCommandFromExpr(expr);
		if (cmd == null)
			throw new Error("unable to get command for: " + expr);
		cmd.run();
	}

	Command getCommandFromExpr(SNode expr){
		Command command;
		String target = expr.get(0).contents();
		if (target.equals("space")) {
		String cmd = expr.get(1).contents();
		if (cmd.equals("setColor")){
			SpaceChangeColor space= new SpaceChangeColor(Tools.getColorByName(expr.get(2).contents().toString()));
			 command= space;
		} else if (cmd.equals("sleep")){
			//String val1= expr.get(2).contents().toString();
            //int res1 = Integer.parseInt(val1);
            Spacesleep space= new Spacesleep(Integer.parseInt(expr.get(2).contents().toString()));
            command=space;
		} else
		throw new Error("Invalid space command:" + expr);
		} else if (target.equals("robi")) {
		String cmd = expr.get(1).contents();
		if (cmd.equals("setColor")){
			//String couleur= expr.get(2).contents().toString();
            //Color c=Tools.getColorByName(couleur);
           // robi.setColor(c);
            RobiChangeColor robi= new  RobiChangeColor(Tools.getColorByName(expr.get(2).contents().toString()));
            command= robi;
		} else if (cmd.equals("translate")) {
			//String val1= expr.get(2).contents().toString();
            //String val2= expr.get(3).contents().toString();
            //int res1 = Integer.parseInt(val1);
            //int res2 = Integer.parseInt(val2);
            Robitranslate robi= new Robitranslate(Integer.parseInt(expr.get(2).contents().toString()), Integer.parseInt(expr.get(3).contents().toString()));
            //robi.translate(new Point(res1,res2));
            command = robi;
		} else {
		throw new Error("Invalid robi command:" + expr);
		}
		} else
		throw new Error("Unknown target in command: " + expr);
		return command;
	}

	public static void main(String[] args) {
		new Exercice3_0();
	}

	public interface Command {
		abstract public void run();
	}

	public class SpaceChangeColor implements Command {
		Color newColor;

		public SpaceChangeColor(Color newColor) {
			this.newColor = newColor;
		}

		@Override
		public void run() {
			space.setColor(newColor);
		}

	}
	
	public class RobiChangeColor implements Command {
		Color newColor;

		public RobiChangeColor(Color newColor) {
			this.newColor = newColor;
		}

		@Override
		public void run() {
			robi.setColor(newColor);
		}

	}
	
	public class Spacesleep implements Command {
		int s;

		public Spacesleep(int s) {
			this.s = s;
		}

		@Override
		public void run() {
			try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}

	}
	
	public class Robitranslate implements Command {
		int x, y;
 
		public Robitranslate (int x, int y) {
			this.x=x;
			this.y=y;
		}

		@Override
		public void run() {
			robi.translate(new Point(x,y));
			
		}

	}
	
	
}