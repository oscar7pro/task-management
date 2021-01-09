package fr.oscar.batch;

import java.util.Arrays;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.oscar.batch.validators.MyJobParametersValidator;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean
	public JobParametersValidator defauJobParametersValidator() {
		DefaultJobParametersValidator bean = new DefaultJobParametersValidator();
		bean.setRequiredKeys(new String[] { "formateursFile", "formationsFile", "seancesFile" });
		bean.setOptionalKeys(new String[] { "run.id" });
		return bean;
	}

	@Bean
	public JobParametersValidator myJobParametersValidator() {
		return new MyJobParametersValidator();
	}

	@Bean
	public JobParametersValidator compositeJobParametersValidator() {
		CompositeJobParametersValidator bean = new CompositeJobParametersValidator();
		bean.setValidators(Arrays.asList(defauJobParametersValidator(), myJobParametersValidator()));
		return bean;
	}

	@Bean
	public Job job(final JobBuilderFactory jobBuilderFactory, final Step chargementFormateursStep) {
		return jobBuilderFactory.get("oscar-formations-batch").start(chargementFormateursStep)
				.validator(compositeJobParametersValidator()).incrementer(new RunIdIncrementer()).build();
	}
}
