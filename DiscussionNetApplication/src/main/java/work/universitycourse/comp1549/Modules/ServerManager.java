package work.universitycourse.comp1549.Modules;

import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import work.universitycourse.comp1549.Components.Channel;
import work.universitycourse.comp1549.Components.Message;
import work.universitycourse.comp1549.Modules.InterfaceManager;
        
/**
 *
 * @author Adnan Turna
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class ServerManager {
    private ServerSocket server;
    private Channel serverChannel;
    private int port = 9090;
    private String serverIP = "127.0.0.1";
    private int maxConnections = 1024;
    private boolean serverRunning;

    public ServerManager() {
        this.initServer();
    }

    public ServerManager(String ip, int port, int maxConnections) {
        this.serverIP = ip;
        this.port = port;
        this.maxConnections = maxConnections;
        this.initServer();
    }

    public void startServer() {
        // Start Request handler thread
        RequestHandler requestHandler = new RequestHandler();
        Thread requestHandlerThread = new Thread(requestHandler);
        requestHandlerThread.start();
        // Accept new clients while the thread is running
        this.serverRunning = true;
        while (this.serverRunning) {
            this.acceptNewClient();
        }
        this.closeServer();
    }

    private void initServer() {
        // Get server channel
        this.serverChannel = Channel.getChannel();

        // Initialise server socket
        // TODO replace with check port available
        try {
            InetAddress serverAddr = InetAddress.getByName(this.serverIP);
            this.server = new ServerSocket(this.port, this.maxConnections, serverAddr);
            this.server.setReuseAddress(true);
        } catch (IOException e) {
            this.serverRunning = false;
            System.out.println("Failed to start server on specified port. IOException error occurred.");
        }
    }

    private void acceptNewClient() {
        try {
            // Accept new client and add them to the channel
            Socket clientSocket = this.server.accept();
            this.serverChannel.addClientConnection(clientSocket);
            System.out.println("New Client Joined");
        } catch (IOException e) {
            System.out.println("Failed ot add new client! IOException error occurred.");
        }
    }

    private void closeServer() {
        try {
            if (this.server != null) {
                this.server.close();
            }
        } catch (IOException e) {
            System.out.println("Failed to close server! IOException error occurred.");
        } finally {
            this.serverRunning = false;
        }
    }

    private static class RequestHandler implements Runnable {

        private Channel serverChannel;

        public RequestHandler() {
            this.serverChannel = Channel.getChannel();
        }

        @Override
        public void run() {
            while (true) {
                // Get next message in channel
                Message messageObj = this.serverChannel.getNextMessage();

                // Process next message if one is present
                if (messageObj != null) {
                    if (this.serverChannel.checkClientConnectionExists(messageObj.receiver)) {
                        this.serverChannel.getClientConnection(messageObj.receiver).sendMessage(messageObj);
                    } else {
                        // TODO How to handle a receiver that is not in the clientconnections hash map
                    }
                }

                // Put the thread to sleep for a bit before checking again
                this.wait(100);
            }
        }

        private void wait(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error occurred");
            }
        }

    }
}
