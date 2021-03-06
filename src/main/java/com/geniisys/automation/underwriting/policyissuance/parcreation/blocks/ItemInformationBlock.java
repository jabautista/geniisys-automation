package com.geniisys.automation.underwriting.policyissuance.parcreation.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.MessageOverlay;

public class ItemInformationBlock {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(ItemInformationBlock.class.getName());

	private final By itemTitleFldLocator = By.xpath("//input[@id='itemTitle']");
	private final By addItemBtnLocator = By.xpath("//input[@id='btnAddItem']");
	private final By regionFldLocator = By.xpath("//select[@id='region']");

	public ItemInformationBlock(BrowserDriver driver) {
		this.driver = driver;
	}

	public void setItemTitle(String itemTitle) {
		try {
			driver.findClickableElement(itemTitleFldLocator).click();
			driver.findElement(itemTitleFldLocator).sendKeys(itemTitle);
			LOGGER.info("Item Title field value set to '" + itemTitle + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}

	public void addItem() {
		try {
			driver.findClickableElement(addItemBtnLocator).click();
			LOGGER.info("'Add' button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		getMessageOvl().clickOk();
	}

	public void selectItem(Integer itemNo) {
		try {
			driver.findClickableElement(
					By.xpath("//div[starts-with(@class,'mtgInnerCell')"
							+ " and contains(text(), '"
							+ itemNo
							+ "')]")).click();
			LOGGER.info("Item Number " + itemNo + " selected in the table grid.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}

	public void setRegion(String regionName) {
		try {
			driver.findElement(regionFldLocator).click();
			Select region = new Select(driver.findElement(regionFldLocator));
			region.selectByVisibleText(regionName);
			LOGGER.info("Region with name '" + regionName + "' selected in the combo box.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}

	private MessageOverlay getMessageOvl() {
		return new MessageOverlay(driver);
	}
}
