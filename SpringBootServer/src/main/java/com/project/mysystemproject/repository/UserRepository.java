package com.project.mysystemproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mysystemproject.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
    
    public List<User> findByusername(String username);
    
   
    
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    public List<User> findByusernameAndpassword(@Param("username") String username, 
    		  @Param("password") String password);
    

    
    
}