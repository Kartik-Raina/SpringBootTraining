package com.training.city.autocomplete;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.city.autocomplete.service.ICityAutoCompleteServcie;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrainingCityAutoCompleteApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TrainingCitiAutoCompleteApplicationTests {

    @Autowired
    ICityAutoCompleteServcie citiAutoCompleteServcie;

    @Before
    public void initValues() {
    	TrainingCityAutoCompleteApplication.cityNames.add("Delhi");
    	TrainingCityAutoCompleteApplication.cityNames.add("MUMBAI");
    	TrainingCityAutoCompleteApplication.cityNames.add("Bangalore");
    	TrainingCityAutoCompleteApplication.cityNames.add("kolkata");
    }

    @Test
    public void findCityNormalCase() {
        assertNotNull(citiAutoCompleteServcie.getCityNames("del", 1));
    }
    
    @Test
    public void findCityCitiBlank() {
        assertNotNull(citiAutoCompleteServcie.getCityNames("", 1));
    }
    
    @Test
    public void findCityAtmostZero() {
        assertNotNull(citiAutoCompleteServcie.getCityNames("mum", 0));
    }

}
