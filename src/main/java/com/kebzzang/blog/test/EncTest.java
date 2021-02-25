package com.kebzzang.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
    @Test
    public void Hashing(){
        String encPassword=new BCryptPasswordEncoder().encode("kebzzang");
        System.out.println("kebzzang->"+encPassword);
    }
}
