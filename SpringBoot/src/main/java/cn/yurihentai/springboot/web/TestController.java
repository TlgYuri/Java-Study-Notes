package cn.yurihentai.springboot.web;

import cn.yurihentai.springboot.bean.MyPOJO;
import cn.yurihentai.springboot.bean.用户;
import cn.yurihentai.springboot.service.用户服务;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 请求映射原理:
 * SpringMVC的核心控制器：DispatcherServlet  入口方法 doDispatch(…)
 *  doDispatch 方法中的 mappedHandler = getHandler(processedRequest); 用来处理请求映射，查找当前请求将要交给哪一个控制器处理
 *  getHandler 方法所在的类中注入了一个装有 HandlerMapping 的List 使用增强for循环遍历这个List  调用HandlerMapping的getHandler方法 如果成功获取到可用的handler则返回
 *  最终找到 RequestMappingHandlerMapping 用于处理 所有自定义Controller的请求 和 错误转发请求(/error)
 *** 通过实现 HandlerMapping 接口  然后将自定义的 HandlerMapping 注入容器  可以实现一些定制化的请求映射处理
 * // REST请求方式 PUT、DELETE等: 核心在于HiddenHttpMethodFilter
 * @author Yuri
 */
@RestController
public class TestController {

    private final cn.yurihentai.springboot.service.用户服务 用户服务;

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

    /**
     * 参数解析过程:
     * DispatcherServlet 的 doDispatch 方法 通过 getHandler 方法找到找对应的Handler后，会调用 getHandlerAdapter 方法继续与找这个 Handler 对应的适配器
     *     getHandlerAdapter 方法内部使用增强for循环遍历当前类中存在的一个 名为 private List<HandlerAdapter> handlerAdapters 的List属性
     *     最终获取到 RequestMappingHandlerAdapter ，这个Handler用于处理使用 @RequestMapping 标注的方法
     *         调用 HandlerAdapter 的 handle 方法，向下调用到子类中的 handleInternal 方法，该方法中调用了 invokeHandlerMethod 方法，再进入方法中则调用了 invokeAndHandle 方法
     *             invokeAndHandle 方法中调用了 invokeForRequest 方法，该方法中调用了 InvocableHandlerMethod 类中的 getMethodArgumentValues 方法即最终执行获取参数逻辑的方法
     *                 getMethodArgumentValues 方法会调用该类中的属性 private HandlerMethodArgumentResolverComposite resolvers 的 support方法 判断当前是否存在支持对应参数映射类型处理的 HandlerMethodArgumentResolver
     *                 如果当前参数可以正常解析  将会继续执行  调用 resolvers.resolveArgument 方法，完成参数解析的步骤
     * POJO封装过程:
     *     对于@RequestBody标注的参数 会将前端传递的参数封装为POJO，此时使用的参数解析器是 RequestResponseBodyMethodProcessor.resolveArgument
     *         resolveArgument 方法中首先调用 readWithMessageConverters 遍历所有的 MessageConverters 获取支持的类型转换器，将字符串转换为目标参数的类型(HTTP协议传输，HTTP协议思想是万物皆文本，因此传递过来的数据都是字符串String)
     *         随后创建一个 WebDataBinder
     *
     *** 不同类型的参数处理会使用不同的参数解析器 如 RequestParamMethodArgumentResolver 用来处理使用@RequestParam 注解标注的参数
     * 可以通过向容器中注入 WebMvcConfigurer 并实现 addFormatters(FormatterRegistry registry) 方法，调用 registry.addConverter 添加自定义的 MessageConverter
     * ==============
     * 处理返回值:
     * DispatcherServlet 中调用 HandlerAdapter 的 handle 方法调用具体方法处理请求
     *     invokeAndHandle 方法在解析参数调用方法后  最终会拿到方法的返回值 调用 returnValueHandlers.handleReturnValue 方法 遍历 所有的HandlerMethodReturnValueHandler 使用 ReturnValueHandler 的 handleReturnValue 方法处理返回值
     *     handleReturnValue 方法中调用 writeWithMessageConverters 方法  遍历找到对应的 MessageConverter 将消息转换为JSON并返回
     * 如 RequestResponseBodyMethodProcessor.handleReturnValue 用来完成使用@ResponseBody 标注的方法的返回值处理
     * MappingJackson2HttpMessageConverter
     */
    @GetMapping("POJO")
    public String testPOJO(@RequestBody MyPOJO pojo) {
        return pojo!=null ? pojo.toString() : "param error!";
    }

}