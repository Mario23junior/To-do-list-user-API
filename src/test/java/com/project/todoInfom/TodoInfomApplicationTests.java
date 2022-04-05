package com.project.todoInfom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
class TodoInfomApplicationTests {

	@Test
	@DisplayName("Teste a classe principal")
 	void main() {
		TodoInfomApplication.main(new String[] {});
	}

}
