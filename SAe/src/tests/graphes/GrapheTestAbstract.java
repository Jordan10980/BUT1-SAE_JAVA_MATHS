package tests.graphes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import graphes.IGraphe;
import graphes.types.GrapheLA;
import graphes.types.GrapheMA;

public abstract  class GrapheTestAbstract {
	private final int nbNoeuds = 9;

	protected abstract IGraphe getGraphe(int nbSommet);
	
	private IGraphe construireGraphe9Noeuds() {
		// Graphe de l'exercice 3.1 du poly de maths
		// A = 1, B = 2, ... I = 9
		IGraphe g = getGraphe(nbNoeuds);
		g.ajouterArc(1,2,3);
		g.ajouterArc(1,1,4);
		g.ajouterArc(2,3,7);
		g.ajouterArc(3,2,8);
		g.ajouterArc(4,3,2);
		g.ajouterArc(4,5,3);
		g.ajouterArc(4,3,5);
		g.ajouterArc(5,1,3);
		g.ajouterArc(5,3,7);
		g.ajouterArc(5,7,8);
		g.ajouterArc(7,2,2);
		g.ajouterArc(7,1,6);
		g.ajouterArc(8,4,6);
		g.ajouterArc(8,2,7);
		g.ajouterArc(9,10,8);

		return g;
	}

	private String getToStringGraphe9Noeuds() {
		return "1 => 3(2) 4(1)\n"
				+ "2 => 7(3)\n"
				+ "3 => 8(2)\n"
				+ "4 => 2(3) 3(5) 5(3)\n"
				+ "5 => 3(1) 7(3) 8(7)\n"
				+ "6 =>\n"
				+ "7 => 2(2) 6(1)\n"
				+ "8 => 6(4) 7(2)\n"
				+ "9 => 8(10)\n";
	}
	
	
	// @Test
	protected void testDistance() {
		IGraphe g = construireGraphe9Noeuds();
		
		List<Integer> chemin;
		int distance;
		
		// Test d'un petit chemin
		chemin = new ArrayList<Integer>();
		chemin.add(1);chemin.add(3);chemin.add(8);chemin.add(6);
		distance = g.distance(chemin);
		assertEquals(distance, 8);

		// Test d'un grand chemin
		chemin = new ArrayList<Integer>();
		chemin.add(1);chemin.add(4);chemin.add(5);chemin.add(8);chemin.add(7);chemin.add(2);chemin.add(7);chemin.add(6);
		distance = g.distance(chemin);
		assertEquals(distance, 19);

		// Test d'un chemin impossible
		chemin = new ArrayList<Integer>();
		chemin.add(1);chemin.add(4);chemin.add(5);chemin.add(2);
		distance = g.distance(chemin);
		assertEquals(distance, IGraphe.INFINI);

	}
	
	// @Test
	protected void testArc() {
		IGraphe g = construireGraphe9Noeuds();

		// Test du nombre de sommet
		assertEquals(nbNoeuds, g.getNbSommets());

		// Test présence des arc dans le graphe
		assertTrue(g.aArc(1,4));
		assertTrue(g.aArc(2,7));
		assertTrue(g.aArc(5,7));
		assertTrue(g.aArc(5,8));
		assertTrue(g.aArc(8,6));
		
		// Test non présence des arc dans le graphe
		assertFalse(g.aArc(4,1));
		assertFalse(g.aArc(8,3));
		assertFalse(g.aArc(9,9));
		
		// Test sur les valuations des arc dans le graphe
		assertEquals(3,g.getValuation(4, 5));
		assertEquals(7,g.getValuation(5, 8));
		assertEquals(10,g.getValuation(9, 8));
		assertEquals(5,g.getValuation(4, 3));
		
	}
	
	// @Test
	protected void testToString() {
		IGraphe g = construireGraphe9Noeuds();
		assertEquals(g.toString(), getToStringGraphe9Noeuds());
	}	

	// @Test
	protected void testListSuccesseur() {
		IGraphe g = construireGraphe9Noeuds();

		List<Integer> successeurs, successeursAttendus;

		// On test les successeurs du noeud 4
		successeursAttendus = new ArrayList<Integer>();
		successeursAttendus.add(2);successeursAttendus.add(3);successeursAttendus.add(5);
		successeurs = g.listSuccesseurs(4);
		assert comparerList(successeurs, successeursAttendus);

		// On test les successeurs du noeud 5
		successeursAttendus = new ArrayList<Integer>();
		successeursAttendus.add(3);successeursAttendus.add(7);successeursAttendus.add(8);
		successeurs = g.listSuccesseurs(5);
		assert comparerList(successeurs, successeursAttendus);

		// On test les successeurs du noeud 6 (pas de successeurs)
		successeursAttendus = new ArrayList<Integer>(); 
		successeurs = g.listSuccesseurs(6);
		assert comparerList(successeurs, successeursAttendus);
	
	}

	// @Test
	protected void testListPredecesseur() {
		IGraphe g = construireGraphe9Noeuds();

		List<Integer> predecesseurs, predecesseursAttendus;

		// On test les predecesseurs du noeud 8
		predecesseursAttendus = new ArrayList<Integer>();
		predecesseursAttendus.add(3);predecesseursAttendus.add(5);predecesseursAttendus.add(9);
		predecesseurs = g.listPredecesseur(8);
		assert comparerList(predecesseurs, predecesseursAttendus);

		// On test les predecesseurs du noeud 7
		predecesseursAttendus = new ArrayList<Integer>();
		predecesseursAttendus.add(2);predecesseursAttendus.add(5);predecesseursAttendus.add(8);
		predecesseurs = g.listPredecesseur(7);
		assert comparerList(predecesseurs, predecesseursAttendus);

		// On test les predecesseurs du noeud 1 (pas de predecesseurs)
		predecesseursAttendus = new ArrayList<Integer>(); 
		predecesseurs = g.listPredecesseur(1);
		assert comparerList(predecesseurs, predecesseursAttendus);
	
	}

	
	
	private boolean comparerList(List<Integer> successeurs, List<Integer> successeursAttendus) {
		for(int noeud: successeurs) 
			if(! successeursAttendus.contains(noeud)) return false;
		for(int noeud: successeursAttendus)
			if(! successeurs.contains(noeud)) return false;
		return true;
	}	

}
