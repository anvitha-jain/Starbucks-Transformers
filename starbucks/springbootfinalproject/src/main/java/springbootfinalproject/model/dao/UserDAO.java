package springbootfinalproject.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import springbootfinalproject.model.User;
import springbootfinalproject.repository.UserRepository;

@Service
public class UserDAO {
	
	@Autowired
	UserRepository userRepository;
	
	/*to save an user*/
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
//	/* search all users*/
//	public List<User> findAll(){
//		return userRepository.findAll();
//	}
	
	
	/*get an user by id*/
	public User findOne(Long id) {
		return userRepository.findOne(id);
		
	}

}
