package cn.yurihentai.web;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.yurihentai.bean.用户;
import cn.yurihentai.service.用户服务;

import java.util.ArrayList;
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
//    @Secured({"ROLE_SIMPLE", "ROLE_NAIVE"}) // 只允许指定角色访问
    @PreAuthorize("hasRole('SIMPLE')")  // 方法执行前检查是否拥有角色SIMPLE  SpEL表达式
    public List<用户> test() {
        return 用户服务.获取所有用户();
    }

    @GetMapping("testPage")
//    principal.username == #username
    @PreAuthorize("hasRole('REFUSED')")
    @PostAuthorize("principal.username.equals(returnObject.pageNum)")  // 方法执行后判断用户名与方法返回值的xx是否相等  不相等将抛异常
    public PageInfo<用户> testPage(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
                                  @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return 用户服务.分页获取所有用户(currentPage, pageSize);
    }

    @GetMapping("ps")
    // 过滤方法的传参，只允许符合条件的值进入方法
    @PreFilter(value = "filterObject == null || ''.equals(filterObject.trim())", filterTarget = "usernameList")
    // 仅当方法返回集合数据时生效  过滤方法返回的结果，去除掉不符合条件的结果
    @PostFilter("filterObject == null || filterObject.username() == null ")  // 这里用中文会报错  蛋疼  TODO 有时间试试最新版本
    public List<用户> ps(List<String> usernameList) {
        List<用户> 用户List = new ArrayList<>();
        return 用户List;
    }

}
