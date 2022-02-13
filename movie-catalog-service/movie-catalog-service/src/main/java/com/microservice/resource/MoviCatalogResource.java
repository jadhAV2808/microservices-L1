package com.microservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.models.CatalogItem;
import com.microservice.models.Movie;
import com.microservice.models.Rating;
import com.microservice.models.UserRating;
import com.microservice.service.MovieInfo;
import com.microservice.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MoviCatalogResource {
	
	
	
	@Autowired
	RestTemplate restTemplate=new RestTemplate();
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo  userRatingInfo;
	

	
	@GetMapping("/{userId}")
//	@HystrixCommand(fallbackMethod="getFallbackCatalog")
	public List<CatalogItem>getCatalog( @PathVariable("userId") String userId){
//		
//		List<Rating>ratings=Arrays.asList(
//				new Rating("1234",4),
//				new Rating("5678",3)
//				);
		
//		RestTemplate restTemplate=new RestTemplate();
		
	
		
		
//2)
		
//UserRating ratings=restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" +userId, UserRating.class);
//		
//		return ratings.getUserRating().stream()
//		           .map(rating -> {
//	               Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
//		             		         
//		        	   return new CatalogItem(movie.getName(),"desc",rating.getRating());
//		           })
//		   
//		           .collect(Collectors.toList());
//		
//		
		
		
		UserRating userRating=userRatingInfo.getUserRating(userId);
		return UserRating.getRatings().stream()
				 .map(rating ->movieInfo.getCatalogItem(rating))
				 .collect(Collectors.toList());
		
		
//1)			
//		return Collections.singletonList(
//				new CatalogItem("transformer","test",4)
//				);
//		
		
	}
	
	

	
	


//	public List<CatalogItem>getFallbackCatalog( @PathVariable("userId") String userId){
//		
//		return Arrays.asList(new CatalogItem("mo movie", "" , 0));
//		
//	}


}


//Movie movie=webClientBuilder.build()
//.get()
//.uri("http://localhost:8082/movies/"+ rating.getMovieId())
//.retrieve()
//.bodyToMono(Movie.class)
//.block();
//

