package config;

import org.aeonbits.owner.ConfigCache;

public class TestConfig {
    public static Configuration config() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
