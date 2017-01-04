package com.lightspeedhq.ecom.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import java.util.ArrayList;
import lombok.Getter;

/**
 * Retrieve the available languages of a shop.
 *
 * @see <a href="http://developers.seoshop.com/api/resources/language">http://developers.seoshop.com/api/resources/language</a>
 * @author stevensnoeijen
 */
//@JsonRootName("language")
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonRootName("languages")
    public static class List extends ArrayList<Language> {
    }

    /**
     * The unique numeric identifier for the language.<br/>
     * Example: <code>{"id": "{id}"}</code>
     */
    @Getter
    private int id;

    /**
     * The language may be disabled.<br/>
     * Example: <code>{"isActive": "false|true"}</code>
     */
    @Getter
    @JsonProperty("isActive")
    private boolean isActive;

    /**
     * Two letter code representing the language.<br/>
     * (format: ISO 639-1)<br/>
     * Example: <code>{"code": "de"}</code>
     */
    @Getter
    private String code;

    /**
     * Two two letter codes seperated by a underscore.<br/>
     * (format: RFC 1766 - ISO 639)<br/>
     * Example: <code>{"locale": "de_DE"}</code>
     */
    @Getter
    private String locale;

    /**
     * Title of the language.<br/>
     * Example: <code>{"title": "Deutsch"}</code>
     */
    @Getter
    private String title;
}
