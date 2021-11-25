package com.example.demo.repository;

import com.example.demo.model.User;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
	
	Optional<User> findByUseridAndPassword(int userid,
			String password);
	
	Optional<User> findByMailAndPassword(String mail, String password);
}
