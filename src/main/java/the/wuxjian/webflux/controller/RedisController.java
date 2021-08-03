package the.wuxjian.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by wuxjian 2021/8/3
 */
@RestController
@RequestMapping("/reids")
public class RedisController {

    @Autowired
    private ReactiveStringRedisTemplate template;

    @GetMapping("set")
    public Mono<String> set(String key, String value) {
        return template.opsForValue().set(key, value).map(flag -> {
            if (flag) {
                return "success";
            }
            return "fail";
        });
    }
}
