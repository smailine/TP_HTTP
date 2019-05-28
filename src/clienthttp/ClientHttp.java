/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienthttp;

import java.io.BufferedReader;
import java.io.File;
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
    private static String url_page="C:\\Users\\Ineida Cardoso\\Desktop\\Etu SUP\\Projet\\ARAR\\index.html";
    private static String typeFichier="text/Html";

    private static void strtoupper(String put) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
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
        String commande = "";
        //try{
            switch(operation){ //transformer en minuscule pas de sensible à la case
                case "get":
                    commande = "GET /"+url_page+" HTTP/1.1 \n";
                    commande+="Date: "+date.toString();
                   // commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\n";
                case "put":
                    commande="PUT /"+url_page+" HTTP/1.1\n";
                    commande+="Date: "+date.toString();
                   // commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\n";
                    commande+="Content-type:"+typeFichier;
                    commande+="Content_length:"+tailleFichier;
                case "fermer":
                    commande = "GET /"+url_page+" HTTP/1.1 \n";
                    commande+="Date: "+date.toString()+"\n";
                    //commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\n";
                    commande+="Connection: Closed";
            }
        /*}catch (UnknownHostException e){
            return (String) Erreur.getERREUR().get(1);
        }*/
        return commande;
    }
   
    public int RecevoirPage(String url_page){
        int erreur=0; //0 pas d'erreur
        String Requete=creationRequete("fermer",url_page);
        if(Requete==Erreur.getERREUR().get(1))
            return 1;
        out.write(Requete);
        return erreur;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
       // connexion();
       ClientHttp client =new ClientHttp();
       System.out.println(client.creationRequete("put",url_page));
        System.out.println(client.Erreur.getERREUR().get(1));
        
    }
    
}
