package com.micromata.labs.cewclient.controller;

import com.micromata.labs.cewclient.payload.RenderConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hagen Trinter (h.trinter@micromata.de)
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {

  @PostMapping(path = "/render")
  public ResponseEntity<String> render(@RequestBody final RenderConfig renderConfig) {
    return ResponseEntity.ok("hello world " + renderConfig.getCount());
  }

}
