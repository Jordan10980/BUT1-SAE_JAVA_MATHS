package graphes;

import java.util.List;

public interface IPCC {
	
	/**
	 * Calcule le plus court chemin
	 * @param graphe : graphe sur lequel le programme va travailler
	 * @param sommetDepart : sommet de d�part
	 * @param sommetArrivee : sommet d'arriv�e
	 * @param cheminPlusCourt : le plus court chemin trouv�
	 * @return : la longueur du plus court chemin trouv�
	 */
	int pc(IGraphe graphe, int sommetDepart, int sommetArrivee, List<Integer> cheminPlusCourt);
}
