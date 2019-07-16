package com.bdqn.service.impl;

import org.apache.commons.codec.digest.DigestUtils;

public class test {
    public static void main(String[] args) {
        String password = DigestUtils.md5Hex("1234");
        System.out.println(password);
    }
}
