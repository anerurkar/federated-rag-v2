package com.example.federatedrag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FederatedQueryService {

    private final VectorStore vectorStore;
    private final RoutingService routingService;
    private final ChatClient chatClient;

    public FederatedQueryService(VectorStore vectorStore,
                                 RoutingService routingService,
                                 ChatClient.Builder builder) {
        this.vectorStore = vectorStore;
        this.routingService = routingService;
        this.chatClient = builder.build();
    }

    public String ask(String query) {

        List<String> domains = routingService.detectDomains(query);
        List<Document> results = new ArrayList<>();

        for (String d : domains) {

            SearchRequest req = SearchRequest.builder()
                    .query(query)
                    .topK(2)
                    .filterExpression("domain == '" + d + "'")
                    .build();

            results.addAll(vectorStore.similaritySearch(req));
        }

        List<Document> top = results.stream()
                .limit(4)
                .toList();

        String context = top.stream()
                .map(x -> "[" + x.getMetadata().get("domain") + "] " + x.getText())
                .collect(Collectors.joining("\n"));

        return chatClient.prompt()
                .user(u -> u.text("Answer only from context:\n\nQ: " + query + "\n\nContext:\n" + context))
                .call()
                .content();
    }
}