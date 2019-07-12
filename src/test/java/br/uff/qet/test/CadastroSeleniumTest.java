package br.uff.qet.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


/*Classe responsavel por realizar o teste automatizado de selenium de realizar login e acessar o ranking or
 * denado por pontos*/

public class CadastroSeleniumTest {

	static WebDriver driver;
	
	@BeforeClass
	public static void configurar_BuscasDoGoogle() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		//Favor trocar o caminho quando for testar no seu computador
		driver.get("file:///C:/Users/ygor.rolim/Documents/QeT/api-usuarios-client/views/main.html"); 
	}
	
	@Test
	public void executeTeste() {
		System.out.println("Realizando teste...");
		
	}
	
	@Test
	public void navegarAteRanking() {
		WebElement input = driver.findElement(By.name("femail"));
		input.sendKeys("admin@email.com");
		
		input = driver.findElement(By.name("fsenha"));
		input.sendKeys("admin");
		
		input = driver.findElement(By.id("botaoSelecionado"));
		input.click();
	}

	
}
