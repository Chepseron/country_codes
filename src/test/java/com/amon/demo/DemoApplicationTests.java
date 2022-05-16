package com.amon.demo;

import com.amon.demo.model.CustomerEntity;
import com.amon.demo.service.CustomerService;
import com.amon.demo.web.CustomerMvcController;
import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

    //test the findAll customers function
    @Test
    public void testFindAll() {
        List<CustomerEntity> foundCustomers = service.getAllCustomers();
        assertNotNull(foundCustomers);
        assertEquals(41, foundCustomers.size());
    }
    //test that read regex works and able to pick from the file regex.txt
    @Test
    public void testRegex() {
        HashMap<String, String> map = new HashMap<>();
        CustomerMvcController controller = new CustomerMvcController();
        map = controller.ReadRegex();
        assertNotNull(map);
        assertTrue(map.containsKey("256"));
    }
}
