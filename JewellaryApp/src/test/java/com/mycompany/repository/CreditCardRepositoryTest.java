/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.repository;

//import com.mycompany.conf.ConnectionConfig;
import com.mycompany.domain.CreditCard;
import com.mycompany.repository.CreditCardRepository;
import java.math.BigDecimal;
import java.util.Date;
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
public class CreditCardRepositoryTest {
    
    public CreditCardRepositoryTest() {
    }
    
    public static ApplicationContext ctx;
    private Long id;
    private CreditCardRepository repo;

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
   @Test
   public void createCreditCard(){
       repo = ctx.getBean(CreditCardRepository.class);
        Date Date = null;
       CreditCard c = new CreditCard.Builder(12382)
                          .balance(BigDecimal.ZERO)
                          .expiryDate(Date)
                          .owner("clifford")
                          .build();
       repo.save(c);
       id = c.getId();
       Assert.assertNotNull(c);
   }
   
   @Test(dependsOnMethods = "createCreditCard")
   public void readCreditCard(){
       repo = ctx.getBean(CreditCardRepository.class);
       CreditCard c = repo.findOne(id);
       Assert.assertEquals(c.getCreditNumber(),12382);
   }
   @Test(dependsOnMethods = "readCreditCard")
   public void updateCreditCard(){
       repo = ctx.getBean(CreditCardRepository.class);
       CreditCard c = repo.findOne(id);
       CreditCard updateCreditCard = new CreditCard.Builder(12382)
                                     // .creditCard(c)
                                      .owner("bongani")
                                      .balance(BigDecimal.ZERO)
                                      .build();
       
       repo.save(updateCreditCard);
       CreditCard newCreditCard = repo.findOne(id);
       Assert.assertEquals(newCreditCard.getBalance(), "400");
   }
   
   @Test(dependsOnMethods = "updateCreditCard")
   public void deleteCreditCard(){
       repo = ctx.getBean(CreditCardRepository.class);
       CreditCard c = repo.findOne(id);
       repo.delete(c);
       CreditCard deleteCreditCard = repo.findOne(id);
       Assert.assertNull(deleteCreditCard);
   }
       
   

    @BeforeClass
    public static void setUpClass() throws Exception {
      //  ctx = new AnnotationConfigApplicationContext(ConnectionConfig.class);
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
