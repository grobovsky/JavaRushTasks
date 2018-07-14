package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush.
Метод main реализован только для вас и не участвует в тестировании.

Требования:
1. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае, если список users пустой.
2. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае, если список users не пустой.
3. Класс Solution.JavaRush не должен поддерживать интерфейс Serializable.
4. Класс Solution.JavaRush должен быть публичным.
5. Класс Solution.JavaRush не должен поддерживать интерфейс Externalizable.
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("file", null, new File("D:\\Java_Rush\\project"));
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            User user1 = new User();
            user1.setFirstName("Ivan");
            user1.setLastName("Ivanov");
            user1.setBirthDate(new Date(89, 1, 10));
            user1.setCountry(User.Country.UKRAINE);
            user1.setMale(true);
            User user2 = new User();
            user2.setFirstName("Vasilisa");
            user2.setLastName("Vasilisova");
            user2.setBirthDate(new Date(90, 2, 11));
            user2.setCountry(User.Country.RUSSIA);
            user2.setMale(false);
            User user3 = new User();
            user3.setFirstName("Sidor");
            user3.setLastName("Sidorov");
            user3.setBirthDate(new Date(91, 3, 12));
            user3.setCountry(User.Country.OTHER);
            user3.setMale(true);
            javaRush.users.add(user1);
            javaRush.users.add(user2);
            javaRush.users.add(user3);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            if(javaRush.equals(loadedObject)){
                System.out.println("All right");
            } else {
                System.out.println("Oops, not its object");
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter out = new PrintWriter(outputStream);
            SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:SSS");
            for(User u : users) {
                out.println(u.getFirstName());
                out.println(u.getLastName());
                //String s = f.format();
                out.println(u.getBirthDate().getTime());
                out.println(u.isMale());
                out.println(u.getCountry());
            }
            out.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:SSS");
            while (in.ready()){
                User u = new User();
                u.setFirstName(in.readLine());
                u.setLastName(in.readLine());
                //Date d = f.parse(in.readLine());
                Date d = new Date();
                d.setTime(Long.parseLong(in.readLine()));
                u.setBirthDate(d);
                u.setMale(Boolean.valueOf(in.readLine()));
                u.setCountry(User.Country.valueOf(in.readLine()));
                users.add(u);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
