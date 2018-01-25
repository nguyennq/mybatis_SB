package vn.nguyen.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by nals on 1/24/18.
 */
//@RequiredArgsConstructor
//@Getter
//@ToString
//@EqualsAndHashCode
//@Entity
public class Customer {

    Long id;
    String name;
    String email;
    Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
