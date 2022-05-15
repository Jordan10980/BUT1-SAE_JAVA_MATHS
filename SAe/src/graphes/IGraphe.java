package graphes;

import java.util.List;

public interface IGraphe {
	final static int INFINI = Integer.MAX_VALUE;
	
	/**
	 * revoie le nombre de sommet
	 * @return le nombre de sommet
	 */
	int getNbSommets();
	
	
	/**
	 * ajoute un arc au graphe
	 * @param source : sommet de d�part
	 * @param valuation : la valeur de l'arc
	 * @param destination : sommet d'arriv�e
	 */
	void ajouterArc(int source, int valuation, int destination);
	
	/**
	 * revoie la valeur de l'arc
	 * @param i : sommet de d�part
	 * @param j : sommet d'arriver
	 * @return la valeur de l'arc
	 */
	int getValuation(int i, int j);
	
	/**
	 * Savoir si oui ou non il y a un arc entre deux sommets
	 * @param i : sommet de d�part
	 * @param j : sommet d'arriv�e
	 * @return true ou false en focntion de s'il y a un arc ou pas
	 */
	boolean aArc(int i, int j);
	
	/**
	 * Savoir les successeurs d'un sommet
	 * @param noeud : sommet sur lequel on veut avoir les successeurs
	 * @return une liste de successeurs
	 */
	List<Integer> listSuccesseurs(int noeud);
	
	/**
	 * Savoir les pr�d�cesseurs d'un sommet
	 * @param noeud : sommet sur lequel on veut avoir les pr�d�cesseurs
	 * @return : une liste de pr�d�cesseurs
	 */
	List<Integer> listPredecesseur(int noeud);
	
	/**
	 * Savoir la distance d'un chemin
	 * @param cheminCalcule : list de sommet sur laquel on veut la distance
	 * @return : la distance du chemin
	 */
	int distance(List<Integer> cheminCalcule);
}
