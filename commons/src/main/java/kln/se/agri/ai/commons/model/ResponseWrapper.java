package kln.se.agri.ai.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {

    /**
     * The status of the response.
     */
    private Status status;

    private String message;

    private String responseCode;
//    /**
//     * The reason for failure (if specific)
//     */
//    private CauseCode causeCode;


    public ResponseWrapper() {
    }

    public ResponseWrapper(String errors, HttpHeaders httpHeaders, HttpStatus partialContent) {

    }

    public enum Status {
        SUCCESS,
        FAILURE
    }

    /**
     * The updated entity.
     */
    private Object body;

    private ResponseWrapper(Status status, Object body) {
        this.status = status;
        this.body = body;
    }

    private ResponseWrapper(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    private ResponseWrapper(Status status, Object body, String message) {
        this.status = status;
        this.body = body;
        this.message = message;
    }

    private ResponseWrapper(Status status, String responseCode, String message) {
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
    }

    private ResponseWrapper(Status status, String code, Object body) {
        this.status = status;
        this.body = body;
        this.responseCode = code;
    }


    private ResponseWrapper(String code, String message, Throwable ... throwables) {
        this.status = Status.FAILURE;
        this.responseCode = code;
        this.message = message;
        // handle exception if provided
//        if(throwables.length == 1) {
//            this.message = throwables[0].getMessage();
//        }
    }

    private ResponseWrapper(Status status, String responseCode, String message, Object body) {
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
        this.body = body;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public CauseCode getCauseCode() {
//        return causeCode;
//    }
//
//    public void setCauseCode(CauseCode causeCode) {
//        this.causeCode = causeCode;
//    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @JsonIgnore
    public static ResponseWrapper success() {
        return new ResponseWrapper(Status.SUCCESS, null);
    }

    @JsonIgnore
    public static ResponseWrapper success(Object body) {
        return new ResponseWrapper(Status.SUCCESS, body);
    }

    @JsonIgnore
    public static ResponseWrapper success(String responseCode, String message, Object body) {
        return new ResponseWrapper(Status.SUCCESS, responseCode, message, body);
    }

    @JsonIgnore
    public static ResponseWrapper success(String code, String message) {
        return new ResponseWrapper(Status.SUCCESS, code, message);
    }

    @JsonIgnore
    public static ResponseWrapper successWithMessage(String code, String message) {
        return new ResponseWrapper(Status.SUCCESS, code, message);
    }

    @JsonIgnore
    public static ResponseWrapper successWithCode(String code, Object body) {
        return new ResponseWrapper(Status.SUCCESS, code, body);
    }

    @JsonIgnore
    public static ResponseWrapper fail() {
        return new ResponseWrapper(Status.FAILURE, null);
    }

    @JsonIgnore
    public static ResponseWrapper fail(String responseCode) {
        return new ResponseWrapper(Status.FAILURE, null, responseCode);
    }

    @JsonIgnore
    public static ResponseWrapper failWithMessage(Object body) {
        return new ResponseWrapper(Status.FAILURE, body);
    }

    @JsonIgnore
    public static ResponseWrapper failWithMessage(String message) {
        return new ResponseWrapper(Status.FAILURE, message);
    }
    @JsonIgnore
    public static ResponseWrapper failWithMessage(String code, String message) {
        return new ResponseWrapper(Status.FAILURE, code, message);
    }

    @JsonIgnore
    public static ResponseWrapper failWithMessage(String code, String message, Object body) {
        return new ResponseWrapper(Status.FAILURE, code, message, body);
    }

    /**
     * Use if an exception was thrown.
     *
     * @param throwable The exception
     * @return A response suitable for exceptions
     */
    @JsonIgnore
    public static ResponseWrapper exception(String code, String message, Throwable throwable) {
        return new ResponseWrapper(code, message, throwable);
    }


}
