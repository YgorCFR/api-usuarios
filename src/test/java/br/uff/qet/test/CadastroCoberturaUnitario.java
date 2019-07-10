package br.uff.qet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.uff.qet.configuration.CORSFilter;
import br.uff.qet.configuration.UserConfiguration;
import br.uff.qet.configuration.UserInitializer;
import br.uff.qet.controller.UserController;
import br.uff.qet.model.User;
import br.uff.qet.service.UserService;
import br.uff.qet.service.UserServiceImpl;


public class CadastroCoberturaUnitario {
	
	
	@Test
	public void testarCadastro(){
		//Declarando o serviço de gerenciamento de usuários
		UserServiceImpl service = new UserServiceImpl();
		
		//Instanciando usuários
		User usuarioPrimeiro = new User(2, "fizz@email.com", 78000, "Fizz", "ssds");
		User usuarioSegundo = new User(3, "buzz@email.com", 1230, "Buzz", "eerapo");
		
		//Criando usuários
		service.saveUser(usuarioPrimeiro);
		service.saveUser(usuarioSegundo);
		
		//Verificando se os mesmos existem
		service.isUserExist(usuarioPrimeiro);
		service.isUserExist(usuarioSegundo);
		
		//Iniciando montagem de um ranking pelo email dos usuarios	
		ArrayList<User> ranking = new ArrayList<User>();
		ranking.add(service.findByEmailSenha(service.findById(2).getEmail(), service.findById(2).getSenha()));
		ranking.add(service.findByEmailSenha(service.findById(1).getEmail(), service.findById(1).getSenha()));
		ranking.add(service.findByEmailSenha(service.findById(3).getEmail(), service.findById(3).getSenha()));
		
		//Ordernando ranking
		Collections.sort(ranking, Comparator
				.comparing(User :: getPontos).reversed());
		
		for (User user : ranking) {
			System.out.println(user.getEmail());
		}
		
		ranking.get(ranking
				.indexOf(service
				.findByEmail(usuarioPrimeiro.getEmail())))
				.setEmail("buzzbuzz@gmail.com");
		
		service.updateUser(ranking.get(ranking.indexOf(service.findByEmail(usuarioPrimeiro.getEmail()))));
		
		
		assertEquals("buzzbuzz@gmail.com", ranking.get(0).getEmail());
	}
	
	@Test
	public void testarEdicoes() {
		UserServiceImpl service = new UserServiceImpl();
		service.saveUser(new User(1, "pessoa@email.com", 23000, "Pessoa", "person"));
		System.out.println(service.findAllUsers());
		
		assertNotNull(service.isUserExist(service.findByEmail("pessoa@email.com")));
		
		service.deleteUserById(4);
		
		System.out.println(service.findByEmailSenha("admin@email.com", "admin").getPontos());
		System.out.println(service.findByEmailSenha("admin@email.com", "admin").getId());
		System.out.println(service.findByEmailSenha("admin@email.com", "admin").getNome());
		
		service.findByEmailSenha("admin@email.com", "admin").setSenha("reficofages");
		service.findByEmailSenha("admin@email.com", "reficofages").setPontos(345600);
		service.findByEmailSenha("admin@email.com", "reficofages").setNome("Ygor");
		
		System.out.println((service.findByEmailSenha("admin@email.com", "reficofages").hashCode()));
		
		if(!(service.findByEmailSenha("admin@email.com", "reficofages").getNome().equals(service.findByEmailSenha("buzzbuzz@gmail.com", "ssds").getNome()))){
			System.out.println(true);
		}
	
		
	}
	
	@Test
	public void testarListaUsuarios() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		assertNotNull(userServiceImpl.findAllUsers());
	}
	
	@Test
	public void testarProcurarPorEmailSenha() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		assertNull(userServiceImpl.findByEmailSenha("admin@email.com", "reficofages"));
	}
	
	@Test
	public void testarProcurarPorId() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		assertNull(userServiceImpl.findById(5));
	}
	
	@Test 
	public void testarCriarUsuario() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		User user = new User();
		user.setEmail("admin@email.com");
		user.setPontos(90000);
		user.setNome("admin");
		user.setSenha("admin");
		userServiceImpl.saveUser(user);
	}
	
	@Test
	public void deletarUmUsuario() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.deleteUserById(1);
	}
	
	@Test
	public void deletarTodosUsuario() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.deleteAllUsers();
	}
	
	@Test
	public void testarListarUmUsuarioController() {
		UserController controller = new UserController();
		assertNull(controller.getUser(4));
	}
	
	@Test
	public void testarEditarUsuarioController() {
		UserController controller = new UserController();
		assertNull(controller.deleteUser(4));
	}
	
	@Test
	public void testarLoginUserController() {
		UserController controller = new UserController();
		assertNull(controller.loginUser(new User(0, "admin@email.com", 89000, "admin", "admin")));
	}
	
	@Test
	public void testarEditarUsuarioPorId() {
		UserController controller = new UserController();
		controller.getClass();
		controller.hashCode();
		UserService service;
		assertNull(controller.updateUser(0, new User(0,"admin@email.com", 89000, "admin", "admin")));
	}
	
	@Test
	public void testarListarTodosUsuarios() {
		UserController controller = new UserController();
		assertNotNull(controller.listAllUsers());
	}
	
	
	
	@Test
	public void testarInicializer() {
		UserInitializer init = new UserInitializer();
		init.getClass();
		init.hashCode();
	}
	
	@Test
	public void testarCORS() {
		CORSFilter filter = new CORSFilter();
		filter.hashCode();
		filter.getClass();
	}
	
	@Test
	public void testarConfig() {
		UserConfiguration config = new UserConfiguration();
		config.getClass();
		config.hashCode();
	}
	
	
	
}
