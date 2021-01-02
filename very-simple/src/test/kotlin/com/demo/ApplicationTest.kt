package com.demo

import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@SpringBatchTest
@RunWith(SpringRunner::class)
class ApplicationTest {

    @Autowired
    lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    lateinit var simpleJob: Job

    @Test
    fun simpleJob() {
        val jobExecution: JobExecution = jobLauncherTestUtils.run {
            job = simpleJob
            launchJob()
        }

        Assert.assertEquals("COMPLETED", jobExecution.exitStatus.exitCode)
    }

}