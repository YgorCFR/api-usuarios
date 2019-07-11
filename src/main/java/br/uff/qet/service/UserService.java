package br.uff.qet.service;

import java.util.List;

import br.uff.qet.model.User;

public interface UserService {
	
	User findById(long id);
	
	User findByEmail(String email);
	
	User findByEmailSenha(String email, String senha);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);
	
	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
	public List<User> buildUserRanking();
}
