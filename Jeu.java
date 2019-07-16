import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import java.util.Scanner;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.util.Timer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Animation;

public class Jeu extends Application{

	public Button newgame=new Button("^-^");
	public Button click=new Button();
	public Button drapeau=new Button();

	public TextField absisse=new TextField();
	public TextField ordonne=new TextField();
	public Label nbbombes=new Label();
	public Label temps=new Label();

	public Parametre para = new Parametre();
	public ComboBox<String> difficulte=new ComboBox<String>();
	private Tableau t;
	private double[] diffi;

	public BorderPane fenetre = new BorderPane();

	private GridPane grille=new GridPane();
	private GridPane menu4=new GridPane();
	private GridPane menu3=new GridPane();
	private GridPane menu2=new GridPane();
	private GridPane menu=new GridPane();
	private GridPane info=new GridPane();

	private int actmode;
	private int cbbombe;
	private int time;

	private Scene fenfen=new Scene(fenetre);

	public static void main(String[] args){
		launch (args);
	}

	public void start(Stage primaryStage){
		time=0;
		temps.setText("Temps : "+new Integer(time).toString());
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event ->{
			time+=1;
			temps.setText("Temps : "+new Integer(time).toString());
		}));
		timeline.setCycleCount(Animation.INDEFINITE);

		int i, j;
		primaryStage.setTitle("Démineur De Guillaume Magniadas");
		click.setDisable(true);
		actmode=0;
		t=new Tableau(para.getLongueur(),para.getHauteur());
		t.remplirBombe(para.getBombe());
		t.remplirScore();
		difficulte.getItems().addAll("Facile","Moyen","Difficile");
		diffi= new double[3];
		diffi[0]=8.1;
		diffi[1]=6.4;
		diffi[2]=4.85;
		for(i=0;i<t.getHauteur();++i){
			for(j=0;j<t.getLongueur();++j){
				grille.add(t.getCase(j,i),j,i);
				final int ii=i, jj=j;
				t.getCase(j,i).setOnAction(event->{
					int fin;
					timeline.play();
					if(actmode==0){
						fin=t.jouerCase(jj,ii);
						if(fin==0 || fin==2){
							return;
						}
						else if(fin==3){
							newgame.setText("^o^");
							t.fin();
							primaryStage.setTitle("Victoire !!!");
							timeline.stop();
						}
						else{
							newgame.setText("x - x");
							t.fin();
							primaryStage.setTitle("Défaite...");
							timeline.stop();
							return;
						}
					}
					else{
						t.getCase(jj,ii).changeFlag();
						cbbombe=para.getBombe()-Case.getNbflag();
						nbbombes.setText("Bombes : " + new Integer(cbbombe).toString());
					}
				});
			}
		}
		cbbombe=para.getBombe()-Case.getNbflag();
		nbbombes.setText("Bombes : "+new Integer(cbbombe).toString());
		newgame.setOnAction(action->{
			int ii, jj, ilon, ihau;
			primaryStage.setTitle("Démineur De Guillaume Magniadas");
			timeline.stop();
			time=0;
			temps.setText("Temps : "+new Integer(time).toString());

			newgame.setText("^⁻^");
			grille=new GridPane();
			if(absisse.getText().isEmpty()){
				ilon=10;
			}
			else{
				ilon=Integer.parseInt(absisse.getText());
			}
			if(ordonne.getText().isEmpty()){
				ihau=10;
			}
			else{
				ihau=Integer.parseInt(ordonne.getText());
			}
			para.setLongueur(ilon);
			para.setHauteur(ihau);
			para.setBombe(diffi[difficulte.getSelectionModel().getSelectedIndex()]);
			t=new Tableau(para.getLongueur(),para.getHauteur());
			t.remplirBombe(para.getBombe());
			t.remplirScore();
			for(ii=0;ii<t.getHauteur();++ii){
				for(jj=0;jj<t.getLongueur();++jj){
					grille.add(t.getCase(jj,ii),jj,ii);
					final int iii=ii, jjj=jj;
					t.getCase(jj,ii).setOnAction(event->{
						int fin;
						timeline.play();
						if(actmode==0){
							fin=t.jouerCase(jjj,iii);
							if(fin==0 || fin==2){
								return;
							}
							else if(fin==3){
								newgame.setText("^o^");
								t.fin();
								primaryStage.setTitle("Victoire !!!");
								timeline.stop();
							}
							else{
								newgame.setText("x - x");
								t.fin();
								primaryStage.setTitle("Défaite...");
								timeline.stop();
								return;
							}
						}
						else{
							t.getCase(jjj,iii).changeFlag();
							cbbombe=para.getBombe()-Case.getNbflag();
							nbbombes.setText("Bombes : " + new Integer(cbbombe).toString());
						}
					});
				}
			}
			cbbombe=para.getBombe()-Case.getNbflag();
			nbbombes.setText("Bombes : "+new Integer(cbbombe).toString());
			fenetre.setCenter(grille);
			grille.setAlignment(Pos.CENTER);
			grille.setPadding(new Insets(5,0,0,0));
			primaryStage.sizeToScene();
		;});
		click.setOnAction(action->{
			click.setDisable(true);
			drapeau.setDisable(false);
			actmode=0;
		});
		drapeau.setOnAction(action->{
			click.setDisable(false);
			drapeau.setDisable(true);
			actmode=1;
		});
		click.setGraphic(new ImageView("Click.png"));
		click.setStyle("-fx-bckground-color:transparent; -fx-padding: 0, 0, 0, 0;");
		drapeau.setGraphic(new ImageView("drapeau.png"));
		drapeau.setStyle("-fx-bckground-color:transparent; -fx-padding: 0, 0, 0, 0;");
		difficulte.getSelectionModel().select(0);
		absisse.setPrefWidth(40);
		ordonne.setPrefWidth(40);

		menu3.add(new Label("Absi'"),0,0);
		menu3.add(new Label("Ordo'"),1,0);
		menu3.add(new Label("Difficulté"),2,0);
		menu3.add(absisse,0,1);
		menu3.add(ordonne,1,1);
		menu3.add(difficulte,2,1);
		menu4.add(click,0,0);
		menu4.add(drapeau,1,0);
		menu2.add(menu4,1,0);
		menu2.add(newgame,4,0);
		menu.add(menu3,0,0);
		menu.add(menu2,0,1);
		menu2.setHgap(5);
		menu3.setHgap(5);
		menu.setPadding(new Insets(5,5,5,5));
		menu.setVgap(5);

		fenetre.setTop(menu);
		menu.setAlignment(Pos.CENTER);

		info.add(nbbombes,0,0);
		info.add(temps,1,0);
		info.setHgap(20);
		fenetre.setBottom(info);
		info.setAlignment(Pos.CENTER);

		fenetre.setCenter(grille);
		grille.setAlignment(Pos.CENTER);

		primaryStage.getIcons().add(new Image("icone.png")); 
		primaryStage.setResizable(false);
		primaryStage.setScene(fenfen);
    	primaryStage.show();
	}
}
