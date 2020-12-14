package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;

public class ItemPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(xpath = "//button[text() = 'Добавить в корзину']")
	WebElement addCardButton;

	@FindBy(xpath = "//a[@class='header__button js-vue-small-cart']")
	WebElement CardButton;



	public ItemPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public ItemPage openPage(String ItemPageUrl)
	{
		driver.get(ItemPageUrl);
		logger.info("Item page opened");
		return this;
	}

	public ItemPage addItemToCard(){

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)");

		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(addCardButton))
				.click();
		logger.info("Item added");

		return new ItemPage(driver);
	}

	public CardPage goToCard(){
		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(CardButton))
				.click();
		return new CardPage(driver);
	}


}
