package com.senacor.codeandplaychannelms.services

import com.senacor.codeandplaychannelms.model.HealthResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "health")
interface HealthCheckClient {

    @RequestMapping(value = ["/actuator/health"], method = [RequestMethod.GET])
    fun checkChannel(): HealthResponse
}