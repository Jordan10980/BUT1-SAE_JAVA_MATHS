package graphes;

import java.util.List;

public interface IPCC {
	
	// calcule le plus court chemin
	int pc(IGraphe graphe, int sommetDepart, int sommetArrivee, List<Integer> cheminPlusCourt);
}
