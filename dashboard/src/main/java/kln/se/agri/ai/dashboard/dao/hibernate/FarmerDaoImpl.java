package kln.se.agri.ai.dashboard.dao.hibernate;

import kln.se.agri.ai.commons.model.Farmer;
import kln.se.agri.ai.persistence.dao.FarmerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

@Repository("farmerDao")
@Transactional
public class FarmerDaoImpl extends CRUDDaoImpl<Farmer> implements FarmerDao {

    private final static Logger logger = LoggerFactory.getLogger(FarmerDaoImpl.class);

    @Override
    public Farmer findFarmerByIdNumber(String idNumber) {
        return (Farmer) entityManager.createNamedQuery(Farmer.GET_FARMER_BY_ID_NUMBER)
                .setParameter("idNumber", idNumber)
                .getSingleResult();
    }

    @Override
    public List<Farmer> getFarmerList() {
        return (List<Farmer>) entityManager.createNamedQuery(Farmer.GET_ALL_FARMERS)
                .getResultList();
    }

}
