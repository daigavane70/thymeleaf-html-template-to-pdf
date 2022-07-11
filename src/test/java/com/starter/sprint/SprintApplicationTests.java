package com.starter.sprint;

import com.starter.sprint.controller.MainController;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SprintApplicationTests {

	@BeforeAll
	static void beforeAll(){
		System.out.println("Before All Method");
	}

	@BeforeEach
	void beforeEach(){
		System.out.println("Before Each");
	}

	int sum(int a, int b){
		return a+b;
	}

	int mult(int a, int b){
		return a*b;
	}


	@Test
	void test1() {
		try{
			for(int i=0;i<10;i++) {
				assertEquals(sum(i,i+10), 2*i+10);
			}
		}
		catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void test2() {
		try{
			for(int i=0;i<10;i++){
				assertEquals(mult(i,i+10), i*(i+10));
			}
		}
		catch (Exception e){
			fail(e);
		}
	}

	@AfterEach
	void afterEach(){
		System.out.println("After Each");
	}

	@AfterAll
	static void afterAll(){
		System.out.println("After All");
	}

}
