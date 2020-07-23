package kln.se.agri.ai.user.dao;

import kln.se.agri.ai.commons.model.Crop;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

public interface CropDao extends CRUDDao<Crop> {

    Crop findCropByName(String name);

    List<Crop> getAllCrops();

    List<String> getAllCropNames();
}
