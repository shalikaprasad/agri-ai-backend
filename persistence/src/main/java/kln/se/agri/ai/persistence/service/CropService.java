package kln.se.agri.ai.persistence.service;


import kln.se.agri.ai.commons.model.Crop;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */
public interface CropService {

    /**
     * @see kln.se.agri.ai.persistence.dao.CropDao#create(Object)
     */
    Crop createCrop(Crop crop);

    /**
     * @see kln.se.agri.ai.persistence.dao.CropDao#update(Object)
     */
    Crop updateCrop(Crop crop);

    /**
     * @see kln.se.agri.ai.persistence.dao.CropDao#find(Long)
     */
    Crop findCrop(Long id);

    Crop getCropPredictionResult(Crop crop);

    Crop getBestCropResult(Crop crop);

    Crop getCropById(Long id);

    Crop getCropByName(String name);

    List<Crop> getAllCrops();

    List<String> getAllCropNames();

    void deleteCropById(Long id);

}

