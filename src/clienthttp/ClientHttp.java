/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienthttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Smaïline
 */
public class ClientHttp {

    /**
     * @param args the command line arguments
     */
    
    private BufferedReader in;
    private PrintWriter out;
    private static int PORT_SERVEUR = 1026;
    private InetAddress IP_SERVEUR;
    private static Socket sc;
    private boolean autoflush=true;
    
    
    
    
    public void connexion(){
        
        try{
           /**
            * Création des flux entrants et sortants du client
            */
           sc = new Socket(InetAddress.getLocalHost(),1026);
           out = new PrintWriter(sc.getOutputStream(),autoflush); 
           in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
           
        }catch(IOException ex){
            System.out.println("Erreur lors de la création des flux");
        }
        
        
    }
    
    public String creationRequete(String operation,String url, int port){
        String commande = "";
        switch(operation){
            case "get" :
                commande = "GET / HTTP/1.1 \n";
            case "put":
                commande="PUT / HTTP/1.1\n";
            case "reponse":
        }
        
        return commande;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
