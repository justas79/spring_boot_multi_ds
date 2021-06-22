package com.justbat.testmultipledb.controller.v2;


import com.justbat.testmultipledb.service.SimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("v2Controller")
@RequestMapping("/v2")
public class TestController {

    private final SimpleService simpleService;

    public TestController(@Qualifier("simpleServicePostgre") SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @GetMapping("test")
    public String response() {
        return simpleService.returnString();
    }
}
