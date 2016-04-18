package by.bsu.up.chat.storage;

import by.bsu.up.chat.common.models.Message;

import java.io.IOException;
import java.util.List;

public interface MessageStorage {

    /**
     * @return the list of messages of the specified range
     * @param portion the DTO which defines range of messages to be returned
     */
    List<Message> getPortion(Portion portion);

    /**
     * Adds message to the storage
     * @param message the message to be added
     */
    void addMessage(Message message) throws IOException;

    /**
     * Updates the message, which's id is equal to the id of provided message
     * @param message the message to be saved
     * @return true if message was updated successfully and false otherwise
     */


    boolean updateMessage(String messageId, String text) throws IOException;

    /**
     * Removes the message with the given id
     * @param messageId the id of message which should be deleted
     * @return true if message was deleted successfully and false otherwise
     */
    boolean removeMessage(String messageId) throws IOException;


    /**
     * @return the amount of stored messages
     */
    void read() throws IOException;
    int size();
}
