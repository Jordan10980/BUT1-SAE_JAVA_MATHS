
package graphes.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GrapheLA extends Graphe{
	private static class Stub {
		public int valuation, cible;
		public Stub (int valuation, int cible) {
			this.valuation = valuation;
			this.cible = cible;
		}
	}
	private	List<Stub> la[];
	
	@SuppressWarnings("unchecked")
	public GrapheLA(int nbNoeuds) {
		super();
		la = new List[nbNoeuds]; // necessite le SuppressWarnings ci-dessus
		for (int i = 0; i < nbNoeuds; ++i)
			la[i] = new ArrayList<>();
	}

	@Override
	public int getNbSommets() {
		return la.length;
	}

	@Override
	public int getValuation(int a, int b) {
		assert estArcOK(a,b);
		List<Stub> stubs = la[a-1];
		for (Stub s : stubs)
			if (s.cible == b)
				return s.valuation;
		return INFINI;
	}

	public void ajouterArc(int a, int v, int b) {
		assert ! aArc(a,b);
		la[a-1].add(new Stub(v, b));
	}
	

	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i< la.length; ++i) {
			str += (i+1) + " =>";
			for (Stub s : la[i]) 
				str += " "+s.cible + "("+s.valuation+")";
			str +="\n";
		}
		return str;
	}

	@Override
	public List<Integer> listSuccesseurs(int noeud) {
		List<Integer> successeurs = new ArrayList<Integer>();
		List<Stub> stubs = la[noeud-1];
		for (Stub s : stubs)
			successeurs.add(s.cible);		
		return successeurs;
	}

	@Override
	public List<Integer> listPredecesseur(int noeud) {
		List<Integer> predecesseurs = new ArrayList<Integer>();
		for (int i=0 ; i < la.length ; i++) {
			List<Stub> stubs = la[i];
			Iterator<Stub> it = stubs.iterator();
			while(it.hasNext()) {
				Stub stub = it.next();
				if(stub.cible == noeud) {
					predecesseurs.add(i+1);
					break;
				}
			}
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
			List<Stub> stubs = la[sommetEnCours-1];
			boolean trouve=false;
			for(Stub stub: stubs) {
				if(stub.cible == successeur) {
					trouve=true;
					distance += stub.valuation;
					sommetEnCours = successeur;
					break;
				}
			}
			if(!trouve) return INFINI;
		}
		return distance;
	}

}
