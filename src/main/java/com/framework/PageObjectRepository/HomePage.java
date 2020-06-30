package com.framework.PageObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.Resources.base;

public class HomePage extends base {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='h_sub_menu']")
	public WebElement expandTab;

	@FindBy(xpath = "//a[contains(text(),'WEATHER')]")
	public WebElement weatherTab;


	public void NavigateToWeatherTab() throws InterruptedException {
		expandTab.click();
		weatherTab.click();
		Thread.sleep(2000);
	}

	
	

}
