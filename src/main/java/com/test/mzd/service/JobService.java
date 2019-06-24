package com.test.mzd.service;

import com.test.mzd.bean.QuartzBean;
import com.test.mzd.dao.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobDao jobDao;



    public List<QuartzBean> listQuartzBean(String name) {
        return jobDao.listQuartzBean(name);
    }
}