package com.example.federatedrag.controller;

import com.example.federatedrag.service.FederatedQueryService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AskController {

    private final FederatedQueryService service;

    public AskController(FederatedQueryService service) {
        this.service = service;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String q) {
        return service.ask(q);
    }
}