package tests.graphes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import graphes.IGraphe;
import graphes.types.GrapheLA;
import graphes.types.GrapheMA;

class IGrapheTest {
	private final int nbNoeuds = 9;

	// Graphe de l'exercice 3.1 du poly de maths
	// A = 1, B = 2, ... I = 9
	@Test
	void test() {
		IGraphe g = new GrapheMA(nbNoeuds);
		tester(g);
		g = new GrapheLA(nbNoeuds);
		tester(g);
	}
	
	void tester(IGraphe g) {
		assertEquals(nbNoeuds, g.getNbSommets());
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
		
		assertTrue(g.aArc(1,4));
		assertTrue(g.aArc(2,7));
		assertTrue(g.aArc(5,7));
		assertTrue(g.aArc(5,8));
		assertTrue(g.aArc(8,6));
		
		assertFalse(g.aArc(4,1));
		assertFalse(g.aArc(8,3));
		assertFalse(g.aArc(9,9));
		
		assertEquals(3,g.getValuation(4, 5));
		assertEquals(7,g.getValuation(5, 8));
		assertEquals(10,g.getValuation(9, 8));
		assertEquals(5,g.getValuation(4, 3));
			
		assertEquals(g.toString(),
				  "1 => 3(2) 4(1)\n"
				+ "2 => 7(3)\n"
				+ "3 => 8(2)\n"
				+ "4 => 2(3) 3(5) 5(3)\n"
				+ "5 => 3(1) 7(3) 8(7)\n"
				+ "6 =>\n"
				+ "7 => 2(2) 6(1)\n"
				+ "8 => 6(4) 7(2)\n"
				+ "9 => 8(10)\n"
		 );
	}
}
