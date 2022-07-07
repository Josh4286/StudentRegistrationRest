package com.mthree;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository, AddressRepository addrRepo) {
		return args -> {
			Address a1 = new Address("15 Random St", "Sydney", 2000);
			Address a2 = new Address("6 Street St", "Canberra", 2500);
			Address a3 = new Address("7 Far Rd", "Sydney", 2000);
			Address a4 = new Address("5 Sad Pl", "Perth", 5000);
			Address a5 = new Address("18 Pitt St", "Adelaide", 3000);
			addrRepo.saveAll(List.of(a1,a2,a3,a4,a5));
			Student josh = new Student("Josh", a1, LocalDate.of(1998,Month.JANUARY,5));
			Student taljit = new Student("Taljit", a2, LocalDate.of(1999,Month.AUGUST,18));
			Student bikin = new Student("Bikin", a1, LocalDate.of(1997,Month.JUNE,15));
			Student dilraj = new Student("Dilraj", a3, LocalDate.of(1997,Month.DECEMBER,24));
			Student sheng = new Student("Sheng", a4, LocalDate.of(1999,Month.FEBRUARY,12));
			Student john = new Student("John", a5, LocalDate.of(1999,Month.MARCH,11));
			Student zac = new Student("Zac", a4, LocalDate.of(1999,Month.SEPTEMBER,29));
			repository.saveAll(List.of(josh,taljit,bikin,dilraj,sheng,john,zac));
		};
	}
	
	//for cross origin
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
	                        .allowedHeaders("*");
	            }
	        };
	    }
	
	
}
