package com.create.verify.receipt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import common.functions.Common;
import models.*;
import models.Hyperlink;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.*;
import reporting.GenerateReport;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.apache.http.entity.mime.MIME.UTF8_CHARSET;

public class readJsonData {

    private static final String excelPath = "C:\\Users\\prathameshu\\TalenticaProject\\RESTAPI\\TestData\\TestData.xlsx";
    protected static String newOrderRef = "";
    private static String currentTimeStamp = "";
    protected static String receiptDate = "";
    protected static String responseJSON = "";
    private static String oAuthToken = "";
    private static String payLoad = "";

    private static Gson gson = new Gson();
    public static CreateOrderResponse orderResponse;
    private static CloseableHttpClient client;
    ObjectMapper obj;
    HashMap<String, String> map;
    protected static Order orderPayload;
    verifyReceiptData verifyReceiptData=new verifyReceiptData();
    Common common=new Common();
    GenerateReport generateReport=new GenerateReport();

    //Setting value to the variables
    private static void dataToBeVerified() {
        Date date = new Date();
        newOrderRef = "TEST-" + date.getTime() + "-ONLINE";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        currentTimeStamp = dateFormat.format(date);

        DateFormat uiDateFormat = new SimpleDateFormat("MMMM dd',' yyyy' - 'HH:mm a", Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -9);
        cal.add(Calendar.MINUTE, 30);
        receiptDate = uiDateFormat.format(cal.getTime());
    }

    //Generating bearer token
    private void generateAuthHeader(){
        try {
            HttpPost httpPost = new HttpPost("OAuth URL");
            httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
            httpPost.addHeader("authorization", "Basic ClientKeyForAuthtoken");
            httpPost.addHeader("accept-encoding", "gzip, deflate");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("test", "test"));
            nvps.add(new BasicNameValuePair("test", "test"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(httpPost);
            oAuthToken = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);
            obj = new ObjectMapper();
            map = obj.readValue(oAuthToken, HashMap.class);
        }catch (Exception e){
                e.printStackTrace();
        }
    }

    //Reading payload from excel and executing create order API
    private void getDataAndExecuteApi() {
        try {
            generateReport.startReport();
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    dataToBeVerified();
                    generateAuthHeader();
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    String newCellValue = cellValue.replaceAll("ReplaceOrderRef", newOrderRef);
                    payLoad = newCellValue.replaceAll("ReplaceDate", currentTimeStamp);
                    payLoad = payLoad.replaceAll("\"\"", "\"");
                    orderPayload = gson.fromJson(payLoad, CreateOrderRequest.class).getOrder();
                    client = HttpClients.createDefault();
                    HttpPost httpPost = new HttpPost("Create Order URL");
                    StringEntity entity = new StringEntity(payLoad);
                    httpPost.setEntity(entity);
                    httpPost.setHeader("Accept", "application/json");
                    httpPost.setHeader("Content-type", "application/json");
                    httpPost.setHeader("Retailer-Ref", "OM_USA");
                    httpPost.setHeader("Authorization", "Bearer " + map.get("access_token"));
                    CloseableHttpResponse response = client.execute(httpPost);
                    responseJSON = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);
                    //System.out.println(responseJSON);
                    workbook.close();
                    orderResponse = gson.fromJson(responseJSON, CreateOrderResponse.class);
                    Order order = orderResponse.getData();
                    verifyOrder(order);
                    common.openBrowser();
                    verifyReceiptData.verifyUI();
                    common.closeBrowser();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Verifying create order response
    private void verifyOrder(Order order) {
        verifyOrderData(order);
        if (order.getOrderItems() != null) {
            for (int i = 0; i < order.getOrderItems().size(); i++) {
                verifyOrderItem(order.getOrderItems().get(i), i);
            }
        }
    }

    private void verifyOrderData(Order order) {
        if (order.getOrderRef().equalsIgnoreCase(orderPayload.getOrderRef().toString())) {
            System.out.println("Expected OrderRef is : " + orderPayload.getOrderRef() + ", Actual OrderRef is : " + orderPayload.getOrderRef());
        } else {
            System.out.println("OrderRef not matched");
        }

        if (order.getOrderNumber().equalsIgnoreCase(newOrderRef)) {
            System.out.println("Expected OrderNumber is : " + newOrderRef + ", Actual OrderNumber is : " + order.getOrderNumber());
        } else {
            System.out.println("OrderNumber not matched");
        }
        if(order.getOrderDate().equals(orderPayload.getOrderDate()))
            System.out.println("order Date matched");

        verifyOrderTotal(order.getTotal());
        verifyOrderSubTotal(order.getSubTotal());
     }

    private void verifyOrderTotal(Amount amount){
        if((orderPayload.getTotal().getQuantity()).equals(amount.getQuantity()) && (orderPayload.getTotal().getCurrency().getCurrencyCode()).equals(amount.getCurrency().getCurrencyCode()))
            System.out.println("Order total matched");
        else
            System.out.println("Order total not matched");
    }

    private void verifyOrderSubTotal(Amount amount){
        if((orderPayload.getSubTotal().getQuantity()).equals(amount.getQuantity()) && (orderPayload.getSubTotal().getCurrency().getCurrencyCode()).equals(amount.getCurrency().getCurrencyCode()))
            System.out.println("Order Subtotal matched");
        else
            System.out.println("Order Subtotal not matched");
    }

    private void verifyOrderItem(OrderItem orderItem, int index) {
        verifyProduct(orderItem.getProduct(), index);
        //verifyListAmount(orderItem.getListAmount(),index);
        //verifyUnitAmount(orderItem.getUnitAmount(),index);
        //verifyOrderItemTotal(orderItem.getTotal(),index);
    }

    private void verifyProduct(Product product, int index) {
        verifyUrls(product.getImageUrl(), product.getLogoUrl(), product.getUrl(), index);
        verifyItemInfo(product.getName(), product.getDescription(), product.getModel(), index);
    }

    private void verifyOrderItemTotal(Amount quantity, int index){
        if((orderPayload.getOrderItems().get(index).getTotal().getQuantity()).equals(quantity))
            System.out.println("OrderItem total matched");
        else
            System.out.println("OrderItem total not matched");
    }

    private void verifyListAmount(Amount quantity, int index){
        if((orderPayload.getOrderItems().get(index).getListAmount().getQuantity()).equals(quantity))
            System.out.println("ListAmount matched");
        else
            System.out.println("ListAmount not matched");
    }

    private void verifyUnitAmount(Amount quantity, int index){
        if((orderPayload.getOrderItems().get(index).getUnitAmount().getQuantity()).equals(quantity))
            System.out.println("UnitAmount matched");
        else
            System.out.println("UnitAmount not matched");
    }

    private void verifyItemInfo(String prodName, String prodDesc, String prodModel, int index){
        if ((orderPayload.getOrderItems().get(index).getProduct().getName()).equals(prodName))
            System.out.println("Product Name matched");
        else
            System.out.println("Product Name not matched");
        if ((orderPayload.getOrderItems().get(index).getProduct().getDescription()).equals(prodDesc))
            System.out.println("Product Desc matched");
        else
            System.out.println("Product Desc not matched");
        if ((orderPayload.getOrderItems().get(index).getProduct().getModel()).equals(prodModel))
            System.out.println("Product Model matched");
        else
            System.out.println("Product Model not matched");
    }

    private void verifyUrls(Hyperlink imageUrl, Hyperlink logoUrl, Hyperlink url, int index) {
        if ((orderPayload.getOrderItems().get(index).getProduct().getImageUrl().getHref()).equals(imageUrl.getHref()))
            System.out.println("URL matched");
        else
            System.out.println("URL not matched");

        if ((orderPayload.getOrderItems().get(index).getProduct().getLogoUrl().getHref()).equals(logoUrl.getHref()))
            System.out.println("logoUrl matched");
        else
            System.out.println("logoUrl not matched");

        if ((orderPayload.getOrderItems().get(index).getProduct().getUrl().getHref()).equals(url.getHref()))
            System.out.println("Url matched");
        else
            System.out.println("Url not matched");
    }

    public void executeReadJSONData(){
     getDataAndExecuteApi();
    }
}

