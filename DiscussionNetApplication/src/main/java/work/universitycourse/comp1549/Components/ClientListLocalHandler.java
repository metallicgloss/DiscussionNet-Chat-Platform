package work.universitycourse.comp1549.Components;

import java.util.HashMap;
import work.universitycourse.comp1549.Components.ClientDetail;
import work.universitycourse.comp1549.Exceptions.ClientIDAlreadyInListException;

public class ClientListLocalHandler {
    private HashMap<String, ClientDetail> clientListLocal = new HashMap<String, ClientDetail>();

    public ClientListLocalHandler() {}
    
    // TODO If not using this constructor remove it
    public ClientListLocalHandler(HashMap<String, ClientDetail> clientList) {
        this.setClientListLocal(clientList);
    }

    // Adds a new client's details to the local client list
    public void addNewClientDetails(ClientDetail clientDetailsObj) throws ClientIDAlreadyInListException {

        if (! this.clientListLocal.containsKey(clientDetailsObj.clientID)) {
            this.clientListLocal.put(clientDetailsObj.clientID, clientDetailsObj);
        } else {
            throw new ClientIDAlreadyInListException("The client ID '" + clientDetailsObj.clientID + "' already exists in the local client list");
        }

    }

    // Set the client list to a predefined client list
    public void setClientListLocal(HashMap<String, ClientDetail> newClientListLocal) {
        this.clientListLocal = newClientListLocal;
    }

    // Get the local client list
    public HashMap<String, ClientDetail> getLocalClientList() {
        return this.clientListLocal;
    }

}
