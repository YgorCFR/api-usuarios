package br.uff.qet.test;

import org.junit.Test;

import br.uff.qet.model.User;
import br.uff.qet.service.UserServiceImpl;

public class CadastrosEstruturalTeste {
	
	/*Neste exemplo se encontra o teste estrutural que demonstrará o código que realiza criação de um usuário 
	 * num único bloco, os grafos estarão em um arquivo a parte.*/
	
	
	/*O método gerenciamentoDeUsuario() criar um usuario no intuito de demonstrar o Teste Estrutural dessas funciona
	 * lidades do programa.*/
	@Test
	public void gerenciamentodeUsuarioCriacao() {
	
		//Declarações:
		/*1*/UserServiceImpl service = new UserServiceImpl();
		/*2*/User usuario = new User();
		
		//Criando usuário
		/*3*/
		usuario.setEmail("algumacoisadasilva@email.com");
		usuario.setNome("Alguma Coisa da Silva");
		usuario.setPontos(89000);
		usuario.setSenha("alguma@1999");
		
		//Pegando o email para verificar se existe algum outro usuario com esse email
		/*4*/String email = usuario.getEmail();
		
		/*5*/for(User user : service.findAllUsers()) {
		/*6*/	if(user.getEmail() == email) {
		/*7*/			System.out.println("Usuario ja existe");
		/*8*/				break;
				}
			 }
		
		//Salvando o usuario
		/*9*/service.saveUser(usuario);
		
		/*10*/System.out.println("Usuario criado com sucesso");
	}
	
	@Test
	public void gerenciamentoDeUsuarioEdicao() {
		//Declarações:
		/*1*/UserServiceImpl service = new UserServiceImpl();
		/*2*/long id = 2;
		/*3*/User usuario = new User();
		/*4*/String email = "palafita@email.com";
		/*5*/double pontos = 12000;
		/*6*/for (User user : service.findAllUsers()) {
		/*7*/	if(user.getId() == id) {
		/*11*/		usuario = user;
		/*12*/		usuario.setEmail(email);
		    		usuario.setPontos(pontos);
		/*13*/    	service.updateUser(usuario);
				System.out.println("Usuario atualizado com sucesso");
				}
			  }
		/*8*/ if(usuario.getEmail() == null) {
		/*9*/	 System.out.println("Usuario nao existe");
		/*10*/	  } //caso else{}
	}
	
}
