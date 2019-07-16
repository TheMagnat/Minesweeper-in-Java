import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.Node;

public class Case extends Button{

	private int x;
	private int y;
	private int score;
	private Boolean visible;
	private Boolean bombe;
	private Boolean flag;
	private static int nbflag;

	private static String images[] = {"casenonclique.png","casevide.png","case1.png","case2.png","case3.png","case4.png","case5.png","case6.png","case7.png","case8.png","bombe.png","casedrapeau.png"}; 

	public Case(int nx, int ny, int ns){
		this.setGraphic(new ImageView(images[0]));
		this.x=nx;
		this.y=ny;
		this.score=ns;
		this.visible=false;
		this.bombe=false;
		this.flag=false;
		this.nbflag=0;
		this.setStyle("-fx-bckground-color:transparent; -fx-padding: 0, 0, 0, 0;");
	}

	public Boolean ifVisible(){
		return this.visible;
	}

	public void rendVisible(){
		this.visible=true;
		changeimage();
	}

	public Boolean ifBombe(){
		return this.bombe;
	}

	public void poseBombe(){
		this.bombe=true;
	}

	public Boolean ifFlag(){
		return this.flag;
	}

	public void changeFlag(){
		if(!this.visible){
			if(this.flag){
				this.setGraphic(new ImageView(images[0]));
				this.flag=false;
				--this.nbflag;
			}
			else{
				this.setGraphic(new ImageView(images[11]));
				this.flag=true;
				++this.nbflag;
			}
		}
	}

	public static int getNbflag(){
		return nbflag;
	}

	public int getScore(){
		return this.score;
	}

	public void changeScore(int s){
		this.score=s;
	}
	public void changeimage(){
		if(!this.visible){
			this.setGraphic(new ImageView(images[0]));
		}
		else{
			//this.setDisable(true);
			if(score==0){
				this.setGraphic(new ImageView(images[1]));
			}
			else if(score==1){
				this.setGraphic(new ImageView(images[2]));
			}
			else if(score==2){
				this.setGraphic(new ImageView(images[3]));
			}
			else if(score==3){
				this.setGraphic(new ImageView(images[4]));
			}
			else if(score==4){
				this.setGraphic(new ImageView(images[5]));
			}
			else if(score==5){
				this.setGraphic(new ImageView(images[6]));
			}
			else if(score==6){
				this.setGraphic(new ImageView(images[7]));
			}
			else if(score==7){
				this.setGraphic(new ImageView(images[8]));
			}
			else if(score==8){
				this.setGraphic(new ImageView(images[9]));
			}
		}
	}

	public void fin(){
		this.setDisable(true);
		if(this.bombe){
			this.visible=true;
			this.setGraphic(new ImageView(images[10]));
		}
	}
}