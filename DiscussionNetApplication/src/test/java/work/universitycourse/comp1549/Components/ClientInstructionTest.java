package work.universitycourse.comp1549.Components;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import work.universitycourse.comp1549.Components.ClientInstruction.DataFormatException;
import work.universitycourse.comp1549.Components.ClientInstruction.InstructionFormatException;
import work.universitycourse.comp1549.Components.ClientInstruction.InstructionNotExistException;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 *
 */

// #---------------------------------------------------------------------------#
// #                                 Contents                                  #
// #---------------------------------------------------------------------------#
// #                                                                           #
// #                             ClientInstruction                             #
// #   Definitions, validations and exceptions for client sided instructions.  #
// #                                                                           #
// #     1 - Convert Instruction to String                                     #
// #     2 - Generate Create Message Instruction String                        #
// #     3 - Generate Become Coordinator Instruction String                    #
// #     4 - Generate Revoke Coordinator Instruction String                    #
// #     5 - Generate Establish Connection Instruction String                  #
// #     6 - Generate Review Join Request Instruction String                   #
// #     7 - Generate Reject Join Request Instruction String                   #
// #     8 - Generate Accept Client Connection Instruction String              #
// #     9 - Generate Update Client Information Cache Instruction String       #
// #     10 - Generate Add Client to List Instruction String                   #
// #     11 - Generate Notify Disconnected Instruction String                  #
// #     12 - Generate Client Disconnected Instruction String                  #
// #     13 - Generate Get Updated List Instruction String                     #
// #     14 - Generate Client Accepted Instruction String                      #
// #     15 - Generate Set Local Info List Instruction String                  #
// #     16 - Generate Connection Rejected Coordinator Instruction String      #
// #     17 - Generate New Coordinator Instruction String                      #
// #                                                                           #
// #---------------------------------------------------------------------------#

public class ClientInstructionTest {

    public ClientInstructionTest() {
    }

    // #-----------------------------------------------------------------------#
    // #                   1. Convert Instruction to String                    #
    // #-----------------------------------------------------------------------#

    @Test
    // Test To String from any instruction.
    public void testConvertInstructionToString()
            throws InstructionNotExistException, InstructionFormatException, DataFormatException {
        // Generate example become coordinator instruction.
        ClientInstruction instruction = new ClientInstruction("2<SEPERATOR>BECOME COORDINATOR");

        // Test to see if matches string.
        assertEquals("2<SEPERATOR>BECOME COORDINATOR", instruction.convertInstructionToString());
    }

    // #-----------------------------------------------------------------------#
    // #             2. Generate Create Message Instruction String             #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateSendMessageInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("1<SEPERATOR>CLIENTB::Example Message::false",
                ClientInstruction.createSendMessageInstructionString("CLIENTB", "Example Message", false));
    }

    // #-----------------------------------------------------------------------#
    // #           3. Generate Become Coordinator Instruction String           #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateBecomeCoordinatorInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("2<SEPERATOR>BECOME COORDINATOR", ClientInstruction.createBecomeCoordinatorInstructionString());
    }

    // #-----------------------------------------------------------------------#
    // #           4. Generate Revoke Coordinator Instruction String           #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateRevokeCoordinatorInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("3<SEPERATOR>REVOKE COORDINATOR", ClientInstruction.createRevokeCoordinatorInstructionString());
    }

    // #-----------------------------------------------------------------------#
    // #          5. Generate Establish Connection Instruction String          #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateEstablishConnectionInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("4<SEPERATOR>CLIENTA", ClientInstruction.createEstablishConnectionInstructionString("CLIENTA"));
    }

    // #-----------------------------------------------------------------------#
    // #          6. Generate Review Join Request Instruction String           #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateReviewJoinRequestInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("5<SEPERATOR>TEMPID::CLIENTA::127.0.0.1::9091",
                ClientInstruction.createReviewJoinRequestInstructionString("TEMPID", "CLIENTA", "127.0.0.1", 9091));
    }

    // #-----------------------------------------------------------------------#
    // #          7. Generate Reject Join Request Instruction String           #
    // #-----------------------------------------------------------------------#

    @Test
    public void createRejectJoinRequestInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("6<SEPERATOR>TEMPID", ClientInstruction.createRejectJoinRequestInstructionString("TEMPID"));
    }

    // #-----------------------------------------------------------------------#
    // #        8. Generate Accept Client Connection Instruction String        #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateAcceptClientConnectionInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("7<SEPERATOR>TEMPID::CLIENTA",
                ClientInstruction.createAcceptClientConnectionInstructionString("TEMPID", "CLIENTA"));
    }

    // #-----------------------------------------------------------------------#
    // #     9. Generate Update Client Information Cache Instruction String    #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateUpdateClientInfosServerCacheInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("8<SEPERATOR>STRINGHERE",
                ClientInstruction.createUpdateClientInfosServerCacheInstructionString("STRINGHERE"));
    }

    // #-----------------------------------------------------------------------#
    // #           10. Generate Add Client to List Instruction String          #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateAddClientInfoToLocalListInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("9<SEPERATOR>CLIENTA::127.0.0.1::9091",
                ClientInstruction.createAddClientInfoToLocalListInstructionString("CLIENTA", "127.0.0.1", 9091));
    }

    // #-----------------------------------------------------------------------#
    // #          11. Generate Notify Disconnected Instruction String          #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateNotifyClientDisconnectedInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("10<SEPERATOR>CLIENTA",
                ClientInstruction.createNotifyClientDisconnectedInstructionString("CLIENTA"));
    }

    // #-----------------------------------------------------------------------#
    // #          12. Generate Client Disconnected Instruction String          #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateClientDisconnectedInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("11<SEPERATOR>CLIENTA", ClientInstruction.createClientDisconnectedInstructionString("CLIENTA"));
    }

    // #-----------------------------------------------------------------------#
    // #           13. Generate Get Updated List Instruction String            #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateGetUpdatedClientInfoListInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("12<SEPERATOR>CLIENTB",
                ClientInstruction.createGetUpdatedClientInfoListInstructionString("CLIENTB"));
    }

    // #-----------------------------------------------------------------------#
    // #            14. Generate Client Accepted Instruction String            #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateClientAcceptedInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("13<SEPERATOR>COORDINATOR",
                ClientInstruction.createClientAcceptedInstructionString("COORDINATOR"));
    }

    // #-----------------------------------------------------------------------#
    // #          15. Generate Set Local Info List Instruction String          #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateSetLocalClientInfoListString() {
        // Check function generates instruction as expected.
        assertEquals("14<SEPERATOR>STRINGHERE", ClientInstruction.createSetLocalClientInfoListString("STRINGHERE"));
    }

    // #-----------------------------------------------------------------------#
    // #    16. Generate Connection Rejected Coordinator Instruction String    #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateConnectionRejectedByCoordinatorInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("15<SEPERATOR>Example Message",
                ClientInstruction.createConnectionRejectedByCoordinatorInstructionString("Example Message"));
    }

    // #-----------------------------------------------------------------------#
    // #            17. Generate New Coordinator Instruction String            #
    // #-----------------------------------------------------------------------#

    @Test
    public void testCreateNotifyOthersOfNewCoordinatorInstructionString() {
        // Check function generates instruction as expected.
        assertEquals("16<SEPERATOR>COORDINATOR",
                ClientInstruction.createNotifyOthersOfNewCoordinatorInstructionString("COORDINATOR"));
    }

}
