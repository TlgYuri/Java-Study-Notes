package xyz.yurihentai.web;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.yurihentai.bean.dto.RequestDTO;
import xyz.yurihentai.util.RedisUtil;

@Api(tags = "swagger显示类的名称")
@RestController
public class RedisTestController {

    private final RedisUtil redisUtil;

    @Autowired
    public RedisTestController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @PostMapping("testSet")
    public Boolean testSet(@RequestParam String key, @RequestParam String value) {
        return redisUtil.set(key,value);
    }

    @GetMapping("testGet")
    public String testGet(@RequestParam String key) {
        return redisUtil.get(key);
    }

    @ApiOperation("这里写对接口方法的描述")
    @ApiImplicitParams({@ApiImplicitParam(value = "参数名", defaultValue = "默认值",
                                            required = false/*是否必传*/, allowMultiple = true/*允许多个(指数组或集合)*/,
                                            paramType = "query"/*参数传递方式：query(requestParam) header(请求头) path(PathVariable) body(请求体) form(from-data)*/),
                        @ApiImplicitParam(/*...*/)})
    @ApiResponses({@ApiResponse(message="响应信息", code = 404, response = Exception.class)}) //描述方法的响应，可以用来描述方法不同情况的报错信息等
    @GetMapping("/testSwagger")
    public String testSwagger(@RequestBody RequestDTO request) { //RequestDTO  实体类中也可以标注swagger描述，用于@RequestBody这种情况
        return request.getValue();
    }

}