package kln.se.agri.ai.persistence.dao;

import kln.se.agri.ai.commons.model.ImageFile;

/**
 * Created by Prasad on 28/01/20.
 */

public interface ImageFileDao extends CRUDDao<ImageFile> {

    ImageFile getImageById(Long id);
}
