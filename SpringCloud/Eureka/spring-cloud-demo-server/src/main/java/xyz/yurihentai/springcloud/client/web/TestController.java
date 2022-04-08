package xyz.yurihentai.springcloud.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.yurihentai.springcloud.client.service.TestService;

@RestController
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test/{target}")
    public String test(@PathVariable("target") String target) {
        if("first".equals(target)) {
            return testService.testFirst();
        } else if ("second".equals(target)) {
            return testService.testSecond();
        } else {
            return "error target";
        }
    }

    @GetMapping("/testFirst")
    public String testFirst() {
        return  testService.testFirst();
    }

    @GetMapping("/testSecond")
    public String testSecond() {
        return  testService.testSecond();
    }

}