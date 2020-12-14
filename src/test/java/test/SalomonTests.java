package test;

import model.User;
import page.CardPage;
import page.ItemPage;
import page.LoginPage;
import page.MainPage;
import service.UserCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.testng.asserts.SoftAssert;

public class SalomonTests extends CommonConditions {

	@Test(priority = 1)
	public void logoutTest() {
		UserCreator userCreator = new UserCreator();
		User testUser = userCreator.withCredentialsFromProperty();

		String resultTitle = new LoginPage(driver)
				.openPage()
				.login(testUser)
				.logout()
		        .pressLoginButton()
		        .getTile();

		assertThat(resultTitle, is(equalTo("ВОЙДИТЕ ИЛИ ЗАРЕГИСТРИРУЙТЕСЬ")));
	}

	@Test(priority = 2)
	public void CardAddingTest() {
		SoftAssert softAssert = new SoftAssert();

		CardPage cardPage = new ItemPage(driver)
				.openPage("https://salomon.ru/catalog/muzhchiny/vidy_sporta/turizm_i_alpinizm/13058/")
				.addItemToCard()
				.goToCard();

		softAssert.assertEquals(cardPage.getCardItemTitle(), "OUTBLAST TS CSWP");
		softAssert.assertEquals(cardPage.getCardItemSize(), "Размер : 7 uk");
		softAssert.assertEquals(cardPage.getCardItemPrice(), "11 990 ₽");
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void SeacrhItemTest(){
		String resultItemTitle = new MainPage(driver)
				.openPage()
				.typeSearchRequest("TOUNDRA PRO CSWP W")
				.getItemTitle();

		assertThat(resultItemTitle, is(equalTo("TOUNDRA PRO CSWP W")));
	}


	@Test(priority = 4)
	public void incorrectEmailLoginTest(){
		UserCreator userCreator = new UserCreator();
		User testUser = userCreator.withIncorrectEmail();
		new LoginPage(driver)
				.openPage()
				.login(testUser);

		String ResultEmailFieldError =  new LoginPage(driver).getEmailFieldError();

		assertThat(ResultEmailFieldError, is(equalTo("Введите e-mail")));
	}

	@Test(priority = 5)
	public void emptyPasswordLoginTest() {
		UserCreator userCreator = new UserCreator();
		User testUser = userCreator.withEmptyPassword();
		new LoginPage(driver)
				.openPage()
				.login(testUser);

		String ResultPasswordFieldError =  new LoginPage(driver).getPasswordFieldError();

		assertThat(ResultPasswordFieldError, is(equalTo("Введите пароль")));
	}

	@Test(priority = 6)
	public void incorrentDataLoginTest(){
		UserCreator userCreator = new UserCreator();
		User testUser = userCreator.withIncorrectPassword();

		new LoginPage(driver)
				.openPage()
				.login(testUser);

		String ResultNotifyText = new LoginPage(driver).getNotifyText();
		assertThat(ResultNotifyText, is(equalTo("Неверный пароль")));
	}

	@Test(priority = 7)
	public void loginTest()  {
		UserCreator userCreator = new UserCreator();
		User testUser = userCreator.withCredentialsFromProperty();

		String UserEmail = new LoginPage(driver)
				.openPage()
				.login(testUser)
				.getUserEmail();

		assertThat(UserEmail, is(equalTo(testUser.getUsername())));
	}

	@Test(priority = 8)
	public void incorrectSeacrhTest() {
	 String ResultEmptyMessageText = new MainPage(driver)
				.openPage()
				.searchItem("non-existent item")
		        .getEmptyMessageText();

		assertThat(ResultEmptyMessageText, is(equalTo("Пожалуйста, проверьте правильность написания или попробуйте поискать по другому слову.")));
	}

	@Test(priority = 9)
	public void incorrectEmailMailingTest() {
		UserCreator userCreator = new UserCreator();
		User testUser = userCreator.withIncorrectEmail();

		String mailingFieldError = new MainPage(driver)
				.openPage()
				.sendEmailToMailingField(testUser)
				.getMailingFieldError();

		assertThat(mailingFieldError, is(equalTo("Введите e-mail")));
	}

	@Test(priority = 10)
	public void incorrectPromoCodeTest() {

		String notifyText = new ItemPage(driver)
				.openPage("https://salomon.ru/catalog/muzhchiny/vidy_sporta/turizm_i_alpinizm/13058/")
				.addItemToCard()
				.goToCard()
				.sendPromoCode("IncorrectPromoCode")
				.getNotifyText();

		assertThat(notifyText, is(equalTo("Неверный промокод")));
	}
}
