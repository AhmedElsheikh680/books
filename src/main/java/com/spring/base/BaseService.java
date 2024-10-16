package com.spring.base;


import com.spring.config.Translator;
import com.spring.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.Optional;

@MappedSuperclass
public class BaseService <T extends BaseEntity<ID>, ID extends Number>{

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    public T findById(ID id) {
//        return baseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Entity Not Found With ID: "+ id));

        Optional<T> entity =  baseRepository.findById(id);
        if (entity.isPresent()){
            return entity.get();
        } else {
//            throw new RecordNotFoundException("This Record With ID-> "+ id + " Not Found");
//            throw new RecordNotFoundException();
            throw new RecordNotFoundException(Translator.toLocale("RECORD_NOT_FOUND",id ));
        }
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
