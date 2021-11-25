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
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


/**
 * Added Password Encryption
 */

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/registers")
public class UserController {
	@Autowired
	private UserRepository bp;

	@Autowired
	private TransactionRepository tr;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/register")
	public ResponseEntity<User> save(@RequestBody User b) {
		LOGGER.info(b.toString());

		Optional<User> detail = bp.findByUseridAndPassword(b.getUserid(),b.getPassword());
		if(detail.isPresent()) {
			LOGGER.info("User is present already");
			return new ResponseEntity("User already registered",HttpStatus.OK);
		} else {
			LOGGER.info("User is new");
			User bk = bp.save(b);
			return new ResponseEntity<User>(bk, HttpStatus.OK);
		}
	}

	@PostMapping("/transaction")
	public ResponseEntity transaction(@RequestBody Transaction transaction){
		LOGGER.info(transaction.toString());

		User counterparty = bp.getById(Integer.parseInt(transaction.getCounterParty()));
		User party = bp.getById(Integer.parseInt(transaction.getParty()));

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
public ResponseEntity<User> getUser(@PathVariable("userid") String userid, @PathVariable("password") String password)
{
	String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
	Optional<User> detail = bp.findByMailAndPassword(userid,encryptedPassword);
	if(detail.isPresent()) {
		LOGGER.info("Hi");
		User bk=detail.get();
		return new ResponseEntity<User>(bk,HttpStatus.OK);
	}
	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			
	}
}
