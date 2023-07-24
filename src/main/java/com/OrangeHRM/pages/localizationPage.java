package com.OrangeHRM.pages;

import com.Base.com.testBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class localizationPage extends testBase{

	public localizationPage() throws IOException {
		PageFactory.initElements(GetDriver(), this); // creates the findElement calls behind the scene. - PageFactory.initElements(driver, this);
	}
	//Put Elements Of The HomePage with PageFactory Object Technique
	
    @FindBy(xpath="//button[@data-toggle='dropdown']")
    WebElement changeLanguage;
	@FindBy(linkText = "English")
	WebElement English;
	@FindBy(linkText = "中文简体")
	WebElement Chinese;
	@FindBy(linkText = "Deutsch")
	WebElement Deutsch;


	
	
	//Put Methods Of The HomePage with PageFactory Object Technique
	public String getPageTitleinEng()
	{

		String Title =GetDriver().getTitle();
		System.out.println(Title);
		return Title;
	}

	public String getPageTitleinDeutsch()
	{
		changeLanguage.click();
		Deutsch.click();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String Title =GetDriver().getTitle();
		System.out.println(Title);
		return Title;
	}


}
