package com.dndadventure.services;

import com.dndadventure.domain.entities.User;
import com.dndadventure.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class CrudService<T> {

    public abstract CrudRepository<T, String> getRepository();

    public T save(T entity) {
        return this.getRepository().save(entity);
    }

    public Iterable<T> saveAll(Iterable<T> entities) {
        return this.getRepository().saveAll(entities);
    }

    public Optional<T> findById(String id) {
        return this.getRepository().findById(id);
    }

    public Iterable<T> findAll() {
        return this.getRepository().findAll();
    }

    public void delete(User activeUser, T entity) {
        this.getRepository().delete(entity);
    }

    public T update(String id, T newEntity) {
        T entity = this.getRepository().findById(id)
            .orElseThrow(NotFoundException::new);
        BeanUtils.copyProperties(newEntity, entity, "id");
        return this.getRepository().save(entity);
    }
}
