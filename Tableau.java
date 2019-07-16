public class Tableau{

	private int longueur;
	private int hauteur;
	private Case [][] liste;
	private int casefaite;
	private int bombes;

	public Tableau(int l, int h){
		int i, j;
		liste = new Case[l][h];
		this.longueur=l;
		this.hauteur=h;
		this.casefaite=0;
		this.bombes=0;
		for(i=0;i<h;++i){
			for(j=0;j<l;++j){
				this.liste[j][i]= new Case(j,i,0);
			}
		}
	}

	public Case getCase(int x, int y){
		return this.liste[x][y];
	}

	public int getLongueur(){
		return this.longueur;
	}

	public int getHauteur(){
		return this.hauteur;
	}

	public int remplirBombe(int nbBombes){
		int i, l, h;
		double dl, dh;
		this.bombes=nbBombes;
		if(nbBombes>this.longueur*this.hauteur){
			return -1;
		}
		for(i=0;i<nbBombes;){
			dl=Math.random()*(double)this.longueur;
			dh=Math.random()*(double)this.hauteur;
			l=(int)dl;
			h=(int)dh;
			if(!this.liste[l][h].ifBombe()){
				this.liste[l][h].poseBombe();
				++i;
			}
		}
		return 0;
	}

	public void remplirScore(){
		int i, j;
		for(i=0;i<this.hauteur;++i){
			for(j=0;j<this.longueur;++j){
				this.liste[j][i].changeScore(evaluerCase(j,i));
			}
		}
	}

	public int evaluerCase(int x, int y){
		int total;
		total=0;
		if(x-1>=0 && this.liste[x-1][y].ifBombe()){
			++total;
		}
		if(x+1<this.longueur && this.liste[x+1][y].ifBombe()){
			++total;
		}
		if(y-1>=0 && this.liste[x][y-1].ifBombe()){
			++total;
		}
		if(y+1<this.hauteur && this.liste[x][y+1].ifBombe()){
			++total;
		}
		if(x-1>=0 && y-1>=0 && this.liste[x-1][y-1].ifBombe()){
			++total;
		}
		if(x-1>=0 && y+1<this.hauteur && this.liste[x-1][y+1].ifBombe()){
			++total;
		}
		if(x+1<this.longueur && y-1>=0 && this.liste[x+1][y-1].ifBombe()){
			++total;
		}
		if(x+1<this.longueur && y+1<this.hauteur && this.liste[x+1][y+1].ifBombe()){
			++total;
		}
		return total;
	}

	public int changeDrapeau(int x, int y){
		if(this.liste[x][y].ifVisible()){
			return 1;
		}
		else{
			this.liste[x][y].changeFlag();
			return 0;
		}
	}

	public int jouerCase(int x, int y){
		if(this.liste[x][y].ifFlag()){
			return 2;
		}
		if(this.liste[x][y].ifBombe()){
			return 1;
		}
		boucleVisible(x,y);
		if(this.casefaite==(this.longueur*this.hauteur-this.bombes)){
			return 3;
		}
		return 0;
	}

	public void boucleVisible(int x, int y){
		if(this.liste[x][y].ifVisible()){
			return;
		}
		this.liste[x][y].rendVisible();
		++this.casefaite;
		if(this.liste[x][y].getScore()>0){
			return;
		}
		if(x-1>=0){
			boucleVisible(x-1,y);
		}
		if(x+1<this.longueur){
			boucleVisible(x+1,y);
		}
		if(y-1>=0){
			boucleVisible(x,y-1);
		}
		if(y+1<this.hauteur){
			boucleVisible(x,y+1);
		}
		if(x-1>=0 && y-1>=0){
			boucleVisible(x-1,y-1);
		}
		if(x-1>=0 && y+1<this.hauteur){
			boucleVisible(x-1,y+1);
		}
		if(x+1<this.longueur && y-1>=0){
			boucleVisible(x+1,y-1);
		}
		if(x+1<this.longueur && y+1<this.hauteur){
			boucleVisible(x+1,y+1);
		}
	}

	public void fin(){
		int i, j;
		for(i=0;i<this.hauteur;++i){
			for(j=0;j<this.longueur;++j){
				this.liste[j][i].fin();
			}
		}
	}

}