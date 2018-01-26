package vn.nguyen.exception;

/**
 * Created by nals on 1/25/18.
 */
public class CustomerIDExistException extends RuntimeException{
    private Long id;
    public CustomerIDExistException(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
