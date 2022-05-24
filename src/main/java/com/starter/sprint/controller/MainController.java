package com.starter.sprint.controller;

import com.starter.sprint.entity.Customer;
import com.starter.sprint.entity.QuoteItem;
import com.starter.sprint.service.Impl.PdfGenerateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
public class MainController {

    @Autowired
    PdfGenerateServiceImpl pdfGenerateService;

    @GetMapping("/")
    public HttpEntity ping() throws IOException {

        Map<String, Object> data = new HashMap<>();
        Customer customer = new Customer();
        customer.setCompanyName("Simple Solution");
        customer.setContactName("John Doe");
        customer.setAddress("123, Simple Street");
        customer.setEmail("john@simplesolution.dev");
        customer.setPhone("123 456 789");
        data.put("customer", customer);

        List<QuoteItem> quoteItems = new ArrayList<>();
        QuoteItem quoteItem1 = new QuoteItem();
        quoteItem1.setDescription("Test Quote Item 1");
        quoteItem1.setQuantity(1);
        quoteItem1.setUnitPrice(1000.0);
        quoteItem1.setTotal(100.0);
        quoteItems.add(quoteItem1);

        QuoteItem quoteItem2 = new QuoteItem();
        quoteItem2.setDescription("Test Quote Item 2");
        quoteItem2.setQuantity(4);
        quoteItem2.setUnitPrice(500.0);
        quoteItem2.setTotal(2000.0);
        quoteItems.add(quoteItem2);

        QuoteItem quoteItem3 = new QuoteItem();
        quoteItem3.setDescription("Test Quote Item 3");
        quoteItem3.setQuantity(2);
        quoteItem3.setUnitPrice(200.0);
        quoteItem3.setTotal(400.0);
        quoteItems.add(quoteItem3);

        data.put("quoteItems", quoteItems);

        File pdfFile = pdfGenerateService.generatePdfFile("quotation", data, "quotation"+ (Math.random()*100)/100 +".pdf");

        InputStream is = new FileInputStream(pdfFile.getAbsolutePath());
        String inputStream = is.toString();
        System.out.println("Input Stream String is: "+inputStream);
        byte[] byteArray = inputStream.getBytes();
        is.close();


        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_PDF);
//        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName());
//        header.setContentLength(byteArray.length);

        return new HttpEntity(pdfFile.getAbsolutePath(), header);
    }
}
