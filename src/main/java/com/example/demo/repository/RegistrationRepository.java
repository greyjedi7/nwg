package com.example.demo.repository;

import com.example.demo.model.Registration;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Integer>
{
	
	Optional<Registration> findByUseridAndPassword(int userid,
			String password);
}
