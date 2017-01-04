package com.lightspeedhq.ecom;

import org.aeonbits.owner.Config;

/**
 *
 * @author stevensnoeijen
 */
@Config.Sources("file:test.properties")
public interface LightspeedEComTestConfig extends Config {

    @Config.Key("webshop.lightspeed.cluster")
    public String getCluster();

    @Config.Key("webshop.lightspeed.language")
    public String getLanguage();

    @Config.Key("webshop.lightspeed.apiKey")
    public String getApiKey();

    @Config.Key("webshop.lightspeed.apiSecret")
    public String getApiSecret();
}
