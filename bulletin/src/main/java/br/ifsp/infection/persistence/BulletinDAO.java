package br.ifsp.infection.persistence;

import java.util.List;
import java.util.Optional;

public interface BulletinDAO<E,K>{
    K insert(E e);
    void delete(K k);
    void update(E e);
    Optional<E> existsById(K k);
    List<E> findAll();

}
