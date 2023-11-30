package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage{

    private WebDriver driver;

    private By menuLocator = By.cssSelector("span[class='sf-MenuItems-UHHCg2qrE5_YBqDV_7AC']");

    private By childMenuLocator = By.cssSelector("div[class='span[class=sf-MenuItems-UHHCg2qrE5_YBqDV_7AC']");

    private By childSubMenuLocator = By.cssSelector("a[class='sf-ChildMenuItems-OIXGN6gTcuAXz_dkh8Yv item-2290']>span");

    private By brandCheckbox = By.xpath("/html/body/div[1]/div/div/main/div/div/div[3]/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div[6]/label/input");

    private By arrangementLocator = By.cssSelector("label[class='horizontalSortingBar-Ce404X9mUYVCRa5bjV4D']");

    private By bestSellerLocator = By.cssSelector("a[class='horizontalSortingBar-wxPx75gvwcKxOM8rMngA unVisible']");

    private By productLocator = By.cssSelector("li[class='productListContent-zAP0Y5msy8OHn5z7T_K_']>div>a");

    private By addBasketLocator = By.cssSelector("button[data-test-id='product-info-button']");

    private By toastTextLocator = By.cssSelector("div[class='hb-toast-text-holder']");

    private By toastBasketLinkLocator = By.cssSelector("a[class='hb-toast-link']");

    private By productNameLocator = By.cssSelector("div[class='product_name_2Klj3']");
    private By completeShoppingLocator = By.id("continue_step_btn");
    private By loginPageFormLocator = By.cssSelector("div[class='_169liE0fur389xWAzdBDFM']");
    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void navigateToHomePage() {
        driver.get("https://hepsiburada.com");
        Assert.assertTrue(driver.getTitle().contains("Online Alışveriş Sitesi Hepsiburada"));
    }

    public void selectMenu(String Menu) {
        List<WebElement> Menus = findElements(menuLocator);
        Actions actions = new Actions(driver);
        actions.moveToElement(Menus.get(0)).build().perform();
    }

    public void selectChildMenu(String childMenuItem, String childSubMenu) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement childSubMenu1 = wait.until(ExpectedConditions.visibilityOfElementLocated(childSubMenuLocator));
        childSubMenu1.click();
    }

    public void selectBrand(String brand) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        WebElement element = driver.findElement(brandCheckbox);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.navigate().refresh();
    }

    public void selectBestSeller() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        WebElement arrangement = findElement(arrangementLocator);
        arrangement.isDisplayed();
        arrangement.click();
        List<WebElement> bestSeller = findElements(bestSellerLocator);
        bestSeller.get(2).click();
        driver.navigate().refresh();
    }
    public void addProduct() throws InterruptedException {
        List<WebElement> product = findElements(productLocator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        Actions actions = new Actions(driver);
        actions.moveToElement(product.get(1)).build().perform();
        WebElement addBasket = findElement(addBasketLocator);
        addBasket.click();
        Thread.sleep(1500);
        WebElement toastText = findElement(toastTextLocator);
        Assert.assertTrue(toastText.isDisplayed(), "Element görünmüyor.");
    }

    public void goBasket() throws InterruptedException {
        WebElement toastBasketLink = findElement(toastBasketLinkLocator);
        Thread.sleep(1500);
        toastBasketLink.click();
        WebElement productName = findElement(productNameLocator);
        Assert.assertTrue(productName.isDisplayed(),"ürün sepette görüntülenmedi");
    }

    public void completeShopping() {
        WebElement completeShopping = findElement(completeShoppingLocator);
        completeShopping.click();
    }

    public void checkLoginPage() {
        WebElement loginPageForm = findElement(loginPageFormLocator);
        Assert.assertTrue(loginPageForm.isDisplayed(), "login page form görüntülenmedi");
    }

}