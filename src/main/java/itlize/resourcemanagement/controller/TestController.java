package itlize.resourcemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/Hello")
    public String Hello(){
        return "Hi Jingxin";
    }
}
