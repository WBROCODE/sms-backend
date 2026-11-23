package net.javaguides.sms;

import net.javaguides.sms.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SmsBackendApplication {

	public static void main(String[] args) {
		ApplicationContext  context = SpringApplication.run(SmsBackendApplication.class, args);


		// 1. Print the beans that came from our own code
		for (String name : context.getBeanDefinitionNames()) {
			if (name.startsWith("student") || name.startsWith("sms")) {
				System.out.println("Bean: " + name + " -> " + context.getBean(name).getClass().getSimpleName());
			}
		}

		// 2. Ask the container for a bean, like context.getBean("car") in Video 1
		StudentService service = context.getBean(StudentService.class);
		System.out.println("StudentService is really: " + service.getClass().getSimpleName());

	}

}
