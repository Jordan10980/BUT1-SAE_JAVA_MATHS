package algorithmes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	private String choisirProchainSommetEnCours(Map<String, Pair<String, Integer>> listSommetsDistance, List<String> listSommetsParcourus) {
		Set<String> set = listSommetsDistance.keySet();
		Iterator<String> itNoeud = set.iterator();
		int plusPetit=0;
		String plusPetitNoeud = null;
		while(itNoeud.hasNext()) {
			String noeud = itNoeud.next();
			int d = listSommetsDistance.get(noeud).getValue();
			if(! estDejaParcouru(noeud, listSommetsParcourus)) {
				if( plusPetitNoeud == null || d < plusPetit) {
					plusPetit = d;
					plusPetitNoeud = noeud;
				}
			}
		}
		return plusPetitNoeud;
	}

	private void mettreAJourListSommetsDistance(Map<String, Pair<String, Integer>> listSommetsDistance, String sommetEnCours, List<String> listSuccesseursNonParcourus) {
		for(int i = 0; i < listSuccesseursNonParcourus.size(); i++) {
			int d = listSommetsDistance.get(sommetEnCours).getValue() + graphe.getValeur(sommetEnCours, listSuccesseursNonParcourus.get(i));
			if(listSommetsDistance.get(listSuccesseursNonParcourus.get(i)) == null) {
				Pair<String, Integer> p = new Pair<String, Integer>(sommetEnCours, d);
				listSommetsDistance.put(listSuccesseursNonParcourus.get(i), p);
			} else {
				if(listSommetsDistance.get(listSuccesseursNonParcourus.get(i)).getValue() > d) {
					Pair<String, Integer> p = new Pair<String, Integer>(sommetEnCours, d);
					listSommetsDistance.put(listSuccesseursNonParcourus.get(i), p);
				}
			}
		}
	}

	private List<String> recupererSuccesseur(String sommetEnCours, List<String> listSommetsParcourus) {
		List<String> listSuccesseurs = graphe.listSuccesseurs(sommetEnCours);
		List<String> listSuccesseursNonParcourus = new ArrayList<String>(); 
		for(int i = 0; i < listSuccesseurs.size(); i++) {
			int k = 0;
			for(int j = 0; j < listSommetsParcourus.size(); j++) {
				if(listSuccesseurs.get(i).equals(listSommetsParcourus.get(j))) {
					break;
				}
				else {
					k++;
				}
			}
			if(k == listSommetsParcourus.size()) {
				listSuccesseursNonParcourus.add(listSuccesseurs.get(i));
			}
		}	
		return listSuccesseursNonParcourus;
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
