package fr.oscar.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class HelloWorldApplication {
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Step helloWorldStep() {
		return stepBuilderFactory.get("step")
				.tasklet(helloWorldTasklet(null))
				.build();
	}
	
	@Bean
	@StepScope
	public HelloWorldTasklet helloWorldTasklet(@Value("#{jobParameters['name']}")String name) {
		return new HelloWorldTasklet(name);
	}
	
	@Bean
	public Job helloWorldJob() {
		return jobBuilderFactory.get("job")
				.start(helloWorldStep())
				.build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}
