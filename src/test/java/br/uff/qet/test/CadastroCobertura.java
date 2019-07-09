package br.uff.qet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import br.uff.qet.configuration.UserConfiguration;
import br.uff.qet.controller.UserController;
import br.uff.qet.model.User;
import br.uff.qet.service.UserService;
import br.uff.qet.service.UserServiceImpl;


public class CadastroCobertura {
	
	
	
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
	
	//@Test
	public void testarEdicoes() {
		
	}
}
