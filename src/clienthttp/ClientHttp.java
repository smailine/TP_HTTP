/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienthttp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Smaïline
 */
public class ClientHttp {

    /**
     * @param args the command line arguments
     */
    
    private static ErreurHTTP Erreur=new ErreurHTTP(); 
    /// liste d'erreur pour avoir le message erreur: get(codeErreur); les cles
    //sont des entiers
    
    private static BufferedReader in;
    private static PrintWriter out;
    private final static int PORT_SERVEUR = 1026;
    private final static String IP_SERVEUR = "192.168.43.67";
    private static Socket sc;
    private static boolean autoflush=true;
    private static String url_page="index.html";
    private static String telechargement="C:\\Users\\Ineida Cardoso\\Desktop\\Etu SUP\\Projet\\ARAR\\index.html";
    private static String typeFichier="text/Html";
    private static  FileReader fichier;
    private static  FileWriter fichier1;
   
    private int tailleFichier;
    private Date date =new Date() ;
    
    
    
    public static void connexion(){
        
        try{
           /**
            * Création des flux entrants et sortants du client
            */

           sc = new Socket(InetAddress.getByName(IP_SERVEUR),PORT_SERVEUR);
           out = new PrintWriter(sc.getOutputStream(),autoflush); 
           in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
           
        }catch(IOException ex){
            System.out.println("Erreur lors de la création des flux");
        }
        
        
    }


    public String creationRequete(String operation,String url){
        try {
            String commande = "";
            //try{
            switch(operation){ //transformer en minuscule pas de sensible à la case
                case "get":
                    commande = "GET /"+url_page+" HTTP/1.1 \n";
                    commande+="Date: "+date.toString();
                    commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\n";
                    break;
                case "put":
                    commande="PUT /"+url_page+" HTTP/1.1\r\n";
                    commande+="Date: "+date.toString()+"\r\n";
                    commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\n";
                    commande+="Content-type:"+typeFichier+"\r\n";
                    commande+="Content_length:"+tailleFichier+"\r\n\r\n";
                    if(contenu(url)!=Erreur.getERREUR().get(2) && contenu(url)!=Erreur.getERREUR().get(3))
                        commande+= contenu(url);
                       
                    break;
                case "fermer":
                    commande = "GET /"+url_page+" HTTP/1.1 \r\n";
                    commande+="Date: "+date.toString()+"\r\n";
                    commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\n";
                    commande+="Connection: Closed";
                    break;
            }
            
            return commande;
        } catch (UnknownHostException ex) {
            //Logger.getLogger(ClientHttp.class.getName()).log(Level.SEVERE, null, ex);
            return (String) Erreur.getERREUR().get(1);
        }
    }
    public String contenu(String file){
        String contenu ="";
         
            try {
                fichier = new FileReader(file);
                fichier.read();
                fichier.close();
            } catch (FileNotFoundException ex) {
                return (String) Erreur.getERREUR().get(2);
            } catch (IOException ex) {
                return (String) Erreur.getERREUR().get(3);
            }
                    

        return contenu;
    }
    
    
    public int envoyerPage(String url_page){
        
        int erreur=0;
        String requete=creationRequete("put",url_page);
        if(requete==(String) Erreur.getERREUR().get(1))
            return 1;
        else if(requete==(String) Erreur.getERREUR().get(2)){
            return 2;
        }
        else if(requete==(String) Erreur.getERREUR().get(3)){
            return 3;
        }
        out.write(requete);
        try {
            sc.close();
        } catch (IOException ex) {
            return 4;
        }
        return 410;
    }
    
    public static int ecrireDansFichier(String nomFichier,String ligne){
        try{
           fichier1 = new FileWriter(nomFichier);
           fichier1.write(ligne);
        }catch(IOException ex){
           return 3;
        }
        return 0;
    }
    
    public static int lireDonneesRecu(){
        boolean page_non_recu = true;
        while(page_non_recu){
            try{
                if(in.ready()){
                   String ligne = "";
                   while(ligne !="EOF"){
                       ligne = in.readLine();
                       if(ecrireDansFichier("texte.txt",ligne)==3){return 3;};
                   }
                   page_non_recu = true;
                   fichier1.close();
                }
            }catch(IOException ex){
                return 3;
            }
            
        }
        return 0;
    }
    public int deconnexion(){
        int erreur=0;
        String requete=creationRequete("fermer",url_page);
        if(requete==(String) Erreur.getERREUR().get(1))
            return 1;
        else if(requete==(String) Erreur.getERREUR().get(2)){
            return 2;
        }
        else if(requete==(String) Erreur.getERREUR().get(3)){
            return 3;
        }
        out.write(requete);
        try {
            sc.close();
        } catch (IOException ex) {
            return 4;
        }
        return 410;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
       // connexion();
       ClientHttp client =new ClientHttp();
       System.out.println(client.creationRequete("put",url_page));
        
    }
    
}
