package com.gcit.training.spring;

import org.springframework.context.annotation.Bean;

import com.gcit.training.spring.lms.service.AdminstratorProfile;
import com.gcit.training.spring.lms.service.BorrowerProfiles;
import com.gcit.training.spring.lms.service.LibrarianProfile;

public class ProfilesConfiguration {
	@Bean(name = "borrowerProfile")
	public BorrowerProfiles borrowerProfile() {
		return new BorrowerProfiles();
	}
	@Bean(name = "librarianProfile")
	public LibrarianProfile librarianProfile() {
		return new LibrarianProfile();
	}
	@Bean(name = "adminProfile")
	public AdminstratorProfile administratorProfile() {
		return new AdminstratorProfile ();
	}
}
