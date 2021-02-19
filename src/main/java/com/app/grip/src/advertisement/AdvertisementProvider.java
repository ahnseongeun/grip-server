package com.app.grip.src.advertisement;

import com.app.grip.config.BaseException;
import com.app.grip.src.advertisement.models.Advertisement;
import com.app.grip.src.advertisement.models.GetAdvertisement;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.NOT_FOUND_ADVERTISEMENT;
import static com.app.grip.config.BaseResponseStatus.NOT_FOUND_USER;

@Service
public class AdvertisementProvider {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementProvider(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    /**
     * 광고 리스트 조회
     * @return
     * @throws BaseException
     */
    public List<GetAdvertisement> retrieveAdvertisementList() throws BaseException{

        Long directoryId = DateTime.now().getMinuteOfDay() % 2 == 0 ? 1L : 2L;

        List<Advertisement> advertisementList =
                advertisementRepository.findByDirectoryIdAndStatus(directoryId,"Y");

        if(advertisementList.size() == 0){
            throw new BaseException(NOT_FOUND_ADVERTISEMENT);
        }

        return advertisementList.stream()
                .map(advertisement -> GetAdvertisement.builder()
                        .advertisementId(advertisement.getId())
                        .description(advertisement.getDescription())
                        .pictureURL(advertisement.getPictureURL())
                        .build())
                .collect(Collectors.toList());
    }

}
