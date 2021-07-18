package the.wuxjian.webflux.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class Student {
    @Id
    private Long id;

    private String remark;
    private boolean active;

    @ReadOnlyProperty
    private LocalDateTime createdAt;
    //	@CreatedBy
    private String createdBy;

    @ReadOnlyProperty
    private LocalDateTime updatedAt;
    private String updatedBy;


    private String code;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String address;
}
