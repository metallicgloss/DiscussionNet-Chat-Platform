package work.universitycourse.comp1549.Components;

import java.net.Socket;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 * 
 */
public class ClientInfo {
    
    public String clientID, clientIP;
    public int clientPort;
    
    public ClientInfo(String clientID, String clientIP, int clientPort) {
        this.clientID = clientID;
        this.clientIP = clientIP;
        this.clientPort = clientPort;
    }

    public ClientInfo(String clientID, Socket clientSocket) {
        this.clientID = clientID;
        this.clientIP = clientSocket.getRemoteSocketAddress().toString();
        this.clientPort = clientSocket.getPort();
    }

    public String toString() {
        return this.clientID + "," + this.clientIP + "," + Integer.toString(this.clientPort);
    }

}
