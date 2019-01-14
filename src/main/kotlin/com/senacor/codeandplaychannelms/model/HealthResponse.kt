package com.senacor.codeandplaychannelms.model

import com.fasterxml.jackson.annotation.JsonProperty

data class HealthResponse(@JsonProperty("status") var status: String)