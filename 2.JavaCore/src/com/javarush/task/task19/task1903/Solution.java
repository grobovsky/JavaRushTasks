package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact{
        private IncomeData data;
        IncomeDataAdapter(IncomeData data){
            this.data = data;
        }

        @Override
        public String getCompanyName() {
            return this.data.getCompany();
        }

        @Override
        public String getCountryName() {
            String s = this.data.getCountryCode();
            for(Map.Entry<String, String> map : countries.entrySet()){
                if(s.equals(map.getKey())){
                    s = map.getValue();
                }
            }
            return s;
        }

        @Override
        public String getName() {
            String name = this.data.getContactLastName() + ", " + this.data.getContactFirstName();
            return name;
        }

        @Override
        public String getPhoneNumber() {
            String codeNum = String.valueOf(this.data.getCountryPhoneCode());
            String phoneNumber = String.valueOf(this.data.getPhoneNumber());
            StringBuilder build = new StringBuilder(phoneNumber);
            while (build.length() < 10){
                build.insert(0,"0");
            }
            phoneNumber = new String(build);
            //string.format не пропустил валидатор
            /*String res = String.format("+%s(%s)%s-%s-$s",
                    codeNum,
                    phoneNumber.substring(0, 3),
                    phoneNumber.substring(3, 6),
                    phoneNumber.substring(6, 8),
                    phoneNumber.substring(8, 10));*/

            build = new StringBuilder();
            build.append("+")
              .append(codeNum).append("(")
              .append(phoneNumber.substring(0, 3))
              .append(")").append(phoneNumber.substring(3, 6)).append("-")
              .append(phoneNumber.substring(6, 8))
              .append("-")
              .append(phoneNumber.substring(8, 10));
            String res = new String(build);
            return res;   //example +38(050)123-45-67
        }
    }


    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
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
