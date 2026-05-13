package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:configUI.properties"})
public interface Configuration extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("browser")
    String browser();

    @Key("headless")
    boolean headless();

    @Key("timeout")
    int timeout();

    @Key("slow.mo")
    int slowMo();
}
