package kln.se.agri.ai.persistence.exceptions;

import java.util.List;

/**
 * Created by Prasad on 12/15/19.
 */

public class ResourceNotFoundException extends BaseException {

    /**
     * resource ID
     */
    private Long resourceId;

    /**
     * resource String code
     */
    private String resourceCode;

    private List<Long> errorIds;
    private String successIds;
    private String errorActIds;

    public ResourceNotFoundException(Long resourceId, String errorCode, String message) {
        super(errorCode, message);
        this.resourceId = resourceId;
    }

    public ResourceNotFoundException(String resourceCode, String errorCode, String message) {
        super(errorCode, message);
        this.resourceCode = resourceCode;
    }

    public ResourceNotFoundException(String errorCode, String message, List<Long> errorIds) {
        super(errorCode, message);
        this.resourceCode = resourceCode;
        this.errorIds = errorIds;
    }

    public ResourceNotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public List<Long> getErrorIds() {
        return errorIds;
    }

    public void setErrorIds(List<Long> errorIds) {
        this.errorIds = errorIds;
    }
}
