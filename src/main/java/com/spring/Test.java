package com.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

//    private static long sequenceNumber=1;
    public static void main(String[] args) {
        long sequenceNumber=1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String dateTime = simpleDateFormat.format(new Date());
        String sequenceNum= String.format("%08d", sequenceNumber++);

        System.out.println(dateTime+ sequenceNum);

    }
}

