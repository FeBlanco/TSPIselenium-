package testes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LojinhaWEBTest {

    private WebDriver navegador;

    @Before
    public  void setUp() {
        //Preparação
        System.setProperty("webdriver.gecko.driver", "C:/selenium/geckodriver.exe");
        navegador = new FirefoxDriver();
        navegador.get("http://165.227.93.41/lojinha-web/");

        //Login com usuário admin e senha admin
        navegador.findElement(By.cssSelector("#usuario")).sendKeys("admin");
        navegador.findElement(By.id("senha")).sendKeys("admin");
        navegador.findElement(By.cssSelector(".btn")).click();

    }
    @Test
    public void testValidarDadosDeUmProduto () {
        //Acessar o produto Ps4 na lista do produtos
        navegador.findElements(By.linkText("PS4")).get(0).click();

        // Validação do Nome do Produto e do nome do primeiro Componente
        String produtonome = navegador.findElement(By.cssSelector("#produtonome")).getAttribute("value");
        Assert.assertEquals("PS4", produtonome);

        String componentenome = navegador.findElements(By.cssSelector(".title")).get(0).getText();
        Assert.assertEquals("Controles", componentenome);
    }

    @Test
    public void testAdicionarUmProduto () {
        //Clicar em adicionar um produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        //Preencher os dados do produto
        navegador.findElement(By.id("produtonome")).sendKeys("PS10");
        navegador.findElement(By.id("produtovalor")).sendKeys("30000");
        navegador.findElement(By.id("produtocores")).sendKeys("azul,verde");
        navegador.findElements(By.cssSelector(".btn")).get(0).click();

        //Validar que a mensagem de produto adicionado foi apresentado no toast
        String mensagem = navegador.findElement(By.cssSelector(".toast")).getText();
        Assert.assertEquals("Produto adicionado com sucesso", mensagem);
    }
    @After
    public void tearDown () {
        //Fechar Navegador
        navegador.quit();

    }
}
