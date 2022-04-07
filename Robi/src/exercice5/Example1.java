package exercice5;



public class Example1 {
	
	String script = "(space add robi (rect.class new))"
					+"(space.robi setColor white) "
					+ "(space.robi setDim 100 100) "
					+"(space.robi translate 20 10) "
					+"(space.robi add im (image.class new alien.gif)) "
					+"(space.robi.im translate 20 20) "
					;
	
	
	public  void launch() {
		Exercice5 exo = new Exercice5();
		System.out.println("Script >> "+script);
		exo.oneShot(script);
	}
	
	public static void main(String[] args) {
		new Example1().launch();
	}
}
  