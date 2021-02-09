package com.app.grip.src.follower;

import com.app.grip.src.follower.models.Follower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends CrudRepository<Follower, Long> {
}
