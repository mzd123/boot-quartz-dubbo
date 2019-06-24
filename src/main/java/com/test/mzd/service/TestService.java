package com.test.mzd.service;

import com.test.mzd.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestService {
    @Autowired
    private TestDao testDao;

    public void updategoods() {
        System.out.println("aa");
    }
}
