package com.blacksky.helmtest.reader.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.blacksky.helmtest.common.dto.MessageDTO;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MessageServiceTest {

    @Test
    public void receive_keepsLast100MessagesInOrder() {
        MessageService svc = new MessageService();

        // add 105 messages
        for (int i = 0; i < 105; i++) {
            MessageDTO m = new MessageDTO();
            m.setTime("t-" + i);
            m.setVersion("v");
            m.setHostname("h");
            svc.receive(m);
        }

        List<MessageDTO> last = svc.getLastMessages();
        assertEquals(100, last.size());
        // first should be t-5 (indexes 5..104)
        assertEquals("t-5", last.get(0).getTime());
        // last should be t-104
        assertEquals("t-104", last.get(last.size() - 1).getTime());
    }
}
