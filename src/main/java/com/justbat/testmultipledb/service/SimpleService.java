package com.justbat.testmultipledb.service;

import com.justbat.testmultipledb.repository.SimpleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SimpleService {

    private final SimpleRepository simpleRepository;

    public String returnString() {
        return simpleRepository.returnString();
    }
}
