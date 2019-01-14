package com.senacor.codeandplaychannelms.services

import com.senacor.codeandplaychannelms.model.Channel
import com.senacor.codeandplaychannelms.repository.ChannelRepository
import feign.Feign
import feign.jackson.JacksonDecoder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service


@Service
class ChannelService(val channelRepository: ChannelRepository) {

    val log: Logger = LoggerFactory.getLogger(ChannelService::class.java)

    fun getAllChannels(): List<Channel> {
        return channelRepository.findAll().filter { it.online }
    }

    fun registerChannel(newChannel: Channel): Channel {
        val channel = channelRepository.findById(newChannel.name)

        if(!channel.isPresent) {
            log.info("Created new channel [{}]", newChannel.name)
            return channelRepository.save(newChannel)
        }

        return channel.get()
    }

    @Scheduled(fixedDelay = 30000)
    fun checkChannelStatus() {
        val channels = channelRepository.findAll()

        channels.forEach {
            log.info("Checking status of channel [{}]", it.name)
            val client = Feign.builder()
                    .decoder(JacksonDecoder())
                    .target(HealthCheckClient::class.java, it.endpoint)

            val status = client.checkChannel(it.name)
            log.info("Channel [{}] is [{}]", it.name, status.status)

            it.online = "UP" == status.status

            channelRepository.save(it)
        }
    }
}