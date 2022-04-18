package algorithmes;

import java.util.ArrayList;
import java.util.List;

import graphes.IGraph;

public class PCCDijkstra {
	
	private IGraph graphe;
	
	public PCCDijkstra(IGraph graphe) {
		this.graphe = graphe;
	}
	
	public String plusCourtChemin(String n1, String n2) {
		List<String> noeudsParcourus = new ArrayList<String>();	
		
		
		
		
		String plusPetitSuccesseur = plusPetitSuccesseur(n1, noeudsParcourus);
		noeudsParcourus.add(plusPetitSuccesseur);
		
		
		
		
		return plusPetitSuccesseur(n1, noeudsParcourus);
	}
	
	
	
	private String plusPetitSuccesseur(String n1, List<String> noeudsParcourus) {
		List<String> successeurs = graphe.listSuccesseurs(n1);
		if(successeurs.size() == 0) {
			return null;
		}
		int i = 0;
		while(estDejaParcouru(successeurs.get(i), noeudsParcourus) && i < successeurs.size()) {
			i++;
		}
		if(i == successeurs.size()) {
			return null;
		}
		String plusPetitS = successeurs.get(i);
		int plusPetitV = graphe.getValeur(n1, successeurs.get(i));
		for(int j=i+1; j<successeurs.size(); j++) {
			int v = graphe.getValeur(n1, successeurs.get(j));
			if(v < plusPetitV && !estDejaParcouru(successeurs.get(j), noeudsParcourus)) {
				plusPetitV = v;
				plusPetitS = successeurs.get(j);
			}
		}
		return plusPetitS;	
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
