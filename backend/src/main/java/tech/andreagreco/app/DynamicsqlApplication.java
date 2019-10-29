package tech.andreagreco.app;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.andreagreco.app.model.User;

@MappedTypes(User.class)
@MapperScan("tech.andreagreco.app.mapper")
@SpringBootApplication
public class DynamicsqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicsqlApplication.class, args);
	}

}
