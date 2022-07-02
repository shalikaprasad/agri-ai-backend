package kln.se.agri.ai.persistence.dao.hibernate;

import kln.se.agri.ai.commons.model.Crop;
import kln.se.agri.ai.persistence.dao.CropDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

@Repository("cropDao")
@Transactional
public class CropDaoImpl extends CRUDDaoImpl<Crop> implements CropDao {

    private final static Logger logger = LoggerFactory.getLogger(CropDaoImpl.class);

    @Override
    public Crop findCropByName(String cropName) {
        try {
            return (Crop) entityManager.createNamedQuery(Crop.GET_CROP_BY_NAME)
                    .setParameter("cropName", cropName)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Crop> getAllCrops() {
        try {
            return (List<Crop>) entityManager.createNamedQuery(Crop.GET_ALL_CROPS)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<String> getAllCropNames() {
        try {
            return (List<String>) entityManager.createNamedQuery(Crop.GET_ALL_CROP_NAME)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }
}
