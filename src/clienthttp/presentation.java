/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienthttp;

//import java.awt.Button;
//import java.awt.Label;
//import java.awt.TextArea;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Ineida Cardoso
 */
public class presentation extends Application{
    private final ErreurHTTP erreur= new ErreurHTTP();
    ClientHttp client = new ClientHttp();

    @Override
    public void start(Stage stage) throws Exception {
        
        int cr_sr;
        //initialiser les messages d'erreur
       
        //initialiser les labels
      Label text1 = new Label();
      Label text2 = new Label();
      Label text3 = new Label();
      Label text4 = new Label();
      Label text5 = new Label();
      text1.setText("Saisissez la localisation du fichier");
      text2.setText("Saisissez le port du serveur ");
      text3.setText("Saisissez l'addresse Ip du serveur");
      text4.setText("Saisissez le nom du fichier ");
      text5.setText("Saisissez le type de fichier");
      Label nom= new Label("Cardoso, Eynard, Nollet, Viraragavane");
      //definition de zones de saisies 
      TextField monFichier = new TextField("Texte.txt");
      TextField typeFichier = new TextField("texte/html");
      TextField adresse = new TextField("192.168.43.67");
      TextField port = new TextField("1026");
      TextField dossier = new TextField("C:\\Users\\Ineida Cardoso\\Desktop\\Etu SUP\\Projet\\ARAR\\HTTP\\");
      
      
      
      Button Lecture = new Button("Charger (get)");
      Button Ecriture = new Button("Envoyer (put)");
      Button Deconnexion = new Button ("Deconnecter");
      HBox space = new HBox(nom);
      HBox fichier = new HBox(text4, monFichier);
      HBox tFichier=new HBox(text5, typeFichier);
      HBox Dossier = new HBox(text1,dossier);
      HBox nbPort = new HBox(text2, port);
      HBox ip = new HBox(text3, adresse);
      HBox Vide1= new HBox();
      VBox Vide2= new VBox();
      HBox button = new HBox(Lecture,Ecriture,Deconnexion);
      
      VBox groupe = new VBox(Vide2,ip,nbPort,Dossier,fichier,tFichier,button,Vide1,space);
      groupe.setSpacing(10);
      BorderPane Border =new BorderPane();
      stage.setTitle("Chargement et envoie d'une page http au serveur");
      Border.setCenter(groupe);
      Border.setLeft(Vide2);
   
      
      Scene scene = new Scene(Border, Color.DARKGRAY);
      stage.setScene(scene);
      stage.show();
      
      ///gestion d'erreur et affichage d'erreur 
      Label Message= new Label();
      ///action de la lecture
      Lecture.setOnAction(new EventHandler<ActionEvent>(){
         
          @Override
          public void handle(ActionEvent event) {
              client.setIP_SERVEUR(adresse.getCharacters().toString());
               client.setPORT_SERVEUR(Integer.parseInt(port.getCharacters().toString()));
               client.setDossier(dossier.getCharacters().toString());
               client.setTypeFichier(typeFichier.getCharacters().toString());
               client.setUrl_page(monFichier.getCharacters().toString());
               System.out.println("fichier" +monFichier.getCharacters());
               
               int cr_rv=client.recevoirPage();
               afficherErreur(cr_rv);
          }
               
           
      });
      
      
        Ecriture.setOnAction(new EventHandler<ActionEvent>(){
         
          @Override
          public void handle(ActionEvent event) {
               client.setIP_SERVEUR(adresse.getCharacters().toString());
               client.setPORT_SERVEUR(Integer.parseInt(port.getCharacters().toString()));
               client.setDossier(dossier.getCharacters().toString());
               client.setTypeFichier(typeFichier.getCharacters().toString());
               int cr_sr;
               cr_sr=client.envoyerPage(monFichier.getCharacters().toString());
               afficherErreur(cr_sr);
               
          }
      });
           
          Deconnexion.setOnAction(new EventHandler<ActionEvent>(){
         
          @Override
          public void handle(ActionEvent event) {
              
               System.out.println("fichier" +monFichier.getCharacters());
               int cr_sr;
               cr_sr=client.deconnexion();
               afficherErreur(cr_sr);
               
          }
      });
    }
    
    public void afficherErreur( int CV){
        Alert dBox = new Alert(AlertType.CONFIRMATION);
        dBox.setTitle("Informations sur la transmission");
        dBox.setHeaderText("Informations");
        dBox.setContentText((String)erreur.getERREUR().get(CV));
        ButtonType btnRee= new ButtonType("Réessayer");
        ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dBox.showAndWait();
        //Optional<ButtonType> choice= dBox.showAndWait();
       
    }
    
    
      
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
