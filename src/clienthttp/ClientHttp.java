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
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Smaïline
 */
public class ClientHttp {

    /**
     * @param args the command line arguments
     */
    
    private static BufferedReader in;
    private static PrintWriter out;
    private final static int PORT_SERVEUR = 1026;
    private final static String IP_SERVEUR = "192.168.43.67";
    private static Socket sc;
    private static boolean autoflush=true;
    private static String url_page="Fichier.txt";
    private static String typeFichier="text/Html";
    private int tailleFichier;
    
    
    
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


    public String creationRequete(String operation,String url, int port) throws UnknownHostException{
        String commande = "";
        switch(operation){
            case "get" :
                commande = "GET / HTTP/1.1 \n";
                commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\n";
            case "put":
                commande="PUT /"+url_page+" HTTP/1.1\n";
                commande+="Host: "+InetAddress.getByName(IP_SERVEUR)+":"+PORT_SERVEUR+"\n";
                commande+="Content-type:"+typeFichier;
                commande+="Content_length:"+tailleFichier;
        }
        
        return commande;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        connexion();
        
    }
    
}
