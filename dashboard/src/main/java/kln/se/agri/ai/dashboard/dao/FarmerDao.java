package kln.se.agri.ai.dashboard.dao;


import kln.se.agri.ai.commons.model.Farmer;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

public interface FarmerDao extends CRUDDao<Farmer> {

    public Farmer findFarmerByIdNumber(String idNumber);

    List<Farmer> getFarmerList();
}
