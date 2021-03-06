/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.repository;


import com.mycompany.domain.CustomerInvoice;
import com.mycompany.repository.CustomerInvoiceRepository;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Bongani
 */
public class CustomerInvoiceRepositoryTest {
    
    public CustomerInvoiceRepositoryTest() {
    }

    public static ApplicationContext ctx;
    private Long id;
    public CustomerInvoiceRepository repo;
    
    
   
    @Test 
    public void createCustInvoice(){
        
        double invoice =233.435;
        repo = ctx.getBean(CustomerInvoiceRepository.class);
        CustomerInvoice c = new CustomerInvoice.Builder(invoice)
                            .invoiceStatus("open")
                            .build();
        repo.save(c);
        id = c.getId();
        Assert.assertNotNull(c);         
     }
    @Test(dependsOnMethods = "createCustInvoice")
     public void readCustInvoice(){
     repo=ctx.getBean(CustomerInvoiceRepository.class);
     CustomerInvoice c = repo.findOne(id);
     Assert.assertEquals(c.getInvoiceStatus(),"open");
     
    }
    @Test(dependsOnMethods = "readCustInvoice")
    public void updateCustInvoice(){
        double invoice =234.09;
        repo = ctx.getBean(CustomerInvoiceRepository.class);
        CustomerInvoice c = repo.findOne(id);
        CustomerInvoice updateCustomerInvoice= new CustomerInvoice.Builder(invoice) 
                                            .orderAmount(200.00)
                                            .invoiceStatus("close")
                                            .build();
        repo.save(updateCustomerInvoice);
        CustomerInvoice newCustomerInvoice= repo.findOne(id);
        Assert.assertEquals(newCustomerInvoice.getInvoiceStatus(),"close");
        
    }
    @Test(dependsOnMethods = "updateCustInvoice")
    public void deleteCustInvoice(){
        repo = ctx.getBean(CustomerInvoiceRepository.class);
        CustomerInvoice cust = repo.findOne(id);
        repo.delete(cust);
        
        CustomerInvoice deletedCusAddress = repo.findOne(id);
        Assert.assertNull(deletedCusAddress);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
