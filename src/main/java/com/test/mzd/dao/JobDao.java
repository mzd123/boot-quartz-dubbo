package com.test.mzd.dao;

import com.test.mzd.bean.QuartzBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobDao {

    List<QuartzBean> listQuartzBean(String name);
}