
public class GrapheLA {

	private Boolean [][]  matriceAdj;
	private int nbNoeuds;
	
	public GrapheLA(int noeuds) {
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
	
	public Boolean [][] getMatriceAdj() {
		return matriceAdj;
	}

	@Override
	public String toString() {
		String chaine = "";
		for(int i = 0; i < this.getNbNoeuds(); i++) {
			chaine += i+1+" -> ";
			for(int j = 0; j < this.getNbNoeuds(); j++){
				if(getMatriceAdj()[i][j] == true) {
					chaine += j+1+" ";	
				}
			}
			chaine += "\n";
		}
			return chaine;
	}
	
	}

