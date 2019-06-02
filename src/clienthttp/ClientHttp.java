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
    private static int PORT_SERVEUR = 80;
    private static String IP_SERVEUR ="https://fr.wikipedia.org/wiki/Tulipe";
    private static Socket sc;
    private static boolean autoflush=true;
    private static String url_page="https://fr.wikipedia.org/wiki/Tulipe";
    private static String dossier="C:\\Users\\Ineida Cardoso\\Desktop\\Etu SUP\\Projet\\ARAR\\HTTP\\";
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
           out = new PrintWriter(sc.getOutputStream()); 
           in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
           
        }catch(IOException ex){
            System.out.println("Erreur lors de la création des flux "+ex);
        }
        
        
    }


    public String creationRequete(String operation,String url){
        try {
            String commande = "";
            //try{
            switch(operation){ //transformer en minuscule pas de sensible à la case
                case "get":
                    out.println("GET /"+dossier+url+" HTTP/1.1");
                    out.println("Date: "+date.toString());
                    out.println("Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR);
                    break;
                case "put":
                    out.println("PUT /"+dossier+url+" HTTP/1.1");
                    out.println("Date: "+date.toString());
                    out.println("Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR);
                    out.println("Content-type:"+typeFichier);
                    int n=contenu(url);
                    out.println("Content_length: "+tailleFichier);
                    out.println();
                    if(n!=2 && n!=3)
                        out.println(fichier);
                       
                    break;
                case "fermer":
                    out.println("GET /"+dossier+url+" HTTP/1.1 ");
                    out.println("Date: "+date.toString());
                    out.println("Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR);
                    out.println("Connection: Closed");
                    break;
            }
            
            return commande;
        } catch (UnknownHostException ex) {
            //Logger.getLogger(ClientHttp.class.getName()).log(Level.SEVERE, null, ex);
            return (String) Erreur.getERREUR().get(1);
        }
    }
    public int contenu(String file){
        
         int contenu;
            try {
                fichier = new FileReader(dossier+file);
                contenu=fichier.read();
                tailleFichier=(int) new File(dossier+file).length();
                fichier.close();
            } catch (FileNotFoundException ex) {
                return 2; //(String) Erreur.getERREUR().get(2);
            } catch (IOException ex) {
                return 3;//(String) Erreur.getERREUR().get(3);
            }
                    

        return contenu;
    }
    
    
    public int envoyerPage(String url_page){
        String reponse="";
        String[] split;
        int erreur=0;
        String requete=creationRequete("put",url_page);
        try {
            reponse=in.readLine();
            split=reponse.split(" ");
          
            
        } catch (IOException ex) {
            return 408;
        }
        try {
            sc.close();
        } catch (IOException ex) {
            return 4;
        }
         return  Integer.parseInt(split[1]);
    }
    
    public static int ecrireDansFichier(String nomFichier,String ligne){
        try{
           fichier1 = new FileWriter(dossier+nomFichier);
           fichier1.write(ligne);
        }catch(IOException ex){
           return 3;
        }
        return 0;
    }
    
    public static int lireDonneesRecu(){
        boolean page_non_recu = true; ///toujours a true
        boolean contenu=false;
        int tailleRecu=0;
        int tailleEspere=0;
        int lecture=0;
        
            try{
                
                   String ligne = "";
                   ligne = in.readLine();
                   System.out.println(ligne);
                   String[] split;
                   split=ligne.split(" ");
                   int code =Integer.parseInt(split[1]);
/*<<<<<<< HEAD*/
                   int i =0;
                   String test = ligne;
                   while(ligne !=null){
                       i++;
                       //ligne = in.readLine();
                       System.out.println(ligne);
                   while(ligne !=null && code==200){
                       ligne = in.readLine();
                       split=ligne.split(" "); 
                       if(split[0]=="Content_length:²"){
                           tailleEspere=Integer.parseInt(split[1]);
                       }
                           lecture=ecrireDansFichier("texte.txt",ligne);
                           tailleRecu+=ligne.length();
                           if (lecture==3){return 3;}
                       }
                   }
                   lecture=ecrireDansFichier("C:\\Users\\Smaïline\\Desktop\\texte.txt",test);
                   fichier1.close();
                
            }catch(IOException ex){
                return 3;
            }
            if(tailleRecu<tailleEspere)return 206;
        return 200;
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
            in.close();
            out.close();
            sc.close();
        } catch (IOException ex) {
            return 4;
        }
        return 410;
    }

    public static String getIP_SERVEUR() {
        return IP_SERVEUR;
    }

    public static void setIP_SERVEUR(String IP_SERVEUR) {
        ClientHttp.IP_SERVEUR = IP_SERVEUR;
    }

    public static String getUrl_page() {
        return url_page;
    }

    public static void setUrl_page(String url_page) {
        ClientHttp.url_page = url_page;
    }

    public static String getDossier() {
        return dossier;
    }

    public static void setDossier(String dossier) {
        ClientHttp.dossier = dossier;
    }

    public static String getTypeFichier() {
        return typeFichier;
    }

    public static void setTypeFichier(String typeFichier) {
        ClientHttp.typeFichier = typeFichier;
    }

    public static int getPORT_SERVEUR() {
        return PORT_SERVEUR;
    }

    public static void setPORT_SERVEUR(int PORT_SERVEUR) {
        ClientHttp.PORT_SERVEUR = PORT_SERVEUR;
    }
    
    
    public int recevoirPage(){
        int erreur=0;
        String requete = creationRequete("get",url_page);
        if(requete==(String) Erreur.getERREUR().get(1))
            return 1;
        else if(requete==(String) Erreur.getERREUR().get(2)){
            return 2;
        }
        else if(requete==(String) Erreur.getERREUR().get(3)){
            return 3;
        }
        System.out.println(requete);
        out.write(requete, 0, requete.length());
        
        lireDonneesRecu();
        
        return 200;
        
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

      
        
       ClientHttp client =new ClientHttp();
       connexion();
       
       out.println("GET / HTTP/1.1");
       out.println();
       out.flush();
       lireDonneesRecu();
       /*System.out.println(in.readLine());
       System.out.println(in.readLine());
       System.out.println(in.readLine());
       System.out.println(in.readLine());
       System.out.println(in.readLine());*/
       //lireDonneesRecu();
       
       //int ok = client.recevoirPage();
       //System.out.println(client.creationRequete("put",url_page));

        
    }
    
}
