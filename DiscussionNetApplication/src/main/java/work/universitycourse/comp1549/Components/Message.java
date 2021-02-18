package work.universitycourse.comp1549.Components;

import java.sql.Timestamp;
import java.io.Serializable;


/**
 *
 * @author Adnan Turna
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final int INSTRUCTION_TYPE = 1;
    public static final int MESSAGE_TYPE = 2;

    public String sender, receiver, message;
    public int messageType;
    public Timestamp timestamp;

    public Message(String sender, String receiver, String message, int type) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.messageType = type;
    }
}