package com.app.grip.src.store;

import com.app.grip.src.store.models.Store;
import com.app.grip.src.user.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
    List<Store> findAll(Sort sort);
    List<Store> findByStatusAndId(String status, Long id);
    List<Store> findByUser(User user);
}