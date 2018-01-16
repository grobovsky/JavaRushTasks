package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Закрепляем адаптер

Адаптировать Customer и Contact к RowItem.
Классом-адаптером является DataAdapter.

Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:

UA Ukraine
RU Russia
CA Canada
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem{
        private Customer customer;
        private Contact contact;
        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String s = this.customer.getCountryName();
            for(Map.Entry<String, String> map : countries.entrySet()){
                if(map.getValue().equals(s)){
                    s = map.getKey();
                }
            }
            return s;
        }

        @Override
        public String getCompany() {
            return this.customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String s = this.contact.getName();
            String[] names = s.split(",");
            String firstName = names[1].trim();
            return firstName;
        }

        @Override
        public String getContactLastName() {
            String s = this.contact.getName();
            String[] names = s.split(",");
            String lastName = names[0].trim();
            return lastName;
        }

        @Override
        public String getDialString() {
            String number = this.contact.getPhoneNumber();
            Pattern p = Pattern.compile("[^0-9&&[^+]]");
            Matcher m = p.matcher(number);
            number = m.replaceAll("");
            return "callto://" + number;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}
