package com.test.mzd.controller;

import com.zongs.commons.exception.BusinessException;
import com.zongs.user.dto.UserDTO;
import com.zongs.user.service.iface.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private UserService userService;

    @RequestMapping("/test.do")
    public void doTest() {
        try {
            UserDTO userDTO = userService.getUserById(10006L);
            System.out.println(userDTO.toString());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
