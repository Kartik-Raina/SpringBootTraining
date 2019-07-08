/**
 * 
 */
package com.training.city.autocomplete.service;

import java.util.List;

/**
 * @author karraina
 *
 * This is the service interface for the application
 */
public interface ICityAutoCompleteServcie {
    
    /**
     * This method returns the list of cities.
     * @param start : The starting characters of a city name
     * @param atmost : number of suggested names
     * @return
     */
    public List<String> getCityNames(String start, int atmost);
}
