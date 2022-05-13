package graphes;

import java.util.List;

public interface IPCC {

	int pc(IGraphe graphe, int sommetDepart, int sommetArrivee, List<Integer> cheminPlusCourt);
}
