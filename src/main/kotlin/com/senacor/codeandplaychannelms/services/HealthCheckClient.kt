package com.senacor.codeandplaychannelms.services

import com.senacor.codeandplaychannelms.model.HealthResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "health")
interface HealthCheckClient {

    @GetMapping(value = ["/{channel}/actuator/health"])
    fun checkChannel(@PathVariable("channel") channel: String): HealthResponse
}