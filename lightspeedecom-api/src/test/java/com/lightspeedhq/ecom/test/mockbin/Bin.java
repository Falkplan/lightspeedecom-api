package com.lightspeedhq.ecom.test.mockbin;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author stevensnoeijen
 */
public class Bin {

    public static class NameValue {

        @Getter
        @Setter
        private String name;

        @Getter
        @Setter
        private String value;
    }

    public static class Content {

        @Getter
        @Setter
        private int compression;

        @Getter
        @Setter
        private String mimeType;

        @Getter
        @Setter
        private int size;

        @Getter
        @Setter
        private String text;
    }

    @Getter
    @Setter
    private int status = 200;

    @Getter
    @Setter
    private String statusText;

    @Getter
    @Setter
    private String httpVersion;

    @Getter
    @Setter
    private List<NameValue> headers = new ArrayList<>();

    @Getter
    @Setter
    private List<NameValue> cookies = new ArrayList<>();

    @Getter
    @Setter
    private Content content = new Content();

    @Getter
    @Setter
    private String redirectURL;

    @Getter
    @Setter
    private int headersSize;

    @Getter
    @Setter
    private int bodySize;
}
