package com.test.mzd.job.rent;

import com.zongs.commons.utils.DateUtil;
import com.zongs.trade.constants.SiginTypeEnum;
import com.zongs.trade.dto.MachineOrderPurchaseDetailDTO;
import com.zongs.trade.service.iface.MachineOrderService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Slf4j
public class SignInJob implements Job {
    @Autowired
    private MachineOrderService machineOrderService;

    /**
     * 凌晨1点执行
     *
     * @return
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            String today = DateUtil.format(DateUtil.getBeforeDayDate(new Date(), -1), DateUtil.NORMAL_DATE_PATTERN);
            log.info(" SignInJob start ");
            /**
             * 15天自动签收
             */
            //先查询15天前发货的采购单明细
            String datatime = DateUtil.format(DateUtil.getBeforeDayDate(new Date(), -15), DateUtil.NORMAL_DATE_PATTERN);
            List<MachineOrderPurchaseDetailDTO> list = machineOrderService.getAllNoSignIn(datatime);
            int i = 0;
            if (list != null && list.size() > 0) {
                i = list.size();
                for (MachineOrderPurchaseDetailDTO machineOrderPurchaseDetailDTO : list) {
                    //定时任务签收
                    machineOrderService.siginMachineOrderPurchaseDetail(new Date(), SiginTypeEnum.AUTO_SIGIN.getCode(), machineOrderPurchaseDetailDTO);
                }
            }
            log.info(today + "总共有" + i + "台机器自动签收");
            log.info(" SignInJob end ");
        } catch (Exception e) {
            log.error(" QuarterRentBillJob error :{}", e);
        }
    }
}
