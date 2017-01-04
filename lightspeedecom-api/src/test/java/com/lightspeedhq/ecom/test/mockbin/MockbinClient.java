package com.lightspeedhq.ecom.test.mockbin;

import feign.Param;
import feign.RequestLine;

/**
 *
 * @author stevensnoeijen
 * @see <a href="https://www.mockbin.org/docs">https://www.mockbin.org/docs</a>
 */
public interface MockbinClient {

    public static String DOMAIN = "https://www.mockbin.org";

    @RequestLine("POST /bin/create")
    public String create(Bin bin);

    @RequestLine("GET /bin/{id}/view")
    public Bin view(@Param("id") String id);

    @RequestLine("GET /bin/{id}")
    public String request(@Param("id") String id);

    @RequestLine("GET /bin/{id}/log")
    public LogBody log(@Param("id") String id);
}
