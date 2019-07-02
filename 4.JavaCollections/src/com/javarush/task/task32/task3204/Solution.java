package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

/* 
Генератор паролей
Реализуй логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов.
2) только цифры и латинские буквы разного регистра.
3) обязательно должны присутствовать цифры, и буквы разного регистра.
Все сгенерированные пароли должны быть уникальные.

Пример правильного пароля:
wMh7smNu

Требования:
1. Класс Solution должен содержать метод getPassword(), который возвращает ByteArrayOutputStream со сгенерированным паролем.
2. Длина пароля должна составлять 8 символов.
3. Пароль должен содержать хотя бы одну цифру.
4. Пароль должен содержать хотя бы одну латинскую букву нижнего регистра.
5. Пароль должен содержать хотя бы одну латинскую букву верхнего регистра.
6. Пароль не должен содержать других символов, кроме цифр и латинских букв разного регистра.
7. Сгенерированные пароли должны быть уникальными.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        String upChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowChars = "abcdefghijklmnopqrstuvwxyz";
        String nubers = "0123456789";
        String dic = upChars+lowChars+nubers;
        StringBuilder pass = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        for(int i = 0; i < 5; i++){
            pass.append(dic.charAt(rnd.nextInt(dic.length())));
        }
        pass.insert(rnd.nextInt(pass.length()), upChars.charAt(rnd.nextInt(upChars.length())));
        pass.insert(rnd.nextInt(pass.length()), lowChars.charAt(rnd.nextInt(lowChars.length())));
        pass.insert(rnd.nextInt(pass.length()), nubers.charAt(rnd.nextInt(nubers.length())));
        byte[] b = pass.toString().getBytes();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(b);
        return out;
    }
}
