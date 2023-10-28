package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET) // http://localhost:8080/api/v1/get-api/hello
    public String getHello() {
        return "Hello World";
    }

    @GetMapping(value = "/name") // http://localhost:8080/api/v1/get-api/name
    public String getName(){
        return "Flature";
    }

//    @GetMapping(value = "/variable1/{variable}") // http://localhost:8080/api/v1/get-api/variable1/{String 값}
//    public String getVariable(@PathVariable String variable){
//        return variable;
//    }

    @GetMapping(value = "/variable1/{variable}") // http://localhost:8080/api/v1/get-api/variable1/{String 값}
//    public String getVariable(@PathVariable("variable") String var){
    public String getVariable(@PathVariable(value = "variable") String var){
        return var;
    }

    @GetMapping(value = "/request1") // http://localhost:8080/api/v1/get-api/request1?name=value1&email=value2&organization=value3
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization) {
        return name+" "+email+" "+organization;
    }

    @GetMapping(value = "/request2") // http://localhost:8080/api/v1/get-api/request1?key1=value1&key2=value2
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb=new StringBuilder();
        param.entrySet().forEach(map -> {
            sb.append(map.getKey()+" : "+map.getValue()+"\n");
        });

        return sb.toString();
    }

    @GetMapping(value = "/request3") // http://localhost:8080/api/v1/get-api/request3?name=value1&email=value2&organization=value3
    public String getRequestParam3(MemberDto memberDto) {
//        return memberDto.getName()+" "+memberDto.getEmail()+" "+memberDto.getOrganization();
        return memberDto.toString();
    }
}
