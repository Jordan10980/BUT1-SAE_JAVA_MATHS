public interface IGraph {
	
	public Boolean[][] initialiser();
	public void ajouterArc(int nLigne, int nColonne);
	public int getNbNoeuds();
	public int dOut (int nLigne);
	public int dIn(int nColonne);
	public String toString();
	public Boolean [][] getMatriceAdj();
	
}
