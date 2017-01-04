package com.lightspeedhq.ecom.test.mockbin;

import java.util.Collections;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author stevensnoeijen
 */
public class Log {

    public static class Creator {

        @Getter
        private String name;

        @Getter
        private String version;
    }

    public static class Entry {

        public static class Request {

            public static class PostData {

                @Getter
                private String mimeType;

                @Getter
                private String text;

                @Getter
                private List<Bin.NameValue> params;
            }

            @Getter
            private String method;

            @Getter
            private String url;

            @Getter
            private String httpVersion;

            @Getter
            private List<Bin.NameValue> cookies;

            @Getter
            private List<Bin.NameValue> headers;

            @Getter
            private PostData postData;

            /**
             *
             * @param name of cookie to find
             * @return value of cookie or null if not found
             */
            public String findCookie(String name) {
                return cookies.stream()
                        .filter(c -> c.getName().equals(name))
                        .findFirst()
                        .orElseGet(() -> new Bin.NameValue()).getValue();
            }

            /**
             *
             * @param name of header to find
             * @return value of header or null if not found
             */
            public String findHeader(String name) {
                return headers.stream()
                        .filter(h -> h.getName().equals(name))
                        .findFirst()
                        .orElseGet(() -> new Bin.NameValue()).getValue();
            }
        }

        @Getter
        private String startedDateTime;

        @Getter
        private String clientIPAddress;

        @Getter
        private Request request;

        @Getter
        private List<Bin.NameValue> queryString;

        @Getter
        private int headerSize;

        @Getter
        private int bodySize;
    }

    @Getter
    private String version;

    @Getter
    private Creator creator;

    @Getter
    private List<Entry> entries = Collections.emptyList();

}
