package id.ac.tazkia.dosen.controller;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseSeleniumTests {

    @LocalServerPort
    private int serverPort;

    protected String baseUrl;
    protected WebDriver webDriver = new HtmlUnitDriver();

    @PostConstruct
    public void setupWebDriver(){
        baseUrl = String.format("http://localhost:%d", serverPort);
    }
}
