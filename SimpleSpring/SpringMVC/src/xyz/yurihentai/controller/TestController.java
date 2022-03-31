package xyz.yurihentai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(value = {"/", "/Yuri"}, params = {"username","age!=69"}, headers = {"Accept-Language=en-US,zh;q=0.8" }) //指定参数必须包含username，age，且age不能为69  指定header
    public String index(String username, Integer age) {
        System.out.println(username + age);
        return "success";
    }

    //ant风格通配符
    // ?:任意单个字符  *：任意一级路径映射  **：任意多级路径映射
    @ResponseBody
    @RequestMapping("/野兽/先辈/**")
    public String ant() {
        return "114514";
    }

    @ResponseBody   //返回为JSON数据，而不是视图
    @RequestMapping("/pathVariable/{value}")  //路径变量  占位符需要与注解中的参数名保持一致
    public String pathVariable(@PathVariable("value") String value) {
        return value;
    }

    /**
        矩阵变量(与路径变量绑定)  默认禁用，需要配置启用
        语法：/matrix/path;age=24;code=114,514
        多个值可以逗号分隔也可以写多次  如code=114;code=514
    */
    @RequestMapping("matrix/{path}")
    public String testMatrix(@MatrixVariable(value = "age", pathVar = "path", required = false) Integer age, // pathVar: 当不同路径变量上绑定的矩阵变量名称相同时，用于区别要从那个路径变量上获取矩阵变量的值
                             @MatrixVariable(value = "code", pathVar = "path" ,required = false) List<String> code,
                             @PathVariable("path") String path) {
        System.out.println(age);
        System.out.println(code);
        System.out.println(path);
        return "success";
    }

    @RequestMapping(value = "rest", method = RequestMethod.PUT)  //表单提交方式为POST 同时有一个参数”_method“值为对应的请求方式  且需要web.xml配置 org.springframework.web.filter.HiddenHttpMethodFilter
    public String rest(
            @RequestParam(value = "name", required = false, defaultValue = "114514")  //value=“name" 需要请求中传递过来的参数名为name
                    String param,
            @RequestHeader("Accept-Encoding") String encode,  //获取指定的请求头信息
            @CookieValue("JSESSIONID") String sessionId,    //获取cookie中的值
            HttpServletRequest request) {
        System.out.println(param); //对中文编码的处理需要tomcat配置-Dfile.encoding=utf-8 同时需要在web.xml配置编码解析器 org.springframework.web.filter.CharacterEncodingFilter
        System.out.println(encode);
        System.out.println(sessionId);
        return "redirect:/rest";  //mvx.xml 中配置  tomcat默认解析器 <mvc:default-servlet-handler/>  和 注解驱动<mvc:annotation-driven/>
    }

    //对JSON的处理  原理：Spring3.0新增的HttpMessageConverter
    @ResponseBody   //返回为JSON数据，而不是视图  或者返回ResponseEntity<T>
    @RequestMapping("json")
    public String testJson(@RequestBody Map<String, Object> param) {    // 通过请求体传递请求参数  获取传递过来的JSON数据   或者使用HttpEntity<T>
        return "OK";
    }

//    @RequestMapping("HttpEntity") //不可用
//    public ResponseEntity<Entity> HttpEntity(RequestEntity<Entity> param) {
//        ResponseEntity<Entity> result = new ResponseEntity<>(param.getBody(), HttpStatus.OK);
//        return result;
//    }

    /**
     * 需要配置Bean : org.springframework.web.multipart.commons.CommonsMultipartResolver(依赖jar包 commons-io 和 commons-fileupload)
     */
    @RequestMapping("uploadFile")
    public String testMultipartFileUpload(@RequestPart MultipartFile file, String desc) {  // 通过form-data传递请求参数
        System.out.println(desc);
        System.out.println(file.getOriginalFilename());
        try {
            System.out.println(file.getInputStream().available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

}