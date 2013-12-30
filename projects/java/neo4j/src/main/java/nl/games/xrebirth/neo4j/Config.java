package nl.games.xrebirth.neo4j;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: bram
 * Date: 29-12-13
 * Time: 1:26
 * To change this template use File | Settings | File Templates.
 */
public class Config {
    private static Config ourInstance = new Config();

    private Configuration configuration;

    public static Configuration getConfiguration() {
        return ourInstance.configuration;
    }

    private Config() {
        try {
            PropertiesConfiguration config = new PropertiesConfiguration("xrebirth.properties");
            config.setReloadingStrategy(new FileChangedReloadingStrategy());
            this.configuration = config;
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }



}
