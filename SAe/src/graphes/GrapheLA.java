package graphes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class GrapheLA implements IGraph{

	private Map<String, Integer> noeuds;
	private ArrayList<ArrayList<Arc>> la;
	
	private static class Arc  {
		String cible;
		int valeur;
		Arc(String cible, int valeur) {
			this.cible = cible;
			this.valeur = valeur;
		}
		@Override
		public boolean equals(Object ob) {
			if (this == ob)
				return true;
			if (ob.getClass() != Arc.class)
				return false;
			Arc a = (Arc) ob;
			return a.cible.equals(this.cible); // on ne tient PAS compte de la valeur
			// afin de faciliter l'ecriture de la methode aArc
			// qui cherche si un arc avec la meme cible existe peu importe la valeur
			// en utilisant contains sur une liste d'arcs or contains appelle
			// cette methode equals
		}
	}

	public GrapheLA(String[] labels) {
		this.noeuds = new HashMap<>();
		int nb = labels.length;
		for (int i =0; i< nb; ++i)
			this.noeuds.put(labels[i], i);
		la = new ArrayList<>();
		for (int i = 0; i < nb; ++i)
			la.add(new ArrayList<>());
	}
	@Override
	public int getNbNoeuds() {
		return la.size();
	}
	
	public boolean estNoeudOK(String label) {
		return noeuds.containsKey(label);
	}
	
	public boolean estArcOK(String n1, String n2) {
		return estNoeudOK(n1) && estNoeudOK(n2);
	}
	@Override
	public void ajouterArc(String label1, String label2, int valeur) {
		assert ! aArc(label1,label2);
		int n1 = noeuds.get(label1);
		la.get(n1).add(new Arc(label2, valeur));
	}
	@Override
	public boolean aArc(String label1, String label2) {
		assert estArcOK(label1,label2);
		int n1 = noeuds.get(label1);
		return la.get(n1).contains(new Arc(label2, 0));
	}
	
	@Override
	public String toString() {
		String s = "";
		for(String label1 : noeuds.keySet()) {
			s+= label1 + " -> ";
			for (Arc a : la.get(noeuds.get(label1)))
				s+= a.cible + "("+ a.valeur + ") ";
			s+="\n";
		}
		return s;
	}
	@Override
	public int dOut(String label) {
		assert estNoeudOK(label);
		return la.get(noeuds.get(label)).size();
	}
	@Override
	public int dIn(String label) {
		assert estNoeudOK(label);
		int d = 0;
		Arc a = new Arc(label, 0);
		for(int i = 0; i< la.size(); ++i)
			if (la.get(i).contains(a))
				++d;
		return d;
	}

	@Override
	public int getValeur(String n1, String n2) {
		assert aArc(n1, n2);
		for (Arc a : la.get(noeuds.get(n1)))
			if (a.cible.equals(n2))
				return a.valeur;
		throw new RuntimeException("Pas de valeur trouv�e pour l'arc " + n1 +" -> " +n2);
	}
	
	@Override
	public List<String> listSuccesseurs(String noeud) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Iterator<String> iterator() {
		return noeuds.keySet().iterator();
	}

}
