package vn.nguyen.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import vn.nguyen.domain.Customer;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Created by nals on 1/25/18.
 */
@Repository
@Mapper
public interface CustomerMapper {

    @Insert("INSERT INTO CUSTOMER(id, name, email, dateCreated) VALUES(#{id},#{name},#{email},#{dateCreated})")
    void addCustomer(@Param("id") Long id, @Param("name") String name, @Param("email") String email, @Param("dateCreated")Date dateCreated);

    @Delete("delete from CUSTOMER where id=#{id}")
    void deleteByUserId(@Param("id") Long id);

    @Update("update CUSTOMER set name=#{name}, email=#{email} where id=#{id}")
    void updateUser(@Param("id") Integer id, @Param("name") String name, @Param("email") String email);

    @Select("SELECT * FROM CUSTOMER")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email")
    })
    List<Customer> findAll();

    @Select("SELECT * FROM CUSTOMER WHERE id=#{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email")
    })
    Customer findByCustomerById(@Param("id") Long id);
}
