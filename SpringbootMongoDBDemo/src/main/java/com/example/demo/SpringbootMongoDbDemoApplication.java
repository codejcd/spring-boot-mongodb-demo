package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootMongoDbDemoApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongoDbDemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		// 최초 실행시 삭제
		repository.deleteAll(); 
		
		// Customer 객체 생성
		Customer Bob = new Customer("Bob", "Smith");
		Customer John = new Customer("John", "Smith");
		Customer Alice = new Customer("Alice", "Smith");
		
		// mongoDB에 insert 수행.
		repository.save(Bob);
		repository.save(John);
		repository.save(Alice);
		
		// mongoDB에 Update 수행.
		Alice.setFirstName("Alice");
		Alice.setLastName("Chris");
		repository.save(Alice);
		
		// mongoDB에 조회 수행.
		Customer temp = repository.findByFirstName("Bob");
		// mongoDB에 삭제 수행.
		repository.delete(temp);
		
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
		
	}
}
