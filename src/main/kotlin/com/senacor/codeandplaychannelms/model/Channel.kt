package com.senacor.codeandplaychannelms.model

import org.hibernate.validator.constraints.URL
import org.springframework.validation.annotation.Validated
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
@Validated
data class Channel(
        @Id @NotNull var name: String,
        @URL var endpoint: String,
        var online: Boolean = true)