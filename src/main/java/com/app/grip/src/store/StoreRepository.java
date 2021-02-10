package com.app.grip.src.store;

import com.app.grip.src.store.models.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
    List<Store> findByStatus(String status);
    List<Store> findByStatusAndId(String status, Long id);
}
