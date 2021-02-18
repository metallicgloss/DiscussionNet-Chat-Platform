package work.universitycourse.comp1549.Modules;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import work.universitycourse.comp1549.Components.Message;
import work.universitycourse.comp1549.Modules.InterfaceManager;

/**
 *
 * @author Adnan Turna
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class ClientManager {
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    private Socket clientSocket;
    private boolean runClient;
    private String username;

    public ClientManager(String hostIP, int port, String username) {
        try {
            this.clientSocket = new Socket(hostIP, port);
            this.outputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.inputStream = new ObjectInputStream(this.clientSocket.getInputStream());
            this.username = username; // TODO check username does not already exists
            this.runClient = true;
            this.sendUsernameToServer();
        } catch (IOException e) {
            this.runClient = false;
            InterfaceManager.displayError(e, "Failed to set streams.");
        }
    }

    public Message getMessage() {
        Message messageObj = null;
        try {
            messageObj = (Message) this.inputStream.readObject();
        } catch (IOException |ClassNotFoundException e) {
            InterfaceManager.displayError(e, "Client Related Error.");
        }
        return messageObj;
    }

    public void sendMessage(String receiver, String message) {
        Message messageObj = new Message(this.username, receiver, message, Message.MESSAGE_TYPE);
        this.sendToChannel(messageObj);
    }

    public void sendInstruction(String receiver, String message) {
        Message messageObj = new Message(this.username, receiver, message, Message.INSTRUCTION_TYPE);
        this.sendToChannel(messageObj);
    }

    private void sendToChannel(Message messageObj) {
        try {
            this.outputStream.writeObject(messageObj);
        } catch (IOException e) {
            this.runClient = false;
            InterfaceManager.displayError(e, "Message send failed.");
        }
    }

    public boolean checkClientConnected() {
        return this.runClient;
    }

    private void sendUsernameToServer() {
        this.sendMessage("SERVER", this.username);
    }
}
