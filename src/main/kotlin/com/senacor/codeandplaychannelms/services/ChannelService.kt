package com.senacor.codeandplaychannelms.services

import com.senacor.codeandplaychannelms.model.Channel
import com.senacor.codeandplaychannelms.repository.ChannelRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Instant


@Service
class ChannelService(val channelRepository: ChannelRepository) {

    val log: Logger = LoggerFactory.getLogger(ChannelService::class.java)

    fun getAllChannels(): List<Channel> {
        return channelRepository.findAll().filter { it.online }.onEach { it.endpoint = null }
    }

    fun registerChannel(newChannel: Channel): Channel {
        val heartbeatTime = Instant.now()
        val channel = channelRepository.findById(newChannel.name)

        if(!channel.isPresent) {
            newChannel.lastHeartbeat = heartbeatTime
            log.info("Created new channel [{}]", newChannel.name)
            return channelRepository.save(newChannel)
        }

        channel.get().endpoint = newChannel.endpoint
        channel.get().lastHeartbeat = heartbeatTime
        channel.get().online = true

        return channelRepository.save(channel.get())
    }

    @Scheduled(fixedDelay = 60000)
    fun checkChannelStatus() {
        val now = Instant.now()
        val channels = channelRepository.findAll()

        channels.forEach {
            log.info("Checking status of channel [{}]", it.name)

            if(now.isAfter(it.lastHeartbeat.plusSeconds(60))) {
                // Channel is offline
                it.online = false
            }

            log.info("Channel [{}] is  online: [{}]", it.name, it.online)

            channelRepository.save(it)
        }
    }
}