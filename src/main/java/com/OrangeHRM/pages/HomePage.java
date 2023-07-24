package com.OrangeHRM.pages;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.com.testBase;




public class HomePage extends testBase{

	public HomePage() throws IOException {
		PageFactory.initElements(GetDriver(), this); // creates the findElement calls behind the scene. - PageFactory.initElements(driver, this);
	}
	//Put Elements Of The HomePage with PageFactory Object Technique
	
    @FindBy(xpath="//a[@href='/web/index.php/dashboard/index']")  //href="/web/index.php/dashboard/index"
    WebElement Dashboard;
	@FindBy(xpath="//a[@href='/web/index.php/recruitment/viewRecruitmentModule']")
	WebElement RecruitmentLink;

	@FindBy(linkText = "Vacancies")
	WebElement Vacancies; // vacancies tab
	
	
	//Put Methods Of The HomePage with PageFactory Object Technique
	public boolean DashboardisDisplayed()
	{
		GetDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js.executeScript("arguments[0].style.border='3px solid purple'", Dashboard);
		return Dashboard.isDisplayed();
	}
	public void HandlingKeyboardEvents() throws AWTException {
		js.executeScript("arguments[0].style.border='3px solid purple'", RecruitmentLink);

		String URLOfRecruit=RecruitmentLink.getAttribute("href");
		//Use robot class to press Ctrl+t keys

		Robot robot = new Robot();
		// Press Down ctrl+t

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		//Release ctrl +t

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
        //Switch focus to new tab
		ArrayList<String> tabs = new ArrayList<String> (GetDriver().getWindowHandles());
		GetDriver().switchTo().window(tabs.get(1));
        //Launch URL in the new tab
		GetDriver().get(URLOfRecruit);
        //Switch focus to new tab

		for (String tab : tabs) {
			System.out.println(GetDriver().getWindowHandle());

			 }
	}

	public void handlingMouseOver()
	{
		Actions action = new Actions(GetDriver());

		//Hover Over Recruitment Category
		action.moveToElement(RecruitmentLink)// Move To Vacancies Tab
				.click()
				.build()
				.perform();


		action.moveToElement(Vacancies)// Move To Vacancies Tab
				.click()
		        .build()
				.perform();
		GetDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

}
