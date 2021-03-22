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

// #---------------------------------------------------------------------------#
// #                                 Contents                                  #
// #---------------------------------------------------------------------------#
// #                                                                           #
// #                                ClientInfo                                 #
// #       Stores pertinent information related to the client connected.       #
// #                                                                           #
// #                     1 - Three Parameter Creation                          #
// #                     2 - Two Parameter Creation                            #
// #                     3 - To String                                         #
// #                                                                           #
// #---------------------------------------------------------------------------#

public class ClientInfo {

    public String clientID, clientIP;
    public int clientPort;

    // #-----------------------------------------------------------------------#
    // #                     1 - Three Parameter Creation                      #
    // #-----------------------------------------------------------------------#

    // When initialised with three parameters (string, string and int), assign values. 
    public ClientInfo(String clientID, String clientIP, int clientPort) {

        this.clientID = clientID;
        this.clientIP = clientIP;
        this.clientPort = clientPort;

    }

    // #-----------------------------------------------------------------------#
    // #                     2 - Two Parameter Creation                        #
    // #-----------------------------------------------------------------------#

    // When intailised with two parameters (string and socket), assign values & get remote values.
    public ClientInfo(String clientID, Socket clientSocket) {

        this.clientID = clientID;
        this.clientIP = clientSocket.getRemoteSocketAddress().toString();
        this.clientPort = clientSocket.getPort();

    }

    // #-----------------------------------------------------------------------#
    // #                     3 - To String                                     #
    // #-----------------------------------------------------------------------#

    // Return client details in string format.
    public String toString() {

        return this.clientID + "," + this.clientIP + "," + Integer.toString(this.clientPort);

    }

}
