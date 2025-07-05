package dev.firefarts.aoe2spanishladder.infrastucture

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.security.auth.callback.ConfirmationCallback.OK

@RestController
class LoginController {

    @GetMapping("/me")
    fun me(@AuthenticationPrincipal oauth2User: OAuth2User): String {
        val userName = oauth2User.getAttribute<String>("username")
        val email = oauth2User.getAttribute<String>("email")
        val id = oauth2User.getAttribute<String>("id")
        val avatarHash = oauth2User.getAttribute<String>("avatar")

        val avatarUrl = "https://cdn.discordapp.com/avatars/$id/$avatarHash.png"

        return "Hola $userName ($email)<br><img src=\"$avatarUrl\" width=\"100\"/>"
    }

    @GetMapping("/login")
    fun login(): ResponseEntity<Void> {
        val redirect = URI("/oauth2/authorization/discord")
        return ResponseEntity.status(OK).location(redirect).build()
    }

    @GetMapping("/logout")
    fun logout(): ResponseEntity<Void> {
        val redirect = URI("redirect:/logout")
        return ResponseEntity.status(OK).location(redirect).build();
    }
}