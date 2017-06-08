package io.github.budincsevity.OpenSubtitlesAPI.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping(value = "/")
    fun index() = "redirect:swagger-ui.html"
}
