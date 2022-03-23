

public class GrapheMA {
	
	private Boolean [][]  matriceAdj;
	private int nbNoeuds;
	
	public GrapheMA(int noeuds) {
		matriceAdj = new Boolean[noeuds][noeuds];
		this.nbNoeuds = noeuds;
		this.initialiser();
	}
	
	public Boolean[][] initialiser() {
		for(int i = 0; i < this.nbNoeuds; i++) {
			for(int j = 0; j < this.nbNoeuds; j++) {
				matriceAdj[i][j] = false;
			}
		}
		return matriceAdj;
	}
	
	
	public void ajouterArc(int nLigne, int nColonne) {
		if(taille(nLigne, nColonne)) {
			matriceAdj[nLigne - 1][nColonne - 1] = true;
		}
	}
	
	public boolean aArc (int nLigne, int nColonne) {
		boolean aArc = false;
		if (this.matriceAdj[nLigne - 1][nColonne - 1] == true) {
			aArc = true;
		}
		return aArc;
		
	}
	
	public int getNbNoeuds() {
		return nbNoeuds;
	}
	
	public void setNbNoeuds(int nbNoeuds) {
		this.nbNoeuds = nbNoeuds;
	}
	
	public boolean taille(int nLigne, int nColonne) {
		if(nLigne - 1 <= this.nbNoeuds && nColonne - 1 <= this.nbNoeuds) 
			return true;
		
		else
			return false;
	}
	
	public int dOut (int nLigne) {
		int res = 0;
		for(int i = 0; i < this.nbNoeuds; i++) {
			if(matriceAdj[nLigne - 1][i] == true) {
				res++;
			}
		}
		
		return res;
	}
	
	public int dIn(int nColonne) {
		int res = 0;
		for(int i = 0; i < this.nbNoeuds; i++) {
			if(matriceAdj[i][nColonne - 1] == true) {
				res++;
			}
		}
		
		return res;
	}

	@Override
	public String toString() {
		String chaine = "";
		for(int i = 0; i < this.nbNoeuds; i++) {
			for(int j = 0; j < this.nbNoeuds; j++){
				if(matriceAdj[i][j] == true) {
					chaine += "1 ";
				}
				else {
					chaine += "0 ";
				}
			}
			chaine += "\n";
		}
		return chaine;
	}

	
	
	
//	dOut
//	dIn
//
	}
