public class GrapheMA implements IGraph {
	
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
				getMatriceAdj()[i][j] = false;
			}
		}
		return getMatriceAdj();
	}
	
	
	public void ajouterArc(int nLigne, int nColonne) {
		if(taille(nLigne, nColonne)) {
			getMatriceAdj()[nLigne - 1][nColonne - 1] = true;
		}
	}
	
	public boolean aArc (int nLigne, int nColonne) {
		boolean aArc = false;
		if (this.getMatriceAdj()[nLigne - 1][nColonne - 1] == true) {
			aArc = true;
		}
		return aArc;	
	}
	
	
	public int getNbNoeuds() {
		return nbNoeuds;
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
			if(getMatriceAdj()[nLigne - 1][i] == true) {
				res++;
			}
		}
		
		return res;
	}
	
	
	public int dIn(int nColonne) {
		int res = 0;
		for(int i = 0; i < this.nbNoeuds; i++) {
			if(getMatriceAdj()[i][nColonne - 1] == true) {
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
				if(getMatriceAdj()[i][j] == true) {
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

	
	public Boolean [][] getMatriceAdj() {
		return matriceAdj;
	}

}