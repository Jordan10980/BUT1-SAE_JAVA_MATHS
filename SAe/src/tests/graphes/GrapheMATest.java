package tests.graphes;

import org.junit.jupiter.api.Test;

import graphes.IGraphe;
import graphes.types.GrapheMA;

class GrapheMATest extends GrapheTestAbstract{

	@Test
	protected void testDistance() {
		super.testDistance();
	}

	@Test
	protected void testArc() {
		super.testArc();
	}

	@Test
	protected void testToString() {
		super.testToString();
	}

	@Test
	protected void testListSuccesseur() {
		super.testListSuccesseur();
	}

	@Test
	protected void testListPredecesseur() {
		super.testListPredecesseur();
	}
	
	@Override
	protected IGraphe getGraphe(int nbSommet) {
		return new GrapheMA(nbSommet);
	}

}
