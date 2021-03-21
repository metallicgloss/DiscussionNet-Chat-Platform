package work.universitycourse.comp1549.Components;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int INSTRUCTION_TYPE = 1;
    public static final int MESSAGE_TYPE = 2;

    public boolean isServerChatMessage = false;
    public int messageType;
    public String sender, receiver, message;
    public Timestamp timestamp;

    public Message(String sender, String receiver, String message, int type) {
        this.message = message;
        this.messageType = type;
        this.receiver = receiver;
        this.sender = sender;
    }

    public Message(String sender, String receiver, String message, int type, boolean isServerChatMessage) {
        this.message = message;
        this.messageType = type;
        this.receiver = receiver;
        this.sender = sender;
        this.isServerChatMessage = isServerChatMessage;
    }

    public String toString() {
        
        return this.sender + "::" + this.receiver + "::" + Integer.toString(this.messageType) +
         "::" + this.message + "::" + this.timestamp.toString() + "::" + Boolean.toString(this.isServerChatMessage);
        
    }

    public static Message fromString(String messageObjString) {
        
        // Seperate String into variables
        String[] messageComponents = messageObjString.split("::");
        String sender = messageComponents[0];
        String receiver = messageComponents[1];
        int messageType = Integer.parseInt(messageComponents[2]);
        String message = messageComponents[3];
        Timestamp timestamp = Timestamp.valueOf(messageComponents[4]);
        boolean isServerChatMessage = messageComponents[5].equals("true");

        // Create message object and set timestamp
        Message messageObj = new Message(sender, receiver, message, messageType, isServerChatMessage);
        messageObj.timestamp = timestamp;

        return messageObj;

    }

}

