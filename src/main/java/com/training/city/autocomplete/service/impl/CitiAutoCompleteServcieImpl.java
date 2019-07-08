/**
 * 
 */
package com.training.city.autocomplete.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.training.city.autocomplete.TrainingCityAutoCompleteApplication;
import com.training.city.autocomplete.service.ICityAutoCompleteServcie;

/**
 * @author karraina
 *
 */
@Service
public class CitiAutoCompleteServcieImpl implements ICityAutoCompleteServcie {

	private static final Logger logger = LoggerFactory.getLogger(CitiAutoCompleteServcieImpl.class);

	@Override
	public List<String> getCityNames(String start, int atmost) {
		List<String> resultList = new ArrayList<String>();
		Set<String> cityNames = TrainingCityAutoCompleteApplication.cityNames;
		// Convert Set to stream for applying filter
		try (Stream<String> cityStream = cityNames.stream().filter((c) -> c.startsWith(start.toLowerCase()))) {
			if (!start.trim().isEmpty() && atmost <= cityNames.size() && atmost != 0) {
				// return the list of result as per the atmost value
				resultList.addAll(cityStream.limit(atmost).collect(Collectors.toList()));
			} else {
				// return all records
				resultList.addAll(cityStream.collect(Collectors.toList()));
			}

		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return resultList;
	}
}
