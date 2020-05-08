package com.sean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("config/appconfig.yml")
@ConfigurationProperties(prefix = "packer")
public class AppConfig {

    public static AppConfig instance;

    public AppConfig(){
        AppConfig.instance = this;
    }

    @Value("${iosPath}")
    private String iosPath;
    @Value("${androidPath}")
    private String androidPath;
    @Value("${tempPath}")
    private String tempPath;
    @Value("${gamePath}")
    private String gamePath;

    public String getIosPath() {
        return iosPath;
    }

    public String getAndroidPath() {
        return androidPath;
    }

    public String getTempPath() {
        return tempPath;
    }

    public String getGamePath() {
        return gamePath;
    }

}
