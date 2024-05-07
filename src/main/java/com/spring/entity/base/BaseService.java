package com.spring.entity.base;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.NoSuchElementException;

@MappedSuperclass
public class BaseService <T extends BaseEntity<ID>, ID extends Number>{

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    public T findById(ID id) {
        return baseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Entity Not Found With ID: "+ id));
    }

    public T getById(ID id) {
        return baseRepository.getById(id);
    }

    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public T save(T t) {
        if (t.getId() != null) {
            throw new RuntimeException();
        }
        return baseRepository.save(t);
    }

    public List<T> saveAll(List<T> authors) {
        return baseRepository.saveAll(authors);
    }

    public T update(T t) {

        return baseRepository.save(t);
    }

    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }
}
