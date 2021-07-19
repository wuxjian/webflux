package the.wuxjian.webflux.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import the.wuxjian.webflux.entity.Student;
import the.wuxjian.webflux.helper.PageQueryBuilder;
import the.wuxjian.webflux.respostiry.StudentRepository;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private R2dbcEntityTemplate template;



    // list
    @GetMapping("/list")
    public Flux<Student> list() {
        return repository.findAll();
    }

    // save
    @PostMapping("/save")
    public Mono<Student> save(@RequestBody Student student) {
        return repository.save(student);
    }

    // 按id查询
    @GetMapping("/{id}")
    public Mono<Student> findById(@PathVariable Long id) {
        return repository.findById(id);
    }

    // 按id查询
    @GetMapping("/findByCode")
    public Mono<Student> findByCode(String code) {
        return repository.selectByCode(code);
    }

    // 按gender查询
    @GetMapping("/findByGender")
    public Flux<Student> selectByGender(String gender) {
        return repository.selectByGender(gender);
    }

    @GetMapping("/example")
    public Flux<Student> customer() {
        Student student = new Student();
        student.setGender("M");
        Example<Student> example = Example.of(student);
        return repository.findAll();
    }

    @GetMapping("/template")
    public Mono<Student> template() {
       return template.selectOne(query(where("code").is("S0001")), Student.class);
    }

    @GetMapping("/page")
    public Mono<Page<Student>> page() {
        PageRequest pageable = PageRequest.of(1, 1);
        return new PageQueryBuilder<>(template, Student.class)
                .where(where("gender").is("M"))
                .pageable(pageable)
                .apply();

    }

    // 先查出来再修改
    @PostMapping("/updateByCode")
    public Mono<Student> updateByCode(@RequestBody ModifyReq req) {
        return repository.selectByCode(req.getCode())
                .flatMap(s -> {
                    if (StringUtils.isEmpty(req.getAddress())) {
                        return Mono.error(new IllegalArgumentException("address 为空"));
                    }
                    s.setAddress(req.getAddress());
                    s.setRemark(req.getRemark());
                    return repository.save(s);
                })
                // 空的时候执行
                .defaultIfEmpty(new Student())
                .doOnError(e -> { // 出错的时间执行
                    throw new RuntimeException(e.getMessage());
                })
                ;
    }


    @Data
    static class ModifyReq {
        private String code;
        private String address;
        private String remark;
    }
}
