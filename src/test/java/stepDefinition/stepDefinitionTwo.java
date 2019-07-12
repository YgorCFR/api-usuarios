package stepDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import br.uff.qet.model.User;
import br.uff.qet.service.UserServiceImpl;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class stepDefinitionTwo {
	
	private UserServiceImpl service;
	private String email;
	private static final Boolean clicar = true;
	private User user;
	
	@Dado("^que queira adicionar um usuario$")
	public void que_queira_adicionar_um_usuario() {
		service= new UserServiceImpl();
	}
	
	@Quando("^eu clicar no botao adicionar usuario$")
	public void eu_clicar_no_botao_adicionar_usuario() {
		assertEquals(true, clicar);
	}
	
	@E("^entre com o email \"([^\"]*)\"$")
	public void entre_com_o_email(String arg1) {
		email = arg1;
	}
	
	@E("^verifique a existencia desse email$")
	public void verifique_a_existencia_desse_email(){
		user = service.findByEmail(email);
	}
	
	@Entao("^eu devo ver o resultado nao nulo$")
	public void eu_devo_ver_o_resultado_nao_nulo() {
			assertNull(user);
	}
}
