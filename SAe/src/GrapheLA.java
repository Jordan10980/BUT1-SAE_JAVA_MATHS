
public class GrapheLA extends GrapheMA {

	public GrapheLA(int noeuds) {
		super(noeuds);
		
	}
	
	public String total_arcs(int nLigne, int nColonne) {
		String chaine = "";
		if(this.getMatriceAdj()[nLigne][nColonne] == true) {
			chaine += " " + nColonne + " ";
		}
		return chaine;
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

