
public class testvif {

	public static void main(String[] args) {
		GrapheMA g = new GrapheMA(6);
		System.out.println(g);
		
		g.ajouterArc(1,2);
		g.ajouterArc(1,3);
		g.ajouterArc(1,4);
		g.ajouterArc(1,5);
		g.ajouterArc(2,5);
		g.ajouterArc(4,4);
		g.ajouterArc(5,1);

		
		System.out.println(g);
		
		System.out.println(g.aArc(1,5));
		System.out.println(g.aArc(4,4));
		System.out.println(g.aArc(5,1));
		
		// Problème ici
		System.out.println(g.aArc(4,1));
		System.out.println(g.aArc(6,6));

	}

}
