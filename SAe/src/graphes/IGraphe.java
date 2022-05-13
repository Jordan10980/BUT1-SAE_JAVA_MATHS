package graphes;

import java.util.List;

public interface IGraphe {
	final static int INFINI = Integer.MAX_VALUE;

	int getNbSommets();
	void ajouterArc(int source, int valuation, int destination);
	int getValuation(int i, int j);
	boolean aArc(int i, int j);
	List<Integer> listSuccesseurs(int noeud);
	int distance(List<Integer> cheminCalcule);
}
