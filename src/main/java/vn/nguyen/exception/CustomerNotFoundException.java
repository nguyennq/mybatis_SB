package vn.nguyen.exception;

/**
 * Created by nals on 1/26/18.
 */
public class CustomerNotFoundException extends RuntimeException{
    private Long id;

    public CustomerNotFoundException(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
