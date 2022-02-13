package com.microservice.models;

import java.util.Collection;
import java.util.List;

public class UserRating {
	private int ratings;
	
	
	private List<Rating>userRating;

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}

	

	
	

}
