package xyz.yurihentai.springboot.web;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yurihentai.springboot.bean.用户;
import xyz.yurihentai.springboot.service.用户服务;

import java.util.List;

@RestController
public class TestController {

    private final 用户服务 用户服务;

    @Autowired
    public TestController(用户服务 用户服务) {
        this.用户服务 = 用户服务;
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @GetMapping("getAll")
    public List<用户> getAll() {
        return 用户服务.获取所有用户();
    }

    @GetMapping("pageAll")
    public PageInfo<用户> pageAll(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
                                @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return 用户服务.分页获取所有用户(currentPage, pageSize);
    }

}