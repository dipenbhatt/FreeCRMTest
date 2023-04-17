package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtill;
	ContactsPage contactsPage;
	String SheetName="Contacts";
	
	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUP()
	{
		initialization();
		testUtill=new TestUtil();
		contactsPage=new ContactsPage();
		loginPage=new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtill.switchtoFrame();
		
    }
	
	@Test(priority=1)
	public void VerifyContactsPageLableTest()
	{
		contactsPage=homePage.clickOnContactsLink();
		Assert.assertTrue(contactsPage.verifyContactsLable(), "contacs lable is missing on the page");
		
	}
	@Test(priority=2)
	public void selectSingleContactsTest() {
		contactsPage=homePage.clickOnContactsLink();
		contactsPage.selectContacsByName("dipen bhatt");
		
	}
	@Test(priority=3)
	public void selectmultipleContactsTest() 
	{
		contactsPage=homePage.clickOnContactsLink();
		contactsPage.selectContacsByName("dipen bhatt");
		contactsPage.selectContacsByName("test1 test1");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() throws IOException
	{
		Object data[][]=TestUtil.getTestData(SheetName);
		return data;
		
	}
	
	@Test(priority=4,dataProvider = "getCRMTestData")
	public void CreateNewContactTest(String title,String firstname,String lastname,String company) 
	{
		
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "peter", "google");
		contactsPage.createNewContact(title, firstname, lastname, company);
	}

	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
}
