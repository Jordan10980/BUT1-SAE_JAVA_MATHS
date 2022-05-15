package graphes.types;

import java.util.ArrayList;
import java.util.List;

public class GrapheMA extends Graphe{
	private int[][] ma;

	public GrapheMA(int nbNoeuds) {
		ma = new int[nbNoeuds][nbNoeuds];
		for (int a = 0; a < nbNoeuds; ++a)
			for (int b = 0; b < nbNoeuds; ++b)
				ma[a][b] = INFINI;
	}
	
	@Override
	public int getNbSommets() { return ma.length; }
	
	@Override
	public int getValuation(int a, int b) {
		assert estArcOK(a,b);
		return ma[a-1][b-1];
	}
	
	@Override
	public void ajouterArc(int a, int v, int b) {
		assert ! aArc(a,b);
		ma[a-1][b-1] = v;
	}
	

	@Override
	public String toString() {
		String str = "";
		int v;
		for(int i = 1; i<= getNbSommets(); ++i) {
			str += (i) + " =>";
			for (int j = 1; j <= getNbSommets(); ++j)
				if ((v= getValuation(i,j)) != INFINI) 
					str += " " + j + "(" + v + ")";
			str +="\n";
		}
		return str;
	}
	
	@Override
	public List<Integer> listSuccesseurs(int noeud) {
		List<Integer> successeurs = new ArrayList<Integer>();
		for (int n2=0; n2< getNbSommets(); ++n2) {
			if (ma[noeud-1][n2] != INFINI)
				successeurs.add(n2+1);
		}
		return successeurs;
	}

	@Override
	public List<Integer> listPredecesseur(int noeud) {
		List<Integer> predecesseurs = new ArrayList<Integer>();
		for(int i =0; i< ma.length ; i++) {
			if(ma[i][noeud-1] != INFINI) 
				predecesseurs.add(i+1);
		}
		return predecesseurs;
	}


	@Override
	public int distance(List<Integer> cheminCalcule) {
		assert cheminCalcule != null;
		assert cheminCalcule.size() > 0;
		int distance = 0;
		int sommetEnCours = cheminCalcule.get(0);
		for (int i=1 ; i<cheminCalcule.size(); i++) {
			int successeur = cheminCalcule.get(i);
			if(ma[sommetEnCours-1][successeur-1] == INFINI) 
				return INFINI;
			distance += ma[sommetEnCours-1][successeur-1];
			sommetEnCours = successeur;
		}
		return distance;
	}

}
