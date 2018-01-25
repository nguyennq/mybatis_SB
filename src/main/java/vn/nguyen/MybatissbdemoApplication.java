package vn.nguyen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("vn.nguyen.Mapper")
public class MybatissbdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatissbdemoApplication.class, args);
	}
}
