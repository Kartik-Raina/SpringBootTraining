/**
 * 
 */
package com.training.city.autocomplete;

import com.training.city.autocomplete.constants.ApplicationConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author karraina
 *
 *         This class is used for doing all actions before the application starts
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    private Environment env;

    /**
     * This event is executed as late as conceivably possible to indicate that the application is
     * ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        loadData();
        return;
    }

    private void loadData() {
        logger.info("Data load started!");
        String line = "";

        // Get the csv file that you want to load
        String fileName = env.getProperty(ApplicationConstants.SOURCE_FILE_NAME_PROPERTY);
        ClassLoader classLoader = new ApplicationStartup().getClass().getClassLoader();
        // This flag ensures that the first list of csv file
        // as skipped as it contains the headers
        boolean isFirstRow = true;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(classLoader.getResourceAsStream(fileName)))) {

            // Read the csv file line by line
            while ((line = br.readLine()) != null) {
                if (!isFirstRow) {
                    // get the "taluk" attribute of the file and store it as a set.
                    String[] country = line.split(ApplicationConstants.CSV_SEPERATOR);
                    TrainingCityAutoCompleteApplication.cityNames.add(country[7].toLowerCase());
                } else {
                    isFirstRow = false;
                }

            }

        } catch (IOException e) {
            logger.error("Data load incomplete", e);
        }

        logger.info("Data load complete!");
    }

}
