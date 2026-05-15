package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:config.properties"
})
public interface Configuration extends Config {

    @Key("api.base.url")
    @DefaultValue("https://restful-booker.herokuapp.com")
    String apiBaseUrl();

    @Key("api.username")
    @DefaultValue("admin")
    String apiUsername();

    @Key("api.password")
    @DefaultValue("password123")
    String apiPassword();

    @Key("graphql.url")
    @DefaultValue("https://us-east-1-shared-usea1-02.cdn.hygraph.com/content/clpvcopq3aavs01usft1idkgj/master")
    String graphqlUrl();

    @Key("ui.base.url")
    @DefaultValue("https://demoqa.com")
    String uiBaseUrl();

    @Key("browser")
    @DefaultValue("chromium")
    String browser();

    @Key("headless")
    @DefaultValue("true")
    boolean headless();

    @Key("timeout")
    @DefaultValue("10000")
    int timeout();
}
