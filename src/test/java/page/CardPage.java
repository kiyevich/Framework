package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CardPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(xpath = "//li[@class='checkout-item']/a")
	WebElement CardItemTitle;

	@FindBy(xpath = "(//p[@class='checkout-item__props']/span)[2]")
	WebElement CardItemSize;

	@FindBy(xpath = "(//p[@class='price']/span)[1]")
	WebElement CardItemPrice;

	@FindBy(id = "ch-promo")
	WebElement promoCodeField;

	@FindBy(xpath = "//p[@class='notify__text']")
	WebElement notifyText;

	public CardPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public CardPage openPage(String ItemPageUrl)
	{
		driver.get(ItemPageUrl);
		logger.info("Login page opened");
		return this;
	}

	public String getCardItemTitle(){
		String resultCardItemTitle = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(CardItemTitle))
				.getText();
		return resultCardItemTitle;
	}

	public String getCardItemSize(){
		String resultCardItemSize = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(CardItemSize))
				.getText();
		return resultCardItemSize;
	}

	public String getCardItemPrice(){
		String resultCardItemPrice = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(CardItemPrice))
				.getText();
		return resultCardItemPrice;
	}

	public CardPage sendPromoCode(String promoCode){
		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.elementToBeClickable(promoCodeField))
				.sendKeys(promoCode, Keys.ENTER);

		return this;
	}

	public String getNotifyText(){
		String resultNotifyText = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.visibilityOf(notifyText))
				.getText();
		return resultNotifyText;
	}

}
