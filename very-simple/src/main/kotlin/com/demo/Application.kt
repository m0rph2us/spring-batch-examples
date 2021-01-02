package com.demo

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder

@EnableBatchProcessing
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@Configuration
class SimpleJobConfig(
        val jobBuilderFactory: JobBuilderFactory,
        val stepBuilderFactory: StepBuilderFactory
) {

    // Job 생성
    @Bean
    fun simpleJob(): Job = jobBuilderFactory.get("simpleJob")
            .start(simpleStep())
            .build()

    // Step 생성
    @Bean
    fun simpleStep(): Step = stepBuilderFactory.get("simpleStep")
            .chunk<Long, Long>(10) // 10개 단위로 write
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build()

    val numbers = (1..25L).toMutableList()

    // Reader 생성
    fun reader(): ItemReader<Long> = ItemReader {
        numbers.takeIf {
            it.isNotEmpty()
        }?.removeAt(0)?.also {
            println("read: $it")
        }
    }

    // Processor 생성
    fun processor(): ItemProcessor<Long, Long> = ItemProcessor { item -> (item * 3) }

    // Writer 생성
    fun writer(): ItemWriter<Long> = ItemWriter { println("write: $it") }

}