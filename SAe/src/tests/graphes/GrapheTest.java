package tests.graphes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import algorithmes.PCCDijkstra;

import graphes.GrapheLA;
import graphes.GrapheMA;
import graphes.IGraph;

class GrapheTest {
	
	// Graphe de l'exercice 3.1 du poly de maths
	@Test
	void test() {
		String[] noeuds = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		IGraph g = new GrapheMA(noeuds);
		tester(g);
		g = new GrapheLA(noeuds);
		tester(g);
	}
	
	void tester(IGraph g) {
		assertEquals(9, g.getNbNoeuds());
		g.ajouterArc("A","C",2);
		g.ajouterArc("A","D",1);
		g.ajouterArc("B","G",3);
		g.ajouterArc("C","H",2);
		g.ajouterArc("D","B",3);
		g.ajouterArc("D","C",5);
		g.ajouterArc("D","E",3);
		g.ajouterArc("E","C",1);
		g.ajouterArc("E","G",3);
		g.ajouterArc("E","H",7);
		g.ajouterArc("G","B",2);
		g.ajouterArc("G","F",1);
		g.ajouterArc("H","F",4);
		g.ajouterArc("H","G",2);
		g.ajouterArc("I","H",10);
		
		assertTrue(g.aArc("A","D"));
		assertTrue(g.aArc("B","G"));
		assertTrue(g.aArc("E","G"));
		assertTrue(g.aArc("E","H"));
		assertTrue(g.aArc("H","F"));
		
		assertFalse(g.aArc("D","A"));
		assertFalse(g.aArc("H","C"));
		assertFalse(g.aArc("I","I"));
		
		assertEquals(2,g.dOut("A"));
		assertEquals(1,g.dOut("B"));
		assertEquals(1,g.dOut("C"));
		assertEquals(3,g.dOut("D"));
		assertEquals(0,g.dOut("F"));
		assertEquals(1,g.dOut("I"));
		
		assertEquals(0,g.dIn("A"));
		assertEquals(2,g.dIn("B"));
		assertEquals(2,g.dIn("F"));
		assertEquals(0,g.dIn("I"));
		
		assertEquals(3,g.getValeur("D", "E"));
		assertEquals(7,g.getValeur("E", "H"));
		assertEquals(10,g.getValeur("I", "H"));
		assertEquals(5,g.getValeur("D", "C"));
		
		System.out.println(g);
		
		StringBuffer s = new StringBuffer();
		for (String noeud : g)
			s.append(noeud + " ");
		System.out.println(s);
		
		
		PCCDijkstra d = new PCCDijkstra(g);
		System.out.println(d.plusCourtChemin("E", "A"));
	}	

}
