package graphes;

import java.util.List;

public interface IPCC {
	
	/**
	 * Calcule le plus court chemin
	 * @param graphe : graphe sur lequel le programme va travailler
	 * @param sommetDepart : sommet de départ
	 * @param sommetArrivee : sommet d'arrivée
	 * @param cheminPlusCourt : le plus court chemin trouvé
	 * @return : la longueur du plus court chemin trouvé
	 */
	int pc(IGraphe graphe, int sommetDepart, int sommetArrivee, List<Integer> cheminPlusCourt);
}
