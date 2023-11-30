package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;

import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class HomePageSteps {

    static WebDriver driver;

    private HomePage homePage;

    @Before
    public void setUp(){
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
            String browser = properties.getProperty("browser");

            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser: " + browser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    @Given("user on the Hepsiburada web page")
    public void user_on_the_Hepsiburada_web_page() {
        homePage = new HomePage(driver);
        homePage.navigateToHomePage();
    }

    @When("user selects {string} in the menu")
    public void user_selects_in_the_menu(String menu) {
        homePage.selectMenu(menu);
    }

    @When("user selects {string} and {string} in the child menu")
    public void user_selects_and_in_the_child_menu(String childMenu, String childSubMenu) {
        homePage.selectChildMenu(childMenu,childSubMenu);
    }

    @When("user select brand {string}")
    public void user_select_brand(String brand)  {
        homePage.selectBrand(brand);
    }

    @When("user select bestsellers")
    public void user_select_bestsellers() {
        homePage.selectBestSeller();
    }

    @When("user adds the 2nd product in the product list to the cart")
    public void user_adds_the_2nd_product_in_the_product_list_to_the_cart() throws InterruptedException {
        homePage.addProduct();
    }

    @When("user goes to the cart and verifies the product")
    public void user_goes_to_the_cart_and_verifies_the_product() throws InterruptedException {
        homePage.goBasket();
    }

    @When("user clicks on the complete shopping button")
    public void user_clicks_on_the_complete_shopping_button() {
        homePage.completeShopping();
    }

    @Then("user should see redirected to the login page")
    public void user_should_see_redirected_to_the_login_page() {
        homePage.checkLoginPage();
    }
}







