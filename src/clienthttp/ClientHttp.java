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
    private final static String IP_SERVEUR ="192.168.43.67";
    private static Socket sc;
    private static boolean autoflush=true;

    private static String url_page="https://fr.wikipedia.org/wiki/Tulipe";
    private static String telechargement="C:\\Users\\Ineida Cardoso\\Desktop\\Etu SUP\\Projet\\ARAR\\index.html";

    private static String typeFichier="text/Html";
    private static  FileReader fichier;
    private static  FileWriter fichier1;
    private static int tempsAttente=80000;
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
                    commande = "GET /"+url_page+" HTTP/1.1 \r\n";
                    commande+="Date: "+date.toString()+"\r\n";
                    commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\r\n";
                    break;
                case "put":
                    commande="PUT /"+url_page+" HTTP/1.1\r\n";
                    commande+="Date: "+date.toString()+"\r\n";
                    commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\r\n";
                    commande+="Content-type:"+typeFichier+"\r\n";
                    commande+="Content_length: "+tailleFichier+"\r\n\r\n";
                    if(contenu(url)!=2 && contenu(url)!=3)
                        commande+= contenu(url);
                       
                    break;
                case "fermer":
                    commande = "GET/ "+url_page+" HTTP/1.1 \r\n";
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
    public int contenu(String file){
        
         int contenu;
            try {
                fichier = new FileReader(telechargement+file);
                contenu=fichier.read();
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
            reponse=in.readLine();
            split=reponse.split(" ");
            Integer.parseInt(split[1]);
        } catch (IOException ex) {
            return 408;
        }
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
/*=======
                   while(ligne !=null && code==200){
                       ligne = in.readLine();
                       split=ligne.split(" "); 
                       if(split[0]=="Content_length:²"){
                           tailleEspere=Integer.parseInt(split[1]);
                       }
>>>>>>> ed0d2296b9ab79f3c3d40eb39abef8eec0169229*/
                       if(ligne.length()==0){
                           contenu=true;
                       }
                       if(contenu){

                           //lecture=ecrireDansFichier("C:\\Users\\Smaïline\\Desktop\\texte.txt",ligne);
                           if (lecture==3){return 3;}
                       }
                      ligne = in.readLine();
                      test+=ligne;
/*=======
                           lecture=ecrireDansFichier("texte.txt",ligne);
                           tailleRecu+=ligne.length();
                           if (lecture==3){return 3;}
                       }
>>>>>>> ed0d2296b9ab79f3c3d40eb39abef8eec0169229*/
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
