public class Parametre{

	private int taillelongueur; //x
	private int taillehauteur; //y
	private double hardness; //plus il est proche de 1 plus la difficulté est grande.
	private int nbBombe;

	public Parametre(){
		this.taillelongueur=10;
		this.taillehauteur=10;
		this.hardness=8.1;
		this.nbBombe=calculBombe();
	}

	public int getLongueur(){
		return this.taillelongueur;
	}

	public void setLongueur(int lon){
		this.taillelongueur=lon;
	}

	public int getHauteur(){
		return this.taillehauteur;
	}

	public void setHauteur(int hau){
		this.taillehauteur=hau;
	}

	public int getBombe(){
		return this.nbBombe;
	}

	public void setBombe(double bom){
		this.hardness=bom;
		this.nbBombe=calculBombe();
	}

	public int calculBombe(){
		return (int)(((double)this.taillelongueur*(double)this.taillehauteur)/this.hardness);
	}

	public void changeHardness(int x){
		this.hardness=x;
	}

	public int changerTaille(int x, int y){
		if(x<1 || y<1){
			return 1;
		}
		this.taillelongueur=x;
		this.taillehauteur=y;
		return 0;
	}

}