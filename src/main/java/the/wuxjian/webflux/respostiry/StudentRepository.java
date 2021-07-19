package the.wuxjian.webflux.respostiry;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import the.wuxjian.webflux.entity.Student;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    @Query("select * from student s where s.code = :code")
    Mono<Student> selectByCode(String code);

    Flux<Student> findByGender(String code, Pageable page);

    @Query("select * from student where gender = :gender")
    Flux<Student> selectByGender(String gender);
}
