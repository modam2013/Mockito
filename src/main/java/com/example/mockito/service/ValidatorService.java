package com.example.mockito.service;

import com.example.mockito.exception.IncorrectNameException;
import com.example.mockito.exception.IncorrectSurnameException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidatorService {
    public String validateName(String name) {
        if (!StringUtils.isEmpty(name)) {
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

    public String validateSurname(String surname) {
        String[] surnames = surname.split("-");
        for (int i = 0; i < surnames.length; i++) {
            if (!StringUtils.isEmpty(surnames[i])) {
                throw new IncorrectSurnameException();
            }
            surnames[i] = StringUtils.capitalize(surnames[i].toLowerCase());
        }
        return String.join("-", surnames);
    }

}
