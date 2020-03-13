package demoTask;

import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class allegroTask {
    private WebDriver driver;

    @Before

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After

    public void quitDriver() {
        driver.close();
        driver.quit();
    }

    @Test

    public void searchTest() {
        driver.get("https://allegro.pl/");

        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@class='_13q9y _8hkto _nu6wg _1sql3 _qdoeh _l7nkx _nyhhx']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@name='string']")))).sendKeys("Iphone 11");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div/div/div/div[2]/div[2]/div[1]/div[3]/div/div/div/div/div/fieldset[10]/div/ul/li[3]/label")))).click();

        List<WebElement> liczbaIphone = driver.findElements(By.xpath("//article"));
        System.out.println("Ilość wyświetlonych telefonów: " + liczbaIphone.size());

        Select sortDropdawn = new Select(driver.findElement(By.xpath("//select[@class='_1h7wt _k70df _7qjq4 _27496_3VqWr']")));
        sortDropdawn.selectByVisibleText(" cena: od najwyższej ");

        driver.navigate().refresh();

        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div/div/div/div[2]/div[1]/div[3]/div/div/div/div[2]/div[1]/div/section[2]/section/article[1]/div/div/div[2]")).click();

        WebElement bestPrice = driver.findElement(By.xpath("//div[@class='_wtiln _bdn9q _9a071_2MEB_']"));
        bestPrice.getText();
        System.out.println("Najwyższa cena: " +bestPrice.getAttribute("aria-label") + " + 23%");

        String text = driver.findElement(By.id("add-to-cart-button")).getText();
        Assert.assertEquals("DODAJ DO KOSZYKA" , text);

    }
}
