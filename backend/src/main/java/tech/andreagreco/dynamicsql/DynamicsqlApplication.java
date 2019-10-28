package tech.andreagreco.dynamicsql;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.andreagreco.dynamicsql.model.User;

@MappedTypes(User.class)
@MapperScan("tech.andreagreco.dynamicsql.mapper")
@SpringBootApplication
public class DynamicsqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicsqlApplication.class, args);
	}

}
