package com.joker.jokerspringbootstarterautoconfigurer;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "joker.hello")
public class HelloPoperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
