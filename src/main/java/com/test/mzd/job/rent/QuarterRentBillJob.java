package com.test.mzd.job.rent;

import com.zongs.account.dto.RentBillDTO;
import com.zongs.account.service.iface.QuarterRentBillService;
import com.zongs.commons.base.model.BaseQTO;
import com.zongs.commons.constants.DeletedEnum;
import com.zongs.user.constants.UserStatusEnum;
import com.zongs.user.dto.UserDTO;
import com.zongs.user.service.iface.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
public class QuarterRentBillJob implements Job {

    @Resource
    private QuarterRentBillService quarterRentBillService;

    @Resource
    private UserService userService;

    private static final int START = 0;

    private static final int COUNT = 500;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info(" QuarterRentBillJob start ");

            BaseQTO<UserDTO> baseQTO = new BaseQTO<>();
            UserDTO userDTO = new UserDTO();
            userDTO.setDeleteMark(DeletedEnum.VALID.getCode());
            userDTO.setStatus(UserStatusEnum.NORMAL.getCode());
            baseQTO.setData(userDTO);

            int start = START;
            int count = COUNT;

            while (true) {
                baseQTO.setStart(start);
                baseQTO.setCount(count);

                List<Long> userIds = userService.getUserIds(baseQTO);

                // TODO
                for (Long userId : userIds) {
                    RentBillDTO rentBillDTO = new RentBillDTO();
                    rentBillDTO.setUserId(userId);
                    rentBillDTO.setQuarterBillTime(new Date());
                    quarterRentBillService.generateQuarterRentBillByUserId(rentBillDTO);
                }

                // 记录数小于分页量时终止
                if (userIds.size() < COUNT) {
                    break;
                }
                start = +COUNT;
            }

            log.info(" QuarterRentBillJob end ");
        } catch (Exception e) {
            log.error(" QuarterRentBillJob error :{}", e);
        }
    }
}
