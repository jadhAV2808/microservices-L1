package com.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.models.CatalogItem;
import com.microservice.models.Movie;
import com.microservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfo {
	
	@Autowired
	RestTemplate restTemplate=new RestTemplate();
	
	
	@HystrixCommand(fallbackMethod="getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		 
		Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
         
           return new CatalogItem(movie.getName(),"desc",rating.getRating());
           
	}
	
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("movie name not found","",rating.getRating());
		
		
	}
		

}
