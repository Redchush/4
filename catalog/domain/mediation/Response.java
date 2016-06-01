package catalog.domain.mediation;

import catalog.domain.entity.News;

/**
 * Created by user on 31.05.2016.
 */
public class Response {
    public static final int OK = 200;
    public static final int DONE = 204;
    public static final int BAD_REQUEST = 400;



    private int intStatus;
    private String errorMessage;
    private String message;
    private Result<News> resultSet;

    public void setStatus(int intStatus){
        this.intStatus = intStatus;
    }

    public void setMessage(String sdsf) {
        this.message = message;
    }
    public void setMessage(Result<News> resultSet){
        this.resultSet = resultSet;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    public int getIntStatus() {
        return intStatus;
    }

    public String getMessage() {
        return message;
    }

    public Result<News> getResultSet() {
        return resultSet;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
