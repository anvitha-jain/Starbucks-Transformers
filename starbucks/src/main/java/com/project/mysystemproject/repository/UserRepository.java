package com.project.mysystemproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mysystemproject.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
//    @Query("SELECT u FROM User u WHERE u.username = 'username'")
//	public List<User> findMyUser(@Param("username") String username);
//    
    public List<User> findByusername(String username);
    
   // public List<User> findByusernameORpassword(String username, String password);
    
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    public List<User> findByusernameAndpassword(@Param("username") String username, 
    		  @Param("password") String password);
    
    
//    @Query("SELECT u FROM User u WHERE u.username = 'username' AND u.password = 'password'")
//    public List<User> findByusernameAndpassword(String username, String password);
    
    
}