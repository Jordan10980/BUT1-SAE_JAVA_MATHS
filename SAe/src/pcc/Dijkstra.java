package pcc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graphes.IGraphe;
import graphes.IPCC;
import util.Pair;

public class Dijkstra implements IPCC {
	
	public Dijkstra() {
		
	}
	
	@Override
	public int pc(IGraphe graphe, int sommetDepart, int sommetArrivee, List<Integer> cheminPlusCourt) {
		int sommetEnCours = sommetDepart;
		List<Integer> listSommetsParcourus = new ArrayList<Integer>();
		Map<Integer, Pair<Integer, Integer>> listSommetsDistance = new HashMap<Integer, Pair<Integer, Integer>>();
		
		for(int i=0; i< graphe.getNbSommets() ; i++) {
			listSommetsDistance.put(i+1 , null);
		}
		
		listSommetsParcourus.add(sommetEnCours);
		listSommetsDistance.put(sommetEnCours, new Pair<Integer, Integer>(sommetEnCours, 0));
		
		while(sommetEnCours != sommetArrivee) {
			List<Integer> listSuccesseursNonParcourus = recupererSuccesseur(graphe, sommetEnCours, listSommetsParcourus);
			mettreAJourListSommetsDistance(graphe, listSommetsDistance, sommetEnCours, listSuccesseursNonParcourus);
			sommetEnCours = choisirProchainSommetEnCours(listSommetsDistance, listSommetsParcourus);
			mettreAJourlistSommetsParcourus(listSommetsParcourus,sommetEnCours);
		}
		
		sommetEnCours = sommetArrivee;
		
		while(sommetEnCours != sommetDepart) {
			int predecesseur =recupererPredecesseur(graphe, sommetEnCours, listSommetsDistance);
			cheminPlusCourt.add(0, predecesseur);
			sommetEnCours = predecesseur;
		}
		
		return 0;
	}
	

	private int recupererPredecesseur(IGraphe graphe, int sommetEnCours, Map<Integer, Pair<Integer, Integer>> listSommetsDistance) {
		Pair<Integer, Integer> p = listSommetsDistance.get(sommetEnCours);
		return p.getKey();		
	}

	private void mettreAJourlistSommetsParcourus(List<Integer> listSommetsParcourus, int sommetEnCours) {
		listSommetsParcourus.add(sommetEnCours);
	}

	private int choisirProchainSommetEnCours(Map<Integer, Pair<Integer, Integer>> listSommetsDistance, List<Integer> listSommetsParcourus) {
		Set<Integer> set = listSommetsDistance.keySet();
		Iterator<Integer> itNoeud = set.iterator();
		int plusPetit=0;
		int plusPetitNoeud = -1;
		while(itNoeud.hasNext()) {
			int noeud = itNoeud.next();
			if(listSommetsDistance.get(noeud) != null) {
				int d = listSommetsDistance.get(noeud).getValue();
				if(! estDejaParcouru(noeud, listSommetsParcourus)) {
					if( plusPetitNoeud == -1|| d < plusPetit) {
						plusPetit = d;
						plusPetitNoeud = noeud;
					}
				}
			}
		}
		return plusPetitNoeud;
	}

	private void mettreAJourListSommetsDistance(IGraphe graphe, Map<Integer, Pair<Integer, Integer>> listSommetsDistance, Integer sommetEnCours, List<Integer> listSuccesseursNonParcourus) {
		for(int i = 0; i < listSuccesseursNonParcourus.size(); i++) {
			int d = listSommetsDistance.get(sommetEnCours).getValue() + graphe.getValuation(sommetEnCours, listSuccesseursNonParcourus.get(i));
			if(listSommetsDistance.get(listSuccesseursNonParcourus.get(i)) == null) {
				Pair<Integer, Integer> p = new Pair<Integer, Integer>(sommetEnCours, d);
				listSommetsDistance.put(listSuccesseursNonParcourus.get(i), p);
			} else {
				if(listSommetsDistance.get(listSuccesseursNonParcourus.get(i)).getValue() > d) {
					Pair<Integer, Integer> p = new Pair<Integer, Integer>(sommetEnCours, d);
					listSommetsDistance.put(listSuccesseursNonParcourus.get(i), p);
				}
			}
		}
	}

	private List<Integer> recupererSuccesseur(IGraphe graphe, int sommetEnCours, List<Integer> listSommetsParcourus) {
		List<Integer> listSuccesseurs = graphe.listSuccesseurs(sommetEnCours);
		List<Integer> listSuccesseursNonParcourus = new ArrayList<Integer>(); 
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

	private boolean estDejaParcouru(int noeud, List<Integer> noeudsParcourus) {
		for(int i=0; i < noeudsParcourus.size(); i++) {
			if(noeud == noeudsParcourus.get(i)) {
				return true;
			}
		}
		return false;
	}

	
}
