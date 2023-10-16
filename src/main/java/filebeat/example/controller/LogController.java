package filebeat.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequiredArgsConstructor
public class LogController {

    private static final Logger dataLogger = LoggerFactory.getLogger("INDEXING_DATA_LOGGER");

    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    @PostMapping(value = "/hello/{index}")
    public String sayHello(@RequestBody String body, @PathVariable String index, HttpServletRequest request) {
        dataLogger.info(String.format("%s\t%s\t%s\t(\"host\":\"%s\",\"service\":\"%s\")\t%s",
                LocalDateTime.now().format(timeFormat),
                request.getMethod(),
                request.getRequestURI(), request.getHeader("Host"), request.getParameter("service"), body.replaceAll("\r?\n", " ")));

        return "Hello " + index;
    }

    @GetMapping("/goodbye/{name}")
    public String sayGoodBye(@PathVariable String name) {
        return "Good bye " + name;
    }
}
