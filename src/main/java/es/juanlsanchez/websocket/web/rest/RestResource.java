package es.juanlsanchez.websocket.web.rest;

import java.time.Instant;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.juanlsanchez.websocket.web.dto.TextDTO;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestResource {

  private final SimpMessagingTemplate template;

  @Inject
  public RestResource(final SimpMessagingTemplate template) {
    this.template = template;
  }


  @RequestMapping(value = "/api/text", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @SendTo("/topic/greetings")
  public ResponseEntity<TextDTO> post(@RequestBody TextDTO text) {
    log.debug("Text: {}", text);

    template.convertAndSend("/topic/greetings",
        new TextDTO(Instant.now().toString() + "- From REST -> " + text.getText()));

    return ResponseEntity.ok(text);
  }

}
