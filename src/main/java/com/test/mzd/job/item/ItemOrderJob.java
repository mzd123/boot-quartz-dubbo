package com.test.mzd.job.item;

import com.zongs.commons.utils.DateUtil;
import com.zongs.trade.dto.ItemOrderDTO;
import com.zongs.trade.service.iface.ItemOrderService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.Date;


@Slf4j
public class ItemOrderJob implements Job {

    @Resource
    private ItemOrderService itemOrderService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info(" ItemOrderJob start ");
            ItemOrderDTO itemOrderDTO = new ItemOrderDTO();
            Date date = new Date();
            itemOrderDTO.setGmtCreated(date);
            itemOrderDTO.setGmtModified(DateUtil.getBeforeDayDate(date, -15));
            itemOrderService.autoSignIn(itemOrderDTO);
            log.info(" ItemOrderJob end ");
        } catch (Exception e) {
            log.error(" ItemOrderJob error :{}", e);
        }
    }
}
