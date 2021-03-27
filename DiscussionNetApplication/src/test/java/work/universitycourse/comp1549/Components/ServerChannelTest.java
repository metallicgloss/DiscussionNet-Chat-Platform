package work.universitycourse.comp1549.Components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */

// #---------------------------------------------------------------------------#
// #                                 Contents                                  #
// #---------------------------------------------------------------------------#
// #                                                                           #
// #                           Server Channel Testing                          #
// #       Unit and integration tests of the ServerChannel functionality.      #
// #                                                                           #
// #                1 - Initalise ServerChannel Instance                       #
// #                2 - Add & Retrieve Message To Channel Test                 #
// #                3 - Add Empty Message To Channel Test                      #
// #                4 - Successfully Retrieve Message From Channel             #
// #                                                                           #
// #---------------------------------------------------------------------------#

public class ServerChannelTest {

    public ServerChannelTest() {
    }

    private ServerChannel instance;

    // #-----------------------------------------------------------------------#
    // #                  1. Initalise ServerChannel Instance                  #
    // #-----------------------------------------------------------------------#

    @BeforeEach
    public void setUp() {
        this.instance = new ServerChannel();
    }

    // #-----------------------------------------------------------------------#
    // #               2. Add & Retrieve Message To Channel Test               #
    // #-----------------------------------------------------------------------#

    @Test
    public void testAddMessageToChannel() {
        // Initialise Message Object
        Message messageObj = new Message("userA", "userB", "testmessage");

        // Add message object to channel stream.
        this.instance.addTransmittableToChannel(messageObj);

        // Verify that next message queued in channel is the message.
        assertEquals(messageObj, this.instance.getNextTransmittableFromChannel());
    }

    // #-----------------------------------------------------------------------#
    // #                 3. Add Empty Message To Channel Test                  #
    // #-----------------------------------------------------------------------#

    @Test
    public void testNullAddMessageToChannel() {
        // Check empty channel returns null.
        assertEquals(null, this.instance.getNextTransmittableFromChannel());
    }

    // #-----------------------------------------------------------------------#
    // #                    4. Check Client Connection Exists                  #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCheckClientConnectionExists() {
        // Check if non-existant client exists.
        assertEquals(false, this.instance.checkClientConnectionExists("ClientA"));
    }

}
