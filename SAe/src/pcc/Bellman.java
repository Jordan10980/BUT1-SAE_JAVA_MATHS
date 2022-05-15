package pcc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import exceptions.NoPathEx;
import graphes.IGraphe;
import graphes.IPCC;

public class Bellman implements IPCC {

	@Override
	public int pc(IGraphe graphe, int sommetDepart, int sommetArrivee, List<Integer> cheminPlusCourt) {
		Map<Integer, Integer> distancesBellman = new HashMap<Integer, Integer>();
		Map<Integer, Integer> predecesseursBellman= new HashMap<Integer, Integer>();
		
		for(int i=0; i<graphe.getNbSommets(); i++)
			distancesBellman.put(i+1,IGraphe.INFINI);
		distancesBellman.put(sommetDepart,0);
		
		List<Integer> ordreTopologique = calculerOrdreTopologique(graphe);
		
		for(int i=0; i < ordreTopologique.size(); i++) {
			int sommetEnCours = ordreTopologique.get(i);
			if(sommetEnCours != sommetDepart) {
				List<Integer> predecesseurs = graphe.listPredecesseur(sommetEnCours);
				int distanceMin = IGraphe.INFINI;
				int predecesseurMin = IGraphe.INFINI;
				for(int predecesseur: predecesseurs) {
					if(distancesBellman.get(predecesseur) !=  IGraphe.INFINI) {
						int distance = distancesBellman.get(predecesseur) + graphe.getValuation(predecesseur, sommetEnCours);
						if(distance < distanceMin) {
							distanceMin = distance;
							predecesseurMin = predecesseur;
						}
					}
				}
				if(distanceMin != IGraphe.INFINI) {
					distancesBellman.put(sommetEnCours, distanceMin);
					predecesseursBellman.put(sommetEnCours,predecesseurMin );
				}
			}
			
			if(sommetEnCours == sommetArrivee) break;
		}
		
		if(predecesseursBellman.get(sommetArrivee) == null)
			throw new NoPathEx();
		
		int sommetEnCours = sommetArrivee;
		while(sommetEnCours !=  sommetDepart) {
			cheminPlusCourt.add(0, sommetEnCours);
			sommetEnCours = predecesseursBellman.get(sommetEnCours);
		}
		cheminPlusCourt.add(0, sommetEnCours);

		return distancesBellman.get(sommetArrivee);
	}

	private List<Integer> calculerOrdreTopologique(IGraphe graphe) {
		
		Map<Integer, Integer> nbPredecesseurParSommet = new HashMap<Integer, Integer>();
		for(int i=0 ; i < graphe.getNbSommets(); i++) {
			int sommetEnCours = i+1;
			int nbPredecesseur = graphe.listPredecesseur(sommetEnCours).size();
			nbPredecesseurParSommet.put(sommetEnCours, nbPredecesseur);
		}
		
		List<Integer> ordreTopologique = new ArrayList<Integer>();
		while(nbPredecesseurParSommet.size() != 0) {
			List<Integer> sommetsASupprimer  = new ArrayList<Integer>();
			Iterator<Integer> itKeySet = nbPredecesseurParSommet.keySet().iterator();
			while (itKeySet.hasNext()) {
				Integer sommet = itKeySet.next();
				if(nbPredecesseurParSommet.get(sommet) == 0) {
					ordreTopologique.add(sommet);
					List<Integer> listeSuccesseur = graphe.listSuccesseurs(sommet);
					for(int successeur:listeSuccesseur) {
						nbPredecesseurParSommet.put(successeur, nbPredecesseurParSommet.get(successeur) -1);
					}
					sommetsASupprimer.add(sommet);
				}
			}
			for(int sommet:sommetsASupprimer) {
				nbPredecesseurParSommet.remove(sommet);
			}
		}
				
		return ordreTopologique;
	}
}
