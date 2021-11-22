package com.example.demo.controller;
import java.util.Base64;
import java.util.Optional;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
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

	@Autowired
	private TransactionRepository tr;

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@PostMapping("/register")
	public ResponseEntity<Registration> save(@RequestBody Registration b) {
		LOGGER.info(b.toString());

		Optional<Registration> detail = bp.findByUseridAndPassword(b.getUserid(),b.getPassword());
		if(detail.isPresent()) {
			LOGGER.info("User is present already");
			return new ResponseEntity("User already registered",HttpStatus.OK);
		} else {
			LOGGER.info("User is new");
			Registration bk = bp.save(b);
			return new ResponseEntity<Registration>(bk, HttpStatus.OK);
		}
	}

	@PostMapping("/transaction")
	public ResponseEntity transaction(@RequestBody Transaction transaction){
		LOGGER.info(transaction.toString());

		Registration counterparty = bp.getById(Integer.parseInt(transaction.getCounterParty()));
		Registration party = bp.getById(Integer.parseInt(transaction.getParty()));

		if(counterparty == null){
			return new ResponseEntity("Counter Party is not found\nTransaction Failed", HttpStatus.NOT_FOUND);
		}

		if(party == null){
			return new ResponseEntity("Party is not found\nTransaction Failed", HttpStatus.NOT_FOUND);
		}



		Double transactionAmount = transaction.getTransactionAmount();

		Double partyBalance = party.getBalance();

		if(partyBalance < transactionAmount){
			return new ResponseEntity("Transaction failed because of insufficient balance", HttpStatus.CONFLICT);
		}
		LOGGER.info(party.toString());
		LOGGER.info(counterparty.toString());
		party.setBalance(party.getBalance() - transactionAmount);
		counterparty.setBalance(counterparty.getBalance() + transactionAmount);


		transaction.setRemainingBalance(party.getBalance());

		party.getTransactions().add(transaction);
		counterparty.getTransactions().add(transaction);

		tr.save(transaction);

		LOGGER.info(party.getTransactions().get(0).toString());
		LOGGER.info(counterparty.getTransactions().get(0).toString());

		bp.save(party);
		bp.save(counterparty);



		return new ResponseEntity("",HttpStatus.OK);
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

