package work.universitycourse.comp1549.Modules;


import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import work.universitycourse.comp1549.Components.Message;
import work.universitycourse.comp1549.Components.Channel;
import work.universitycourse.comp1549.Modules.InterfaceManager;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class ClientConnectionManager {

    private Socket clientSocket;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;
        private String clientID;
        private Channel serverChannel;

        public ClientConnectionManager(Socket socket) {
            this.clientSocket = socket;
            this.serverChannel = Channel.getChannel();
            
            this.setInputOutputStreams();
            this.setClientID();
        }

        public void run() {
            while (true) {
                this.addMessageToChannel();
            }
        }

        public String getClientID() {
            return this.clientID;
        }

        private void setClientID() {
            this.clientID = this.receiveMessage().message;
        }

        private void setInputOutputStreams() {
            try {
                this.inputStream = new ObjectInputStream(this.clientSocket.getInputStream());
                this.outputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            } catch (IOException e) {
                InterfaceManager.displayError(e, "Failed setting input / output streams! IOException error occurred.");
            }
        }

        private void closeStreams() {
            try {
                this.outputStream.close();
                this.inputStream.close();
            } catch (IOException e) {
                InterfaceManager.displayError(e, "Failed to close streams! IOException error occurred.");
            }
        }

        private void addMessageToChannel() {
            Message response = this.receiveMessage();
            if (response != null) {
                response.sender = this.clientID; // Ensure client does not try to fake ID
                this.serverChannel.addMessage(response);
            }
        }

        private Message receiveMessage() {
            Message data = null;
            try {
                data = (Message) this.inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                InterfaceManager.displayError(e, "Data retrieval failed.");
            }
            return data;
        }

        public void sendMessage(Message message) {
            try {
                this.outputStream.writeObject(message);
            } catch (IOException e) {
                InterfaceManager.displayError(e, "Message Send Failed.");
            }
        }

}
