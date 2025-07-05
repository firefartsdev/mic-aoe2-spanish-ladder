package dev.firefarts.aoe2spanishladder.infrastucture

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @GetMapping("/login")
    fun home(@AuthenticationPrincipal oauth2User: OAuth2User): String {
        val userName = oauth2User.getAttribute<String>("username")
        val email = oauth2User.getAttribute<String>("email")
        val id = oauth2User.getAttribute<String>("id")
        val avatarHash = oauth2User.getAttribute<String>("avatar")

        val avatarUrl = "https://cdn.discordapp.com/avatars/$id/$avatarHash.png"

        return "Hola $userName ($email)<br><img src=\"$avatarUrl\" width=\"100\"/>"
    }


}