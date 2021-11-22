package com.example.demo.controller;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


/**
 * Added Password Encryption
 */

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/registers")
public class RegistrationController {
	@Autowired
	private RegistrationRepository bp;

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@PostMapping("/register")
	public ResponseEntity<Registration> save(@RequestBody Registration b) {
		LOGGER.info(b.toString());
		Registration bk = bp.save(b);

		return new ResponseEntity<Registration>(bk,HttpStatus.OK);
	}
	


@GetMapping("/getDetails/{userid}/{password}")
public ResponseEntity<Registration> getUser(@PathVariable("userid") int userid, @PathVariable("password") String password)
{
	String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
	Optional<Registration> detail = bp.findByUseridAndPassword(userid,encryptedPassword);
	if(detail.isPresent()) {
		LOGGER.info("Hi");
		Registration bk=detail.get();
		return new ResponseEntity<Registration>(bk,HttpStatus.OK);
	}
	return new ResponseEntity<Registration>(HttpStatus.NOT_FOUND);
			
	}
}

