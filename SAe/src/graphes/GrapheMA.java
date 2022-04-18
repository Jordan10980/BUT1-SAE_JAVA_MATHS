package graphes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GrapheMA implements IGraph {
	private Map<String, Integer> noeuds;
	private String[] labels; // pour toString
	private boolean[][] mab;
	private int[][] mav;

	public GrapheMA(String[] labels) {
		this.labels = labels.clone();
		this.noeuds = new HashMap<>();
		int nb = labels.length;
		for (int i =0; i< nb; ++i)
			this.noeuds.put(labels[i], i);
		this.mab = new boolean[nb][nb]; 
		this.mav = new int[nb][nb];
	}
	
	public int getNbNoeuds() { return mab.length; }
	
	public boolean estNoeudOK(String label) {
		return noeuds.containsKey(label);
	}
	
	public boolean estArcOK(String n1, String n2) {
		return estNoeudOK(n1) && estNoeudOK(n2);
	}

	public void ajouterArc(String label1, String label2, int valeur) {
		assert ! aArc(label1,label2);
		int n1 = noeuds.get(label1);
		int n2 = noeuds.get(label2);
		mab[n1][n2] = true;
		mav[n1][n2] = valeur;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(String label1 : noeuds.keySet()) {
			s+= label1 + " -> ";
			int n1 = noeuds.get(label1);
			for (int n2=0; n2< getNbNoeuds(); ++n2) {
				if (mab[n1][n2])
					s+= labels[n2] + "("+ mav[n1][n2] + ") ";
			}
			s+="\n";
		}
		return s;
	}

	public boolean aArc(String label1, String label2) {
		assert estArcOK(label1,label2);
		return mab[noeuds.get(label1)][noeuds.get(label2)];
	}
	
	public int getValeur(String label1, String label2) {
		assert estArcOK(label1, label2);
		return mav[noeuds.get(label1)][noeuds.get(label2)];
	}

	public int dOut(String label) {
		assert estNoeudOK(label);
		int n1 = noeuds.get(label);
		int degre = 0;
		for (int n2 = 0; n2 < getNbNoeuds(); ++n2)
			if (mab[n1][n2])
				++degre;
		return degre;
	}

	public int dIn(String label) {
		assert estNoeudOK(label);
		int n2 = noeuds.get(label);
		int degre = 0;
		for (int n1 = 0; n1 < getNbNoeuds(); ++n1)
			if (mab[n1][n2])
				++degre;
		return degre;
	}
	
	@Override
	public List<String> listSuccesseurs(String noeud) {
		List<String> successeurs = new ArrayList<String>();
		int n1 = noeuds.get(noeud);
		for (int n2=0; n2< getNbNoeuds(); ++n2) {
			if (mab[n1][n2])
				successeurs.add(labels[n2]);
		}
		return successeurs;
	}

	@Override
	public Iterator<String> iterator() {
		return noeuds.keySet().iterator();
	}

}
