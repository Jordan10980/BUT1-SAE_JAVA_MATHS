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
		
		// On initialise le tableau de Dijkstra avec l'ensemble des noeud du graphe
		// au depart chaque noeud n'a pas encore de predecesseur défini (pair <noeud,distance>)
		for(int i=0; i< graphe.getNbSommets() ; i++) {
			listSommetsDistance.put(i+1 , null);
		}
		
		// On initialise la liste des sommets parcourus avec le sommet de depart
		listSommetsParcourus.add(sommetEnCours);
		// On ajoute le noeud le départ dans le tableau de Dijkstra avec un predecesseur égal à lui même et une distance de 0
		listSommetsDistance.put(sommetEnCours, new Pair<Integer, Integer>(sommetEnCours, 0));
		
		// Tant que le sommet en cours n'est pas le sommet d'arrivee, on poursuit l'algorithme de Dijkstra
		while(sommetEnCours != sommetArrivee) {
			// On récupère les sucesseurs du noeud en cours en excluant ceux par lequel on est déjà passé
			List<Integer> listSuccesseursNonParcourus = recupererSuccesseur(graphe, sommetEnCours, listSommetsParcourus);

			// On met à jout le tableau de disktra pour les successeurs trouvés
			mettreAJourListSommetsDistance(graphe, listSommetsDistance, sommetEnCours, listSuccesseursNonParcourus);
			
			// A l'aide du tableau de dijkstra, on sélectionne le prochain sommet en cours parmis les noeuds non encore parcouru 
			sommetEnCours = choisirProchainSommetEnCours(listSommetsDistance, listSommetsParcourus);

			// On ajoute le nouveau sommet en cours dans la liste des sommets parcourus
			mettreAJourlistSommetsParcourus(listSommetsParcourus,sommetEnCours);
		}
		
		// On reparcours le tableau de Dijsktra en partant du sommet d'arrivée jusqu'au sommet de départ 
		// pour reconstituer le chemin le plus cours
		sommetEnCours = sommetArrivee;
		while(sommetEnCours != sommetDepart) {
			int predecesseur = recupererPredecesseur(sommetEnCours, listSommetsDistance);
			cheminPlusCourt.add(0, predecesseur);
			sommetEnCours = predecesseur;
		}
		
		return 0;
	}
	
	/**
	 * Recupere le predecesseur d'une noeud dans le tableau de Dijkstra.
	 * 
	 * @param sommet le sommet pour lequel recherche le predecesseur 
	 * @param listSommetsDistance le tableau de Dijkstra
	 * @return le predecesseur trouve
	 */
	private int recupererPredecesseur(int sommet, Map<Integer, Pair<Integer, Integer>> listSommetsDistance) {
		// Le predecesseur est la clef de la paire associée au noeud.
		Pair<Integer, Integer> p = listSommetsDistance.get(sommet);
		return p.getKey();		
	}

	/**
	 * Ajout un noeud à la liste des sommets parcouru
	 * 
	 * @param listSommetsParcourus la liste des sommets déjà parcourus dans l'algorithme de Dijkstra
	 * @param sommetEnCours le noeud à ajouter
	 */
	private void mettreAJourlistSommetsParcourus(List<Integer> listSommetsParcourus, int sommetEnCours) {
		listSommetsParcourus.add(sommetEnCours);
	}

	
	/**
	 * On choisis le prochain sommet à parcourir en s'appuyant sur le tableau de Dijkstra.
	 * Il s'agit du sommet qui a la plus petite distance définie (en ignorant les sommets déjà parcourus).
	 * 
	 * @param listSommetsDistance le tableau de Dijkstra
	 * @param listSommetsParcourus la liste des sommets déjà parcouru dans l'algorithme de Dijkstra
	 * @return le prochain sommet à parcourir
	 */
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

	/**
	 * Le mise à jour du tableau de Dijkstra s'appuie sur la règle suivante.<BR>
	 * "Si<BR> 
	 * &nbsp;&nbsp; la distance déjà enregistrée (dans le tableau) pour un sucesseur est plus grande que la distance enregistré pour le sommet en cours + la valuation vers le sucesseur.<BR>
	 * alors <BR>
	 * &nbsp;&nbsp; on met à jour cette distance (et son predecesseur)" 
	 * @param graphe le graphe
	 * @param listSommetsDistance le tableau de Dijkstra
	 * @param sommetEnCours le sommet en cours
	 * @param listSuccesseursNonParcourus : la liste des successeurs pour lesquels on est succeptible de mettre à jour le tableau de Dijkstra
	 */
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

	/**
	 * Récupération des successeurs d'un noeud en omettant les sommet déjà parcourus.
	 * 
	 * @param graphe le graphe
	 * @param sommetEnCours le sommet en cours
	 * @param listSommetsParcourus la liste des sommets déjà parcourus dans l'algorithme de Dijkstra
	 * @return la liste des successeurs non encore parcourus du sommet en cours 
	 */
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

	/**
	 * Permets de savoir si un noeud est déjà parcourus ans l'algorithme de Dijkstra ou pas
	 * @param noeud
	 * @param noeudsParcourus
	 * @return True si le noeud est déjà parcouru. False sinon
	 */
	private boolean estDejaParcouru(int noeud, List<Integer> noeudsParcourus) {
		for(int i=0; i < noeudsParcourus.size(); i++) {
			if(noeud == noeudsParcourus.get(i)) {
				return true;
			}
		}
		return false;
	}

	
}
