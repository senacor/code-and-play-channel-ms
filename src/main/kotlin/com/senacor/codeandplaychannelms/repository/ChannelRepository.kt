package com.senacor.codeandplaychannelms.repository

import com.senacor.codeandplaychannelms.model.Channel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChannelRepository : CrudRepository<Channel, String>