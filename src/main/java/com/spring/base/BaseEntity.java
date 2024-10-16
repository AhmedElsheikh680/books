package com.spring.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity <ID>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

//    @NotNull(message = "Should Be Enter Name")
//    @NotEmpty
//    @NotBlank
//    private String name;

    private String statusCode;

    @CreatedBy
    private String createdBy;

    @CreatedDate
//    private LocalDateTime createdDate;
    private Date createdDate;

    @LastModifiedBy
    private String lastModifiedBy;
    @LastModifiedDate
//    private LocalDateTime lastModifiedDate;
    private Date lastModifiedDate;


}
