package com.senacor.codeandplaychannelms.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.validation.annotation.Validated
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Validated
data class Channel(@Id @JsonProperty("name") var name: String) {

    @JsonInclude(JsonInclude.Include.NON_NULL) var endpoint: String? = null
    @JsonIgnore var online: Boolean = true
    @JsonIgnore var lastHeartbeat: Instant = Instant.now()

}


