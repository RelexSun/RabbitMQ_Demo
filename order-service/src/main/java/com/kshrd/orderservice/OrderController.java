package com.kshrd.orderservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderPublisher publisher;

    @PostMapping("/topic/{id}")
    public ResponseEntity<String> sendTopic(@PathVariable String id) {
        publisher.publishTopic(id);
        return ResponseEntity.ok("Topic message sent for order " + id);
    }

    @PostMapping("/fanout/{id}")
    public ResponseEntity<String> sendFanout(@PathVariable String id) {
        publisher.publishFanout(id);
        return ResponseEntity.ok("Fanout message sent for order " + id);
    }

    @PostMapping("/direct/{id}")
    public ResponseEntity<String> sendDirect(@PathVariable String id) {
        publisher.publishDirect(id);
        return ResponseEntity.ok("Direct message sent for order " + id);
    }

    @PostMapping("/headers/{id}")
    public ResponseEntity<String> sendHeaders(@PathVariable String id) {
        publisher.publishHeaders(id);
        return ResponseEntity.ok("Headers message sent for order " + id);
    }
}
