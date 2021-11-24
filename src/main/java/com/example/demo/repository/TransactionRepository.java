package com.example.demo.repository;

import com.example.demo.model.Transaction;
import com.example.demo.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	Optional<Transaction> getId(String counterParty, String party)
			;
}