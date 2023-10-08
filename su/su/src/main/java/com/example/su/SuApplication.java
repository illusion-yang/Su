package com.example.su;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@SpringBootApplication
public class SuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuApplication.class, args);
	}

//	@GetMapping("/id")
//	public String index(){
//		return "我搭建了一个服务器";
//	}
}
