package graphes;

import java.util.List;

public interface IGraphe {
	final static int INFINI = Integer.MAX_VALUE;

	// renvoie le nombre de sommet
	int getNbSommets();
	
	// ajoute un arc au graphe
	void ajouterArc(int source, int valuation, int destination);
	
	// renvoie la valeur d'un arc entre dexu sommets
	int getValuation(int i, int j);
	
	// revoie si oui ou non il y a un arc entre les deux sommets
	boolean aArc(int i, int j);
	
	// renvoie la liste des successeurs
	List<Integer> listSuccesseurs(int noeud);
	
	// renvoie la liste des prédecesseurs 
	List<Integer> listPredecesseur(int noeud);
	
	// renvoie la distance du chemin envoyé
	int distance(List<Integer> cheminCalcule);
}
