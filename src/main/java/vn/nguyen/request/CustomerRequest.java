package vn.nguyen.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import vn.nguyen.utils.StatusCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by nals on 1/26/18.
 */
public class CustomerRequest implements Serializable {

    @NotNull(message = StatusCode.ERROR_NOT_BLANK)
    private Long id;

    private String name;

    @Email(message = StatusCode.ERROR_INVALID_FORMAT)
    private String email;

//    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
}
