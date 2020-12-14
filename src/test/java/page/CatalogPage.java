package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(xpath = "//p[@class='empty__text']")
	WebElement emptyMessageText;

	public CatalogPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public CatalogPage openPage(String ItemPageUrl)
	{
		driver.get(ItemPageUrl);
		logger.info("Catalog page opened");
		return this;
	}

	public String getEmptyMessageText()
	{
		String resultEmptyMessageText = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.visibilityOf(emptyMessageText))
				.getText();
		return resultEmptyMessageText;
	}



}
