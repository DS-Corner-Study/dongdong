package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET) // http://localhost:8080/api/v1/get-api/hello
    public String getHello() {
        LOGGER.info("getHello 메서드 호출됨.");
        return "Hello World";
    }

    @GetMapping(value = "/name") // http://localhost:8080/api/v1/get-api/name
    public String getName(){
        LOGGER.info("getName 메서드 호출됨.");
        return "Flature";
    }

//    @GetMapping(value = "/variable1/{variable}") // http://localhost:8080/api/v1/get-api/variable1/{String 값}
//    public String getVariable(@PathVariable String variable){
//        return variable;
//    }

    @GetMapping(value = "/variable1/{variable}") // http://localhost:8080/api/v1/get-api/variable1/{String 값}
//    public String getVariable(@PathVariable("variable") String var){
    public String getVariable(@PathVariable(value = "variable") String var){
        LOGGER.info("@PathVariable을 통해 들어온 값: {}", var);
        return var;
    }

    @ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/request1") // http://localhost:8080/api/v1/get-api/request1?name=value1&email=value2&organization=value3
    public String getRequestParam1(
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회사", required = true) @RequestParam String organization) {
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
