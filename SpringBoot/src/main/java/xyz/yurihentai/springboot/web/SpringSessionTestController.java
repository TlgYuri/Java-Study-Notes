package xyz.yurihentai.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session/")
public class SpringSessionTestController {

    @GetMapping("/set")
    public String testSessionSet(HttpSession session, String key, String value) {
        session.setAttribute(key, value);
        return "ok";
    }

    @GetMapping("/get")
    public String testSessionGet(HttpSession session, String key) {
        return session.getAttribute(key).toString();
    }

}
