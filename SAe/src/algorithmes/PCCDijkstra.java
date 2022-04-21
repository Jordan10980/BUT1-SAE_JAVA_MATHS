package algorithmes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import graphes.IGraph;
import util.Pair;

public class PCCDijkstra {
	
	private IGraph graphe;
	
	public PCCDijkstra(IGraph graphe) {
		this.graphe = graphe;
	}
	
	public List<String> plusCourtChemin(String s1, String s2) {
		String sommetEnCours = s1;
		List<String> listSommetsParcourus = new ArrayList<String>();
		Map<String, Pair<String, Integer>> listSommetsDistance = new HashMap<String, Pair<String, Integer>>();
		
		Iterator<String> itNoeud = graphe.iterator();
		while(itNoeud.hasNext()) {
			String noeud = itNoeud.next();
			listSommetsDistance.put(noeud , null);
		}
		
		listSommetsParcourus.add(sommetEnCours);
		listSommetsDistance.put(sommetEnCours, new Pair<String, Integer>(sommetEnCours, 0));
		
		while(sommetEnCours != s2) {
			List<String> listSuccesseursNonParcourus = recupererSuccesseur(sommetEnCours, listSommetsParcourus);
			mettreAJourListSommetsDistance(listSommetsDistance, sommetEnCours, listSuccesseursNonParcourus);
			sommetEnCours = choisirProchainSommetEnCours(listSommetsDistance, listSommetsParcourus);
			mettreAJourlistSommetsParcourus(listSommetsParcourus,sommetEnCours);
		}
		
		List<String> cheminPlusCourt = new ArrayList<String>();
		sommetEnCours = s2;
		
		while(sommetEnCours != s1) {
			String predecesseur =recupererPredecesseur(sommetEnCours, listSommetsDistance);
			cheminPlusCourt.add(0, predecesseur);
			sommetEnCours = predecesseur;
		}
		
		return cheminPlusCourt;
	}
	
	
	

	private String recupererPredecesseur(String sommetEnCours, Map<String, Pair<String, Integer>> listSommetsDistance) {
		Pair<String, Integer> p = listSommetsDistance.get(sommetEnCours);
		return p.getKey();		
	}

	private void mettreAJourlistSommetsParcourus(List<String> listSommetsParcourus, String sommetEnCours) {
		listSommetsParcourus.add(sommetEnCours);
	}

	private String choisirProchainSommetEnCours(Map<String, Pair<String, Integer>> listSommetsDistance,
			List<String> listSommetsParcourus) {
		// TODO Auto-generated method stub
		return null;
	}

	private void mettreAJourListSommetsDistance(Map<String, Pair<String, Integer>> listSommetsDistance,
			String sommetEnCours, List<String> listSuccesseursNonParcourus) {
		// TODO Auto-generated method stub
		
	}

	private List<String> recupererSuccesseur(String sommetEnCours, List<String> listSommetsParcourus) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean estDejaParcouru(String noeud, List<String> noeudsParcourus) {
		for(int i=0; i < noeudsParcourus.size(); i++) {
			if(noeud.equals(noeudsParcourus.get(i)) == true ) {
				return true;
			}
		}
		return false;
	}
	
	
}







//private String plusPetitSuccesseur(String n1, List<String> noeudsParcourus) {
//List<String> successeurs = graphe.listSuccesseurs(n1);
//if(successeurs.size() == 0) {
//	return null;
//}
//int i = 0;
//while(estDejaParcouru(successeurs.get(i), noeudsParcourus) && i < successeurs.size()) {
//	i++;
//}
//if(i == successeurs.size()) {
//	return null;
//}
//String plusPetitS = successeurs.get(i);
//int plusPetitV = graphe.getValeur(n1, successeurs.get(i));
//for(int j=i+1; j<successeurs.size(); j++) {
//	int v = graphe.getValeur(n1, successeurs.get(j));
//	if(v < plusPetitV && !estDejaParcouru(successeurs.get(j), noeudsParcourus)) {
//		plusPetitV = v;
//		plusPetitS = successeurs.get(j);
//	}
//}
//return plusPetitS;	
//}