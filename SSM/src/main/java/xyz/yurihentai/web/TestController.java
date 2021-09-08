package xyz.yurihentai.web;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yurihentai.bean.用户;
import xyz.yurihentai.dao.用户DAO;

import java.util.List;

@RestController
//@RequestMapping("/test/")
public class TestController {

    private final 用户DAO 用户DAO;

    @Autowired
    public TestController(用户DAO 用户DAO) {
        this.用户DAO = 用户DAO;
    }

    //对象自动转换为json需要依赖jackson
    @GetMapping("test")
    public List<用户> test() {
        return 用户DAO.getAll();
    }

    @GetMapping("testPage")
    public PageInfo<用户> testPage(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
                                  @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return 用户DAO.pageAll(currentPage, pageSize);
    }

}
