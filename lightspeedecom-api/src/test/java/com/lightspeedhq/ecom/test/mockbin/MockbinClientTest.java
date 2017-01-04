package com.lightspeedhq.ecom.test.mockbin;

import feign.FeignException;
import com.lightspeedhq.ecom.test.InSequence;
import com.lightspeedhq.ecom.test.InSequenceRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 *
 * @author stevensnoeijen
 */
@RunWith(InSequenceRunner.class)
public class MockbinClientTest {

    private MockbinClient mockbinClient;
    private static String id;

    public MockbinClientTest() {
        mockbinClient = MockbinFactory.create();
    }

    @Test
    @InSequence(1)
    public void create() {
        Bin bin = new Bin();
        bin.setStatus(200);
        bin.setStatusText("OK");
        bin.setHttpVersion("HTTP/1.1");
        bin.getContent().setMimeType("text/html");
        bin.getContent().setText("\"test ok\"");

        id = mockbinClient.create(bin);
    }

    @Test
    @InSequence(2)
    public void view() {
        Bin bin = mockbinClient.view(id);
        assertNotNull(bin);
    }

    @Test
    @InSequence(3)
    public void request() {
        String response = mockbinClient.request(id);
        assertEquals("test ok", response);
    }

    @Test
    @InSequence(4)
    public void log() {
        LogBody body = mockbinClient.log(id);
        assertNotNull(body.getLog());
        assertEquals(1, body.getLog().getEntries().size());
    }

    @Test(expected = FeignException.class)
    public void expectedError() {
        mockbinClient.create(new Bin());
    }

}
