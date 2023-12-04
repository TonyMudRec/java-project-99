package hexlet.code.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    /**
     * welcome page handler.
     * @return greeting
     */
    @GetMapping(path = "/welcome")
    public String welcome() {
        return "Welcome to Spring!";
    }
}
