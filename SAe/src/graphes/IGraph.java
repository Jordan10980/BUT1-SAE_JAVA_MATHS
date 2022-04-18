package graphes;
import java.util.Iterator;
import java.util.List;

public interface IGraph extends Iterable<String> {
	public int getNbNoeuds();
	void ajouterArc(String label1, String label2, int valeur);
	boolean aArc(String label1, String label2);
	int getValeur(String n1, String n2);
	int dOut(String label);
	int dIn(String label);
	Iterator<String> iterator();
	List<String> listSuccesseurs(String noeud);
}
