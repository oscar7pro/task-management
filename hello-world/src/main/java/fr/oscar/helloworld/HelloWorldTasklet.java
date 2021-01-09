package fr.oscar.helloworld;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class HelloWorldTasklet implements Tasklet {

	private final String name;
	

	public HelloWorldTasklet(String name) {
		super();
		this.name = name;
	}


	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("Hello " + name);
		return RepeatStatus.FINISHED;
	}

}
