package kln.se.agri.ai.persistence.dao.hibernate;

import kln.se.agri.ai.commons.model.ImageFile;
import kln.se.agri.ai.persistence.dao.ImageFileDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Prasad on 28/01/20.
 */

@Repository("imageFileDao")
@Transactional
public class ImageFileDaoImpl extends CRUDDaoImpl<ImageFile> implements ImageFileDao {

    @Override
    public ImageFile getImageById(Long id) {
        return (ImageFile) entityManager.createNamedQuery(ImageFile.GET_IMAGE_BY_ID)
                .setParameter("picUuid",id)
                .getSingleResult();
    }
}
