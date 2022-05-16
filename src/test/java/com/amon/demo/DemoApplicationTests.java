package com.amon.demo;

import com.amon.demo.model.CustomerEntity;
import com.amon.demo.service.CustomerService;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    CustomerService service;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testFindAll_thenStudentListShouldBeReturned() {
        List<CustomerEntity> foundCustomers = service.getAllCustomers();
        assertNotNull(foundCustomers);
        assertEquals(41, foundCustomers.size());
    }
}
