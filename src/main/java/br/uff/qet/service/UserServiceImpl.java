package br.uff.qet.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uff.qet.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static {
		users = populate();
	}
	
	public User findById(long id) {
		for (User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User findByEmail(String email) {
		for (User user : users) {
			if(user.getEmail() == email) {
				return user;
			}
		}
		return null;
	}
	
	public User findByEmailSenha(String email, String senha) {
		for(User user : users) {
			if((user.getEmail().trim().equals( email.trim())) && (user.getSenha().trim().equals( senha.trim()))) {
				System.out.println("Usuario encontrado.");
				return user;
			}
		}
		return null;
	}
	
	public void deleteUserById(long id) {
		for(Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	public void saveUser (User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}
	
	public void updateUser (User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}
	
	public boolean isUserExist(User user) {
		return findByEmail(user.getEmail()) != null;
	}
	
	private static List<User> populate() {
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "admin@email.com", 8000, "admin", "admin"));
		users.add(new User(counter.incrementAndGet(), "riffs@rmail.com", 8500, "riffs", "ffff"));
		users.add(new User(counter.incrementAndGet(), "buzz@email.com", 4000, "buzz", "buzz"));
		users.add(new User(counter.incrementAndGet(), "fizz@email.com", 5000, "fizz", "fizz"));
		users.add(new User(counter.incrementAndGet(), "zucs@email.com", 87000, "zucs", "zucs"));
		users.add(new User(counter.incrementAndGet(), "klemm@email.com", 8000, "klemm", "klemm"));
		users.add(new User(counter.incrementAndGet(), "oopas@email.com", 1000, "oopas", "oopas"));
		users.add(new User(counter.incrementAndGet(), "qquu@email.com", 8300, "qquu", "qquu"));
		users.add(new User(counter.incrementAndGet(), "zebraz@email.com",48000, "zebraz", "zebraz"));
		return users;
	}
	
	public void deleteAllUsers() {
		users.clear();
	}
	
	public List<User> buildUserRanking() {
		if(users == null) {
			return null;
		}else {
				users.sort(Comparator.comparingDouble(User:: getPontos)
				.reversed());
		}
		return users;
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}
}
