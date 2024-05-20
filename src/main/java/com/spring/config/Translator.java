package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {


    private static MessageSource messageSource;

    @Autowired
    public Translator(MessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String toLocale(String messageCode, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();

        String msg = messageSource.getMessage(messageCode, args, locale);

        return msg;
    }


}
