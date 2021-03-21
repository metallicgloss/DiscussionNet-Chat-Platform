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

    public boolean server_chat_message = false;
    public int messageType;
    public String sender, receiver, message;
    public Timestamp timestamp;

    public Message(String sender, String receiver, String message, int type) {
        this.message = message;
        this.messageType = type;
        this.receiver = receiver;
        this.sender = sender;
    }

    public Message(String sender, String receiver, String message, int type, boolean server_chat_message) {
        this.message = message;
        this.messageType = type;
        this.receiver = receiver;
        this.sender = sender;
        this.server_chat_message = server_chat_message;
    }

}

