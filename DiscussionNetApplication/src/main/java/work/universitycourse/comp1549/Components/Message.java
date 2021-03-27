package work.universitycourse.comp1549.Components;

import java.sql.Timestamp;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */

// #---------------------------------------------------------------------------#
// #                                  Contents                                 #
// #---------------------------------------------------------------------------#
// #                                                                           #
// #                                  Message                                  #
// #        Message object for data transfer between clients connected.        #
// #                                                                           #
// #                           1 - Direct Message                              #
// #                           2 - Group Message                               #
// #                           3 - To String                                   #
// #                           4 - From String                                 #
// #                                                                           #
// #---------------------------------------------------------------------------#

@SuppressWarnings("serial")
public class Message extends Transmittable {

    public boolean isServerChatMessage = false;
    public String message;

    // #-----------------------------------------------------------------------#
    // #                          1 - Direct Message                           #
    // #-----------------------------------------------------------------------#

    // Initiate object with four parameters, direct personal message.
    // Abstract factory design pattern potentiality
    public Message(String sender, String receiver, String message) {

        this.sender = sender;
        this.receiver = receiver;
        this.message = message;

    }

    // #-----------------------------------------------------------------------#
    // #                          2 - Group Message                            #
    // #-----------------------------------------------------------------------#

    // Initiate object with five parameters, group chat message.
    public Message(String sender, String receiver, String message, boolean isServerChatMessage) {

        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.isServerChatMessage = isServerChatMessage;

    }

    // #-----------------------------------------------------------------------#
    // #                          3 - To String                                #
    // #-----------------------------------------------------------------------#

    // Generate string instruction from message object.
    public String toString() {
        // Return single message string.
        return this.sender + "::" + this.receiver + "::" + this.message + "::" + this.timestamp.toString() + "::"
                + Boolean.toString(this.isServerChatMessage);
    }

    // #-----------------------------------------------------------------------#
    // #                          4 - From String                              #
    // #-----------------------------------------------------------------------#

    // Split message string into message objects - used when deciphering received.
    public static Message fromString(String messageObjString) {

        // Seperate String into variables
        String[] messageComponents = messageObjString.split("::");
        String sender = messageComponents[0];
        String receiver = messageComponents[1];
        String message = messageComponents[2];
        Timestamp timestamp = Timestamp.valueOf(messageComponents[3]);
        boolean isServerChatMessage = messageComponents[4].equals("true");

        // Create message object and set timestamp
        Message messageObj = new Message(sender, receiver, message, isServerChatMessage);
        messageObj.timestamp = timestamp;

        return messageObj;

    }

}
