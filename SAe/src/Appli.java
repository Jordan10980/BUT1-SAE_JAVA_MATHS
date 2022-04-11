
public class Appli {

	public static void main(String[] args) {
		
		IGraph g1 = new GrapheMA(4);
		g1.ajouterArc(1, 3);
		
		Dijkstra d1 = new Dijkstra();
		d1.plusCourtChemin(1, 3, g1);

	}

}
