/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienthttp;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Ineida Cardoso
 */
public class ErreurHTTP{
 
    private final static Hashtable ERREUR=new Hashtable();
    public ErreurHTTP(){
        ERREUR.put(0,"Sucess");
        ERREUR.put(1,"Unknown Host");
        ERREUR.put(2,"File Not Found");
        ERREUR.put(2,"Problem during outload file");
        
        ERREUR.put(100,"Continue");
        ERREUR.put(101,"Switching_Protocols");
        
        ERREUR.put(200,"OK");
        ERREUR.put(201,"Created");
        ERREUR.put(202,"Accepted");
        ERREUR.put(204,"No Content");
        ERREUR.put(205,"Reset Content");
        ERREUR.put(206,"Partial Content");
        
        ERREUR.put(300,"Multiple Choices");
        ERREUR.put(301,"Moved Permanently");
        ERREUR.put(302,"Found");
        ERREUR.put(303,"See Other");
        ERREUR.put(304,"Not Modified");
        ERREUR.put(305,"Use Proxy");
        ERREUR.put(306,"Temporary Redirect");
        
        ERREUR.put(400,"Bad Request");
        ERREUR.put(401,"Unauthorized");
        ERREUR.put(402,"Payment Required");
        ERREUR.put(403,"Forbidden");
        ERREUR.put(404,"Not Found");
        ERREUR.put(405,"Method Not Allowed");
        ERREUR.put(406,"Not Acceptable");
        ERREUR.put(407,"Proxy Authentication Required");
        ERREUR.put(408,"Request Time Out");
        ERREUR.put(409,"Conflict Information");
        ERREUR.put(410,"Gone");
        ERREUR.put(411,"Length Required");
        ERREUR.put(412,"Precondition Failed");
        ERREUR.put(413,"Request Entity Too arge");
        ERREUR.put(414,"Request URI Too Large");
        ERREUR.put(415,"Unsupported Media Type");
        ERREUR.put(416,"Requested Range Not Satisfiable");
        ERREUR.put(417,"Expectation Failed");
        
        ERREUR.put(500,"Internal Server Error");
        ERREUR.put(501,"Not Implemented");
        ERREUR.put(502,"Bad Gateway");
        ERREUR.put(503,"Service Unavailable");
        ERREUR.put(504,"Gateway Time Out");
        ERREUR.put(505,"HTTP Version Not Supported");
       }

    public static Hashtable getERREUR() {
        return ERREUR;
    }
    
}
    