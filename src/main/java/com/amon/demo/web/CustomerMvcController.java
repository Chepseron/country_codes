package com.amon.demo.web;

import com.amon.demo.model.CustomerEntity;
import com.amon.demo.service.CustomerService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/")
public class CustomerMvcController {

    @Autowired
    CustomerService service;

    @RequestMapping(path = {"/", "/search"})
    public String getAllCustomers(Model model) {
        //declare a list of customer entity to hold all the customers
        List<CustomerEntity> list = service.getAllCustomers();
        //declare a hashmap to hold the key(code) and value(country and regex) so as to validate the numbers and render the country on the table
        HashMap<String, String> resultMap = new HashMap<>();
        //Declare a list to hold the validated numbers, country and code
        List<HoldingObject> listHolder = new ArrayList<>();
        //declare an object to hold the country,code,status,number, name and regex
        HoldingObject holder = new HoldingObject();
        //get the regex declared in the assignment to validate the numbers
        resultMap = ReadRegex();
        //loop through the customer list and validate the number to obtain the status and the country
        for (CustomerEntity ex : list) {
            holder = new HoldingObject();
            String code = ex.getPhone().substring(ex.getPhone().indexOf("(") + 1, ex.getPhone().indexOf(")"));
            String[] countryCode = resultMap.get(code).split("\\|");
            holder.setCountry(countryCode[0]);
            holder.setCode(Integer.parseInt(code));
            holder.setName(ex.getName());
            holder.setId(ex.getId());
            holder.setRegex(countryCode[1]);
            holder.setPhone(ex.getPhone());
            //match the regex and the phone number
            Pattern pattern = Pattern.compile(countryCode[1]);
            Matcher matcher = pattern.matcher(ex.getPhone());
            if (matcher.matches()) {
                holder.setStatus("valid");
            } else {
                holder.setStatus("Invalid");
            }
            //add all the object items to the holding list so as to render on the interface
            listHolder.add(holder);
        }
        //add attribute to the declared variable (customers) on the thymeleaf interface
        model.addAttribute("customers", listHolder);
        return "list-customers";
    }

    //a function to retrieve get the table of country and regex from the file regex.txt
    public HashMap<String, String> ReadRegex() {
        BufferedReader br = null;
        HashMap<String, String> map = new HashMap<>();
        try {
            br = new BufferedReader(new FileReader("regex.txt"));
            String line = "";
            //obtain the values from the read file
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(" | ");
                String country = parts[0];
                String code = parts[4].replace("+", "");
                String regex = StringUtils.substringAfter(line, " Regex = ");
                System.out.println("Country " + country + " code " + code + " Regex " + StringUtils.substringAfter(line, " Regex = "));
                map.put(code, country + "|" + regex);
            }
            return map;
        } catch (Exception ex) {
            Logger.getLogger(CustomerMvcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //a class to hold the customer details so as to parse it on the list
    public class HoldingObject {

        private Long id;
        private String name;
        private String phone;
        private String country;
        private String status;
        private String regex;
        private int code;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         * @param phone the phone to set
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         * @return the country
         */
        public String getCountry() {
            return country;
        }

        /**
         * @param country the country to set
         */
        public void setCountry(String country) {
            this.country = country;
        }

        /**
         * @return the id
         */
        public Long getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * @return the status
         */
        public String getStatus() {
            return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * @return the code
         */
        public int getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(int code) {
            this.code = code;
        }

        /**
         * @return the regex
         */
        public String getRegex() {
            return regex;
        }

        /**
         * @param regex the regex to set
         */
        public void setRegex(String regex) {
            this.regex = regex;
        }
    }
}
