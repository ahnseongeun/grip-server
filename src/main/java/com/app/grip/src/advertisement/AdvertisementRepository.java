package com.app.grip.src.advertisement;

import com.app.grip.src.user.models.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface AdvertisementRepository extends CrudRepository<AdvertisementInfo, Long>  {
}
