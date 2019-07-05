package br.uff.qet.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import br.uff.qet.model.User;
import br.uff.qet.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user/", method= RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if(users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Selecionando usuario com id: " + id);
		User user = userService.findById(id);
		if(user == null) {
			System.out.println("Usuario com id: " + id + " nao encontrado.");
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login/", method = RequestMethod.POST)
	public ResponseEntity<User> loginUser(@RequestBody User userLog) {
		System.out.println("Logando usuario: " + userLog.getEmail());
		User user = userService.findByEmailSenha(userLog.getEmail(), userLog.getSenha());
		
		if(user == null) {
			System.out.println("Usuario nao encontrado.");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/" , method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		System.out.println("Criando Player :" + user.getEmail());
		if(userService.findByEmail(user.getEmail()) == null) {
			System.out.println("Um usuario com email " + user.getEmail() + " ja existe");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} 
		
		userService.saveUser(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	} 
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Atualizando usuário: " + id);
		User currentUser = userService.findById(id);
		
		if(currentUser == null) {
			System.out.println("Usuário com id" + id + " não encontrado.");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		currentUser.setNome(user.getNome());
		currentUser.setEmail(user.getEmail());
		currentUser.setSenha(user.getSenha());
		currentUser.setPontos(user.getPontos());
		
		userService.updateUser(currentUser);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Selecionando e deletando player com id: " + id);
		
		User user = userService.findById(id);
		
		if(user == null) {
			System.out.println("Usuário não encontrado com o id: " + id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Deletando todos os usuários");
		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
}
