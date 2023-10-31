package com.springboot.api.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "회원 정보", description = "이름, 이메일, 회사, 나이를 가진 Domain Class")
public class MemberDto {
    @ApiModelProperty(value = "이름")
    private String name;
    @ApiModelProperty(value = "이메일")
    private String email;
    @ApiModelProperty(value = "회사")
    private String organization;
    @ApiModelProperty(value = "나이")
    private Number age;

    public Number getAge() {
        return age;
    }

    public void setAge(Number age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString(){
        return "MemberDto{"+"name='"+name+'\''+", email='"+email+'\''+", organization='"+organization+'\''+", age='"+age+'\''+'}';
    }
}
