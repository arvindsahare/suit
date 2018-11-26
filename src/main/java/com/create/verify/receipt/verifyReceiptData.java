package com.create.verify.receipt;

import common.functions.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import read.xpath.properties.ReadProperty;
import reporting.GenerateReport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class verifyReceiptData extends Common {
    String locator="";

    ReadProperty readProperty = new ReadProperty();
    GenerateReport report=new GenerateReport();

    private String setLocator(String locatorValue){
        String xPath=readProperty.getValueOfKey(locatorValue);
        return xPath;
    }

    private void clickDetailedReceipt() {
        boolean flag;
        locator=setLocator("detailedReceiptButton");
        By buttonLocator = By.xpath(locator);
        waitTillElementVisible(buttonLocator);
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        flag=elements.get(elements.size()-1).isDisplayed();
            if(flag) {
                driver.findElements(By.xpath(locator)).get(elements.size() - 1).click();
                //report.passTest("Click Detailed Receipt Button");
                report.passTest("Click Detailed Receipt");
                switchToiFrame();
            }else{
                report.failTest("Click Detailed Receipt");
            }
    }

    private void verifyItemName(){
        locator=setLocator("productName");
        boolean flag=verifyElementListExistIniFrame(locator);
        if(flag) {
            List<WebElement> elements = returnElementListInIFrame(locator);
            for (int i = 0; i < elements.size(); i++) {
                if ((elements.get(i).getText()).equalsIgnoreCase(readJsonData.orderPayload.getOrderItems().get(i).getProduct().getName())) {
                    report.passTest("Verify list of orderItems Name : " + elements.get(i).getText());
                }
            }
        }else{
            report.failTest("Verify list of orderItems Name");
        }
    }

    private void verifyItemDesc(){
        locator=setLocator("productDesc");
        boolean flag=verifyElementListExistIniFrame(locator);
        if(flag) {
            List<WebElement> elements = returnElementListInIFrame(locator);
            for (int i = 0; i < elements.size(); i++) {
                if ((elements.get(i).getText()).equalsIgnoreCase(readJsonData.orderPayload.getOrderItems().get(i).getProduct().getDescription())) {
                    report.passTest("Verify list of orderItems Description : " + elements.get(i).getText());
                }
            }
        }else{
            report.failTest("Verify list of orderItems Description");
        }
    }

    private void verifyKeyValuePair(){
        locator=setLocator("productKeyValue");
        boolean flag=verifyElementListExistIniFrame(locator);
        if(flag) {
            List<WebElement> elements = receipt.findElements(By.xpath(locator));
            for (int i = 0; i < elements.size(); i++) {
                String dynamicLocator=setLocator("productKeyValueDynamic");
                dynamicLocator=dynamicLocator.replace("productName",readJsonData.orderPayload.getOrderItems().get(i).getProduct().getName());
                String actualKeyValue=verifyGetText(dynamicLocator);
                String keyValue=readJsonData.orderPayload.getOrderItems().get(i).getOrderItemMetaData().get(0).getKey()+" "+readJsonData.orderPayload.getOrderItems().get(i).getOrderItemMetaData().get(0).getValue();
                if ((actualKeyValue).equalsIgnoreCase(keyValue)) {
                    report.passTest("Verify orderItems KeyValue pair : " + actualKeyValue);
                }
            }
        }else{
            report.failTest("Verify orderItems KeyValue pair");
        }
    }

    private void verifyOrderNumber(){
        scrollDown();
        locator = setLocator("orderNumber");
        boolean flag = verifyExistIniFrame(locator);
        if (flag) {
            String actualOrderNumber = verifyGetText(locator);
            if(actualOrderNumber.equalsIgnoreCase(readJsonData.newOrderRef))
                report.passTest("Verify orderNumber : " + actualOrderNumber);
            else
                report.failTest("Verify orderNumber");
        }else{
            report.failTest("Verify orderNumber");
        }
    }

    private void verifyPartnerLogo(){
        locator=setLocator("partnerLogo");
        boolean flag=verifyExistIniFrame(locator);
        if(flag) {
            String logoUrl=receipt.findElement(By.className("partner-logo")).getAttribute("src");
            if(logoUrl.equalsIgnoreCase("https://i.1mrkt.net/partner/web_receipt_om_usa2")) {
                report.passTest("Verify partnerLogo : " + logoUrl);
            }else{
                report.failTest("Verify partnerLogo");
            }
        }else{
            report.failTest("Verify partnerLogo");
        }
    }

    private void verifyProductImage(){
        locator=setLocator("productImage");
        boolean flag=verifyElementListExistIniFrame(locator);
        if(flag){
            List<WebElement> elements = receipt.findElements(By.xpath(locator));
            for (int i = 0; i < elements.size(); i++) {
                String dynamicLocator=setLocator("productImageDynamic");
                dynamicLocator=dynamicLocator.replace("productName",readJsonData.orderPayload.getOrderItems().get(i).getProduct().getName());
                String actualImageURL=receipt.findElement(By.xpath(dynamicLocator)).getAttribute("src");
                if ((actualImageURL).equalsIgnoreCase(readJsonData.orderPayload.getOrderItems().get(0).getProduct().getImageUrl().getHref())) {
                    report.passTest("Verify Image URL : " + actualImageURL);
                }else{
                    report.failTest("Verify Image URL");
                }
            }
        }else{
            report.failTest("Verify Image URL");
        }
    }

    private void verifyReceiptDate() {
        locator = setLocator("receiptDate");
        boolean flag = verifyExistIniFrame(locator);
        if (flag) {
            try {
                String actualUIDate = verifyGetText(locator);
                DateFormat uiDateFormat = new SimpleDateFormat("MMMM dd',' yyyy' - 'HH:mm a", Locale.US);
                Date actualDate = uiDateFormat.parse(actualUIDate);
                Date expectedDate = uiDateFormat.parse(readJsonData.receiptDate);
                if(actualDate.equals(expectedDate))
                    report.passTest("Verify Receipt Date : " + actualDate);
                else
                    report.failTest("Verify Receipt Date");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            report.failTest("Verify Receipt Date");
        }
    }

    private void verifyItemModel(){
        locator=setLocator("productModel");
        boolean flag=verifyElementListExistIniFrame(locator);
        if(flag) {
            List<WebElement> elements = returnElementListInIFrame(locator);
            for (int i = 0; i < elements.size(); i++) {
                if ((elements.get(i).getText()).equalsIgnoreCase(readJsonData.orderPayload.getOrderItems().get(i).getProduct().getModel())) {
                    report.passTest("Verify orderItems Model : " + elements.get(i).getText());
                }
            }
        }else{
            report.failTest("Verify orderItems Model");
        }
    }

    private void verifyOrderSubTotal(){
        boolean flag;
        String actualOrderSubTotal;
        locator=setLocator("orderSubTotal");
        flag=verifyElementListExistIniFrame(locator);
        if(flag){
        actualOrderSubTotal=verifyGetText(locator);
            actualOrderSubTotal=actualOrderSubTotal.replace("$","");
            if(actualOrderSubTotal.equalsIgnoreCase(readJsonData.orderPayload.getSubTotal().getQuantity().toString())){
                report.passTest("Verify Order SubTotal : " + actualOrderSubTotal);
            }else{
                report.failTest("Verify Order SubTotal");
            }
        }
    }

    private void verifyOrderTotal(){
        boolean flag;
        String actualOrderTotal;
        locator=setLocator("orderTotal");
        flag=verifyElementListExistIniFrame(locator);
        if(flag){
            actualOrderTotal=verifyGetText(locator);
            actualOrderTotal=actualOrderTotal.replace("$","");
            if(actualOrderTotal.equalsIgnoreCase(readJsonData.orderPayload.getTotal().getQuantity().toString())){
                report.passTest("Verify Order Total : " + actualOrderTotal);
            }else{
                report.failTest("Verify Order Total");
            }
        }
    }

    public void verifyUI(){
        clickDetailedReceipt();
        verifyProductImage();
        verifyItemName();
        verifyItemDesc();
        verifyPartnerLogo();
        verifyItemModel();
        verifyReceiptDate();
        verifyKeyValuePair();
        verifyOrderNumber();
        verifyOrderSubTotal();
        verifyOrderTotal();
    }
}
