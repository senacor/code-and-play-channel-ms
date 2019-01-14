package com.senacor.codeandplaychannelms.api

import com.senacor.codeandplaychannelms.model.Channel
import com.senacor.codeandplaychannelms.services.ChannelService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/channels")
class ChannelController(val channelService: ChannelService) {

    @GetMapping
    fun getAllChannels() = channelService.getAllChannels()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun registerChannel(@RequestBody channel: Channel) = channelService.registerChannel(channel)
}