package com.senacor.codeandplaychannelms.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.URL
import org.springframework.validation.annotation.Validated
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Validated
data class Channel(@Id @JsonProperty("name") var name: String) {

    @JsonProperty("endpoint") @URL lateinit var endpoint: String
    @JsonIgnore var online: Boolean = true

}


