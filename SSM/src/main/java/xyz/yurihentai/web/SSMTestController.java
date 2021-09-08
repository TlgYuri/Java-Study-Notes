package xyz.yurihentai.web;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yurihentai.bean.用户;
import xyz.yurihentai.service.用户服务;

import java.util.List;

@RestController
//@RequestMapping("/test/")
public class SSMTestController {

    private final 用户服务 用户服务;

    @Autowired
    public SSMTestController(用户服务 用户服务) {
        this.用户服务 = 用户服务;
    }

    //对象自动转换为json需要依赖jackson
    @GetMapping("test")
    public List<用户> test() {
        return 用户服务.getAll();
    }

    @GetMapping("testPage")
    public PageInfo<用户> testPage(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
                                  @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return 用户服务.pageAll(currentPage, pageSize);
    }

}
