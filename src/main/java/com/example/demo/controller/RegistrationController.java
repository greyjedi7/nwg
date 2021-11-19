package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Registration;
import com.example.demo.repository.RegistrationRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/registers")
public class RegistrationController {
	@Autowired
	private RegistrationRepository bp;
	
	@PostMapping("/register")
	public ResponseEntity<Registration> save(@RequestBody Registration b) {
		System.out.println(b);
		Registration bk = bp.save(b);
		return new ResponseEntity<Registration>(bk,HttpStatus.OK);
	}
	


@GetMapping("/getDetails/{userid}/{password}")

public ResponseEntity<Registration> getUser(@PathVariable("userid") int userid, @PathVariable("password") String password)
{
	Optional<Registration> detail = bp.findByUseridAndPassword(userid,password);
	if(detail.isPresent()) {
		System.out.println("Hi");
		Registration bk=detail.get();
		return new ResponseEntity<Registration>(bk,HttpStatus.OK);
	}
	return new ResponseEntity<Registration>(HttpStatus.NOT_FOUND);
			
	}
}

