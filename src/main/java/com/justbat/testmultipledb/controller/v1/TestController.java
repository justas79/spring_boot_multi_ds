package com.justbat.testmultipledb.controller.v1;


import com.justbat.testmultipledb.service.SimpleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1controller")
@RequestMapping("/v1")
public class TestController {

    private SimpleService simpleService;

    public TestController(@Qualifier("simpleServiceMysql") SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @GetMapping("test")
    public String response() {
        return simpleService.returnString();
    }
}
