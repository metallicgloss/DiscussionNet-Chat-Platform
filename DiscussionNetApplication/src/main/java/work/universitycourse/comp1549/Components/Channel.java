package work.universitycourse.comp1549.Components;

import java.util.Deque;
import java.net.Socket;
import java.util.HashMap;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import work.universitycourse.comp1549.Modules.ClientConnectionManager;

/**
 *
 * @author Adnan Turna
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class Channel {
    private static Channel channel;
    private Deque<Message> channelMessages = new ArrayDeque<Message>(); // Used as a Queue
    private HashMap<String, ClientConnectionManager> clientConnections = new HashMap<String, ClientConnectionManager>();


    private Channel() {}

    public static Channel getChannel() {
        if (channel == null) {
            channel = new Channel();
        }
        return channel;
    }

    public void addMessage(Message messageObj) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        messageObj.timestamp = timestamp;
        this.channelMessages.addLast(messageObj);
    }

    public Message getNextMessage() {
        // Gets next available message from the channelMessages, using FIFO
        return this.channelMessages.poll();
    }

    public ClientConnectionManager getClientConnection(String clientID) {
        // Returns clientConnection object using the client's ID
        return this.clientConnections.get(clientID);
    }

    public boolean checkClientConnectionExists(String clientID) {
        // Check if a clientConnection exists by checking the client's ID
        return this.clientConnections.containsKey(clientID);
    }

    public void addClientConnection(Socket clientSocket) {
        // Create client connection object
        ClientConnectionManager clientConnectionObj = new ClientConnectionManager(clientSocket);
        String clientID = clientConnectionObj.getClientID();

        // Add client connection object to clientConnections hashmap
        this.clientConnections.put(clientID, clientConnectionObj);

        // Start thread to listen for new client messeges
        this.startClientThread(clientID);
    }

    private void startClientThread(String clientID) {
        new Thread((Runnable) this.clientConnections.get(clientID)).start();
    }
}
