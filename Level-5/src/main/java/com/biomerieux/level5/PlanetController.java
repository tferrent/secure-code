// Follow the instructions below to get started:
//
// 1. PlanetControllerTest is passing but PlanetController is vulnerable.
// 2. Review the code in this file. Can you spot the bug(s)?
// 3. Fix the bug(s). Ensure PlanetControllerTest passes.
// 4. Run PlanetControllerHackTest and if passing then CONGRATS!
package com.biomerieux.level5;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@Controller
public class PlanetController {

    // Hard-coded planet data
    private static final Map<String, String> PLANET_DATA = Map.of(
            "Mercury", "The smallest and fastest planet in the Solar System.",
            "Venus", "The second planet from the Sun and the hottest planet.",
            "Earth", "Our home planet and the only known celestial body to support life.",
            "Mars", "The fourth planet from the Sun and often called the 'Red Planet'.",
            "Jupiter", "The largest planet in the Solar System and known for its great red spot."
    );

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String submit(@RequestParam(value = "planet", required = false) String planet,
                          Model model, HttpServletResponse response) throws IOException {

        String sanitizedPlanet = planet == null ? "" : planet.replaceAll("[<>{}\\[\\]]", "");

        if (sanitizedPlanet.isBlank()) {
            writeRaw(response, "<h2>Please enter a planet name.</h2>");
            return null;
        }

        if (sanitizedPlanet.toLowerCase().contains("script")) {
            writeRaw(response, "<h2>Blocked</h2></p>");
            return null;
        }

        model.addAttribute("planet", sanitizedPlanet);
        model.addAttribute("info", getPlanetInfo(sanitizedPlanet));
        return "details";
    }

    static String getPlanetInfo(String planet) {
        return PLANET_DATA.getOrDefault(planet, "Unknown planet.");
    }

    private static void writeRaw(HttpServletResponse response, String body) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(body);
    }
}
