package work.universitycourse.comp1549.Components;

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
// #                            Client Info Testing                            #
// #          Unit tests of ClientInfo functionality - string methods.         #
// #                                                                           #
// #                   1 - Message Export As String Test                       #
// #                   2 - Message Parse From String Test                      #
// #                                                                           #
// #---------------------------------------------------------------------------#

public class ClientInfoTest {

    // #-----------------------------------------------------------------------#
    // #                    1. Message Export As String Test                   #
    // #-----------------------------------------------------------------------#

    @Test
    // Test to validate that after initialisation, string returned matches.
    public void testToString() {
        ClientInfo client = new ClientInfo("TestID", "192.168.0.1", 11);

        // Assert string matches.
        assertEquals("TestID,192.168.0.1,11", client.toString());
    }

    // #-----------------------------------------------------------------------#
    // #                  2. - Message Parse From String Test                  #
    // #-----------------------------------------------------------------------#

    @Test
    // Test to validate that if not all parameters contain content, function does not error.
    public void testToStringError() {
        ClientInfo instance = new ClientInfo("TestID", "", 11);

        // Assert string matches.
        assertEquals("TestID,,11", instance.toString());
    }
}
