package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") //8080 기본 주소
    public String home(){
        return "home"; //home.html 호출
    }
}
