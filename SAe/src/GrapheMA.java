import java.util.ArrayList;
import java.util.Arrays;

public class GrapheMA {
	
	private Boolean [][]  matriceAdj;
	private int nbNoeuds;
	
	public GrapheMA(int noeuds) {
		matriceAdj = new Boolean[noeuds][noeuds];
		this.matriceAdj = initialiser();
		this.nbNoeuds = noeuds;
		
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
			matriceAdj[nLigne][nColonne] = true;
		}
	}
	
	public boolean aArc (int nLigne, int nColonne) {
		return matriceAdj[nLigne][nColonne];
		
	}
	
	public int getNbNoeuds() {
		return nbNoeuds;
	}
	
	public void setNbNoeuds(int nbNoeuds) {
		this.nbNoeuds = nbNoeuds;
	}
	
	public boolean taille(int nLigne, int nColonne) {
		if(nLigne < this.nbNoeuds && nColonne < this.nbNoeuds) 
			return true;
		
		else
			return false;
	}
	
	public int dOut (int nLigne) {
		int res = 0;
		for(int i = 0; i < this.nbNoeuds; i++) {
			if(matriceAdj[nLigne][i] == true) {
				res++;
			}
		}
		
		return res;
	}
	
	public int dIn(int nColonne) {
		int res = 0;
		for(int i = 0; i < this.nbNoeuds; i++) {
			if(matriceAdj[i][nColonne] == true) {
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
				chaine += matriceAdj[i][j] + " ";
			}
			chaine += "\n";
		}
		return chaine;
	}

	
	
	
//	dOut
//	dIn
//
	}
