/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienthttp;

import java.net.Socket;

/**
 *
 * @author Ineida Cardoso
 */
public class ErreurHTTP{
   
    ///les erreurs;
    private static final int Continue=100;  
    private static final int Switching_Protocols=101;
    private static final int OK=200;
    private static final int Created=201;
    private static final int Accepted=202;
    private static final int Non_Authoritative=203;
    private static final int No_Content=204;
    private static final int Reset_Content=205;
    private static final int Partial_Content=206;

    private static final int Multiple_Choices=300;
    private static final int Moved_Permanently=301;
    private static final int Found=302;
    private static final int See_Other=303;
    private static final int Not_Modified=304;
    private static final int Use_Proxy=305;
    private static final int Temporary_Redirect=307;

    private static final int Bad_Request=400; 
    private static final int Unauthorized =401; 
    private static final int Payment_Required =402;  
    private static final int Forbidden=403;
    private static final int Not_Found=404;
    private static final int Method_Not_Allowed=405;
    private static final int Not_Acceptable=406; 
    private static final int Proxy_Authentication_Required=407;
    private static final int Request_Time_Out=408;
    private static final int Conflict_Information=409;
    private static final int Gone=410;
    private static final int Length_Required=411;
    private static final int Precondition_Failed=412;
    private static final int Request_Entity_Too_Large=413;
    private static final int Request_URI_Too_Large=414;
    private static final int Unsupported_Media_Type=415;
    private static final int Requested_Range_Not_Satisfiable=416;
    private static final int Expectation_Failed=417;
    
    private static final int Internal_Server_Error=500;
    private static final int Not_Implemented=501; 
    private static final int Bad_Gateway=502;
    private static final int Service_Unavailable=503;
    private static final int Gateway_Time_Out=504;
    private static final int HTTP_Version_Not_Supported=505;

    public static int getContinue() {
        return Continue;
    }

    public static int getSwitching_Protocols() {
        return Switching_Protocols;
    }

    public static int getOK() {
        return OK;
    }

    public static int getCreated() {
        return Created;
    }

    public static int getAccepted() {
        return Accepted;
    }

    public static int getNon_Authoritative() {
        return Non_Authoritative;
    }

    public static int getNo_Content() {
        return No_Content;
    }

    public static int getReset_Content() {
        return Reset_Content;
    }

    public static int getPartial_Content() {
        return Partial_Content;
    }

    public static int getMultiple_Choices() {
        return Multiple_Choices;
    }

    public static int getMoved_Permanently() {
        return Moved_Permanently;
    }

    public static int getFound() {
        return Found;
    }

    public static int getSee_Other() {
        return See_Other;
    }

    public static int getNot_Modified() {
        return Not_Modified;
    }

    public static int getUse_Proxy() {
        return Use_Proxy;
    }

    public static int getTemporary_Redirect() {
        return Temporary_Redirect;
    }

    public static int getBad_Request() {
        return Bad_Request;
    }

    public static int getUnauthorized() {
        return Unauthorized;
    }

    public static int getPayment_Required() {
        return Payment_Required;
    }

    public static int getForbidden() {
        return Forbidden;
    }

    public static int getNot_Found() {
        return Not_Found;
    }

    public static int getMethod_Not_Allowed() {
        return Method_Not_Allowed;
    }

    public static int getNot_Acceptable() {
        return Not_Acceptable;
    }

    public static int getProxy_Authentication_Required() {
        return Proxy_Authentication_Required;
    }

    public static int getRequest_Time_Out() {
        return Request_Time_Out;
    }

    public static int getConflict_Information() {
        return Conflict_Information;
    }

    public static int getGone() {
        return Gone;
    }

    public static int getLength_Required() {
        return Length_Required;
    }

    public static int getPrecondition_Failed() {
        return Precondition_Failed;
    }

    public static int getRequest_Entity_Too_Large() {
        return Request_Entity_Too_Large;
    }

    public static int getRequest_URI_Too_Large() {
        return Request_URI_Too_Large;
    }

    public static int getUnsupported_Media_Type() {
        return Unsupported_Media_Type;
    }

    public static int getRequested_Range_Not_Satisfiable() {
        return Requested_Range_Not_Satisfiable;
    }

    public static int getInternal_Server_Error() {
        return Internal_Server_Error;
    }

    public static int getNot_Implemented() {
        return Not_Implemented;
    }

    public static int getBad_Gateway() {
        return Bad_Gateway;
    }

    public static int getService_Unavailable() {
        return Service_Unavailable;
    }

    public static int getGateway_Time_Out() {
        return Gateway_Time_Out;
    }

    public static int getHTTP_Version_Not_Supported() {
        return HTTP_Version_Not_Supported;
    }
    
    
    
}
