package work.universitycourse.comp1549.Modules;


import java.util.Deque;
import java.util.ArrayDeque;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.InetAddress;

import work.universitycourse.comp1549.Components.ClientInstruction;
import work.universitycourse.comp1549.Components.Message;
import work.universitycourse.comp1549.Modules.InterfaceManager;

import work.universitycourse.comp1549.Exceptions.InstructionNotExistException;
import work.universitycourse.comp1549.Exceptions.InstructionFormatException; 
import work.universitycourse.comp1549.Exceptions.DataFormatInvalidException; 

import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class ClientManager {

    private Socket clientSocket;
    private String username;

    private Thread serverListenerThread;
    private Thread instructionsHandlerThread;

    // private InstructionsManager instructionsManager;
    private InstructionsManager instructionsManager = new InstructionsManager();

    public ClientManager(String serverIP, int serverPort, String username, String clientIP, int clientPort) {
        try {
            // Create socket
            InetAddress inetAddress = InetAddress.getByName(clientIP);
            this.clientSocket = new Socket(serverIP, serverPort, inetAddress, clientPort);
            
            // Define other variables
            this.username = username; // TODO check username does not already exists
            
            // Make contact with the server (send username to client)
            this.instructionsManager.addSendMessageInstruction("SERVER", this.username);
            
            // Create Threads
            InstructionsHandler instructionsHandler = new InstructionsHandler(new ObjectOutputStream(this.clientSocket.getOutputStream()), this.instructionsManager, this.username);
            this.instructionsHandlerThread = new Thread(instructionsHandler);
            
            ServerListener serverListener = new ServerListener(new ObjectInputStream(this.clientSocket.getInputStream()), this.instructionsManager);
            this.serverListenerThread = new Thread(serverListener);

            // Start Threads
            this.serverListenerThread.start();
            this.instructionsHandlerThread.start();

        } catch (IOException e) {
            InterfaceManager.displayError(e, "Failed to set streams.");
        }
    }

    public void sendMessage(String receiver, String message) {
        this.instructionsManager.addSendMessageInstruction(receiver, message);
    }

    private class ServerListener implements Runnable {

        private ObjectInputStream inputStream;
        private InstructionsManager instructionsManager;

        public ServerListener(ObjectInputStream inputStream, InstructionsManager instructionsManager) {
            this.inputStream = inputStream;
            this.instructionsManager = instructionsManager;
        }

        public Message getMessage() {
            Message messageObj = null;
            try {
                messageObj = (Message) this.inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                InterfaceManager.displayError(e, "Client Related Error.");
            }
            return messageObj;
        }

        @Override
        public void run() {
            while (true) {
                Message serverResponse = this.getMessage();
                if (serverResponse != null) {
                    // handle request
                    switch(serverResponse.messageType) {
                        case Message.INSTRUCTION_TYPE:
                            this.instructionsManager.addInstruction(serverResponse.message);
                            showMessageDialog(null, "Instruction Recieved from " + serverResponse.sender + ": " + serverResponse.message);
                            break;
                        case Message.MESSAGE_TYPE:
                            showMessageDialog(null, "Message Recieved from " + serverResponse.sender + ": " + serverResponse.message);
                            break;
                        default:
                            showMessageDialog(null, "Unknown Message Type Sent: " + Integer.toString(serverResponse.messageType));
                            break;
                    }
                }

                // Put the thread to sleep for a bit before checking again
                this.wait(100);
            }
        }

        private void wait(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                InterfaceManager.displayError(e, "Thread sleep error occurred");
            }
        }
    }

    private class InstructionsHandler implements Runnable {

        private ObjectOutputStream outputStream;
        private InstructionsManager instructionsManager;
        private String username;

        public InstructionsHandler(ObjectOutputStream outputStream, InstructionsManager instructionsManager, String username) {
            this.outputStream = outputStream;
            this.username = username;
            this.instructionsManager = instructionsManager;
        }

        @Override
        public void run() {
            while (true) {
                ClientInstruction instruction = this.instructionsManager.getNextInstruction();
                if (instruction != null) {
                    switch (instruction.instructionType) {
                        case ClientInstruction.SEND_MESSAGE_INSTRUCTION_TYPE: 
                            // SEND MESSAGE TO SERVER
                            String[] dataComponents = instruction.data.split("<SEPERATOR>");
                            String receiver = dataComponents[0];
                            String message = dataComponents[1];
                            this.sendMessage(receiver, message);
                            break;
                        default:
                            // TODO Send custom error message as unexpected instruction was sent
                            // TODO Technically should not be possible as it would of been handled else where
                            break;
                    }
                }

                this.wait(100);
            }
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
                InterfaceManager.displayError(e, "Message send failed.");
            }
        }

        private void wait(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                InterfaceManager.displayError(e, "Thread sleep error occurred");
            }
        }

    }

    private class InstructionsManager {
        private Deque<ClientInstruction> instructions = new ArrayDeque<ClientInstruction>();

        public InstructionsManager() {}

        public void addInstruction(String instruction) {
            try {
                ClientInstruction instructionObj = new ClientInstruction(instruction);
                this.instructions.addLast(instructionObj);
            } catch (InstructionNotExistException | InstructionFormatException| DataFormatInvalidException e) {
                Exception a = e;
                InterfaceManager.displayError(a, "Instruction construction related error");
            }
        }

        public void addSendMessageInstruction(String receiver, String message) {
            String instructionString = ClientInstruction.createSendMessageInstructionString(receiver, message);
            this.addInstruction(instructionString);
        }

        public ClientInstruction getNextInstruction() {
            return this.instructions.poll();
        }
    }

}
