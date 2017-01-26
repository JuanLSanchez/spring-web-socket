package es.juanlsanchez.websocket.web.ws;

import java.time.Instant;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import es.juanlsanchez.websocket.web.dto.TextDTO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebSocketResource {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public TextDTO greeting(TextDTO text) throws Exception {
    log.debug("Text: {}", text);

    TextDTO result = new TextDTO(Instant.now().toString() + " - " + text.getText());
    Thread.sleep(1000); // simulated delay

    return result;
  }

}
