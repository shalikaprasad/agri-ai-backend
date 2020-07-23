package kln.se.agri.ai.user.service.impl;

import kln.se.agri.ai.commons.model.Crop;
import kln.se.agri.ai.persistence.dao.CropDao;
import kln.se.agri.ai.persistence.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

@Service("cropService")
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDao cropDao;

    @Override
    @Transactional
    public Crop createCrop(Crop crop) {
        return cropDao.create(crop);
    }

    @Override
    public Crop updateCrop(Crop crop) {
        return cropDao.update(crop);
    }

    @Override
    public Crop findCrop(Long id) {
        return null;
    }

    @Override
    public Crop getCropPredictionResult(Crop crop) {
        return crop;
    }

    @Override
    public Crop getBestCropResult(Crop crop) {
        return crop;
    }

    @Override
    public Crop getCropById(Long id) {
        return cropDao.find(id);
    }

    @Override
    public Crop getCropByName(String name) {
        return cropDao.findCropByName(name);
    }

    @Override
    public List<Crop> getAllCrops() {
        return cropDao.getAllCrops();
    }

    @Override
    public List<String> getAllCropNames() {
        return cropDao.getAllCropNames();
    }

    @Override
    public void deleteCropById(Long id) {
        cropDao.delete(id);
    }


}
