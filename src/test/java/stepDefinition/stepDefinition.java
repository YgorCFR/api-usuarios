package stepDefinition;


import static org.junit.Assert.assertNotNull;


import br.uff.qet.model.User;
import br.uff.qet.service.UserServiceImpl;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class stepDefinition {
	
	UserServiceImpl user;
	String email, senha;
	
	@Dado("^que eu queira logar$")
	public void que_eu_escolha_logar() {
		user = new UserServiceImpl();
	}
	
	@Quando("^eu preencho o primeiro campo com o valor \"([^\"]*)\"$")
	public void eu_preencho_o_primeiro_campo_com_o_valor(String arg1) {
		email = arg1;
	}
	
	@E("^eu preencho o segundo campo com o valor \"([^\"]*)\"$")
	public void eu_preencho_o_segundo_campo_com_o_valor(String arg2) {
		senha = arg2;
	}
	
	@Entao("^eu devo ver o resultado não nulo Usuario$")
	public void eu_devo_ver_o_resultado() {
		User arg = user.findByEmailSenha(email, senha);
		assertNotNull(arg);
	}
}
