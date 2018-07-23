package com.sbq.web.controller;


import com.sbq.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/devicewarning")
public class WarningController extends BaseController {

    @Autowired
    private IDeviceService deviceService;

    @RequestMapping("deviceWarning")
    public String manager() {

        return "devicewarning/deviceWarning";
    }

}
