package br.uff.qet.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.uff.qet.model.User;
import br.uff.qet.service.UserServiceImpl;

/*Nesta classe serão feitos os testes de mutação para cada componentes mais evidentes da classe UserServiceImpl, responsável pela implementação 
 * das regras de negócio da funcionalidade gerenciamento de usuários do jogo. Um adicional, será feita a montagem de um
 * ranking de usuário para realizar verificações mais complexas.*/
public class CadastrosMutantes {
	
	
	/*Nesse teste é verificado a existência de um determinado usuário.*/
	@Test
	public void verificaUsuarios() {
		
		//Declara a classe de servicos da api de gerenciamento de usuário.
		UserServiceImpl service = new UserServiceImpl();
		
		//Verificando se um usuario existe
		assertTrue(service.isUserExist(new User(1, "admin@email.com", 8000, "admin", "admin")));
	}
	
	
	/*Nesse teste é verificado o número de usuários presentes na aplicação sendo montado um ranking do mesmo*/
	@Test
	public void montaRanking() {
		//Declara a classe de servicos da api de gerenciamento de usuário.
		UserServiceImpl service = new UserServiceImpl();
		
		//Verifica se há algum usuário cadastrado
		if(service.findAllUsers().size() > 0) {
			
			//Testa o método de montar ranking ordenado por pontos, para verificar se não está nulo.
			assertNotNull(service.buildUserRanking());
		}
		else {
			
			//Testa o método de montar ranking ordenado por pontos, para verificar se está nulo
			assertNull(service.buildUserRanking());
		}
	}
	
	
	
	/*Nesse teste é verificado se o login de um usuário se constitui válido*/
	@Test
	public void validaRegistroUsuarioPosEdicao() {
		//Declara a classe de servicos da api de gerenciamento de usuário.
		UserServiceImpl service = new UserServiceImpl();
		
		
		if (service.findByEmail("admin@email.com") == null){
			//Testando o metodo que auxilia no login do usuario é inválido
			assertNull(service.findByEmailSenha("admin@email.com", "admin"));
		}
		else {
			
			//Testando o metodo que auxilia no login do usuario é válido
			assertNotNull(service.findByEmailSenha("admin@email.com", "admin"));
		}
		
	}
	
	
}
