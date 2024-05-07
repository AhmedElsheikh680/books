package com.spring.entity.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<ID>, ID extends Number> extends JpaRepository<T, ID> {

    @Transactional
    @Modifying
    @Query("UPDATE #{#entityName} t SET t.statusCode= :statusCode WHERE t.id= :id")
    void updateEntity(ID id, String statusCode);
}
