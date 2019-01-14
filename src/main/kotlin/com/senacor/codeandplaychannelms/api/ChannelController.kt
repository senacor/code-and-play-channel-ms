package com.senacor.codeandplaychannelms.api

import com.senacor.codeandplaychannelms.model.Channel
import com.senacor.codeandplaychannelms.services.ChannelService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/")
class ChannelController(val channelService: ChannelService) {

    @GetMapping
    fun getAllChannels() = channelService.getAllChannels()

    @PostMapping
    fun registerChannel(@Valid channel: Channel) = channelService.registerChannel(channel)
}