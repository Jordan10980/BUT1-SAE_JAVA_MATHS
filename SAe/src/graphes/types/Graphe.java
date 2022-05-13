package graphes.types;

import graphes.IGraphe;

public abstract class Graphe implements IGraphe {
	
	public boolean estNoeudOK(int n) {
		return n >= 1 && n <= getNbSommets();
	}
	
	public boolean estArcOK(int a, int b) {
		return estNoeudOK(a) && estNoeudOK(b);
	}
	
	@Override
	public boolean aArc(int a, int b) {
		return getValuation(a,b) != INFINI;
	}
	
}