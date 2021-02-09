package com.app.grip.src.advertisement;

import com.app.grip.src.advertisement.models.Advertisement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Long>  {

    List<Advertisement> findByDirectoryIdAndStatus(Long directoryId,String status);
}
