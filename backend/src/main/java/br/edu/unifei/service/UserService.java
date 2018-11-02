package br.edu.unifei.service;

import java.util.List;

import br.edu.unifei.model.User;

public interface UserService {

	User findById(long id);
	void saveUser(User user);
	void updateUser(User user);
	void deleteUser(User user);
	List<User> findAllUsers();
	void deleteUserById(long id);
	
}
