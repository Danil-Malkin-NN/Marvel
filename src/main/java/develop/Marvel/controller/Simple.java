package develop.Marvel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class Simple {

    @RequestMapping(value = "/simple/",method = RequestMethod.GET)
    public String simple(Map<String, Object> model){
        System.out.println("11");
        return "successfully";
    }


}
