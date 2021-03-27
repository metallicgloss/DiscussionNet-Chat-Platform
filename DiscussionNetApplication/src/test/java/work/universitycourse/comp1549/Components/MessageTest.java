package work.universitycourse.comp1549.Components;

import java.sql.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
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
// #                              Message Testing                              #
// #                   Unit tests of Message functionality.                    #
// #                                                                           #
// #               1 - Initalise Timestamp Value                               #
// #               2 - Export DM Instruction to String                         #
// #               3 - Export Group Message Instruction to String              #
// #               4 - Generate DM Instruction From String                     #
// #               5 - Generate Group Message Instruction From String          #
// #                                                                           #
// #---------------------------------------------------------------------------#

public class MessageTest {

    public MessageTest() {
    }

    private Timestamp timestamp;

    // #-----------------------------------------------------------------------#
    // #                     1. Initalise Timestamp Value                      #
    // #-----------------------------------------------------------------------#

    @BeforeEach
    public void setUp() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    // #-----------------------------------------------------------------------#
    // #                   2. Export DM Instruction to String                  #
    // #-----------------------------------------------------------------------#

    @Test
    // Test to validate that direct message string outputs as expected.
    public void testDirectMessageToString() {
        Message directMessage = new Message("ClientA", "ClientB", "Example Message");
        directMessage.timestamp = this.timestamp;

        // Assert string matches.
        assertEquals("ClientA::ClientB::Example Message::" + this.timestamp + "::false", directMessage.toString());
    }

    // #-----------------------------------------------------------------------#
    // #             3. Export Group Message Instruction to String             #
    // #-----------------------------------------------------------------------#

    @Test
    // Test to validate that group message string outputs as expected.
    public void testGroupMessageToString() {
        Message groupMessage = new Message("ClientA", "ClientB", "Example Message", true);
        groupMessage.timestamp = this.timestamp;

        // Assert string matches.
        assertEquals("ClientA::ClientB::Example Message::" + this.timestamp + "::true", groupMessage.toString());
    }

    // #-----------------------------------------------------------------------#
    // #                 4. Generate DM Instruction From String                #
    // #-----------------------------------------------------------------------#

    @Test
    // Test to validate that direct message ingest as string works as expected.
    public void testDirectMessageFromString() {
        // Generate message object from string import.
        Message directMessageConstructed = Message
                .fromString("ClientA::ClientB::Example Message::2021-02-01 01:01:01.000000001::false");

        // Initiate message directly.
        Message directMessage = new Message("ClientA", "ClientB", "Example Message");
        directMessage.timestamp = Timestamp.valueOf("2021-02-01 01:01:01.000000001");

        // Assert matches values
        assertTrue(new ReflectionEquals(directMessage).matches(directMessageConstructed));
    }

    // #-----------------------------------------------------------------------#
    // #           5. Generate Group Message Instruction From String           #
    // #-----------------------------------------------------------------------#

    @Test
    // Test to validate that group message ingest as string works as expected.
    public void testGroupMessageFromString() {
        // Generate message object from string import.
        Message groupMessageConstructed = Message
                .fromString("ClientA::ClientB::Example Message::2021-02-01 01:01:01.000000001::true");

        // Initiate message directly.
        Message groupMessage = new Message("ClientA", "ClientB", "Example Message", true);
        groupMessage.timestamp = Timestamp.valueOf("2021-02-01 01:01:01.000000001");

        // Assert matches values
        assertTrue(new ReflectionEquals(groupMessage).matches(groupMessageConstructed));
    }
}
