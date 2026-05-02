package com.example.federatedrag.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class IngestionService implements CommandLineRunner {

    private final VectorStore vectorStore;

    public IngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) {
        ingest("data/kyc/kyc.txt", "kyc");
        ingest("data/lending/lending.txt", "lending");
    }

    private void ingest(String path, String domain) {
        try {
            // Load file from classpath (src/main/resources)
            ClassPathResource resource = new ClassPathResource(path);

            InputStream is = resource.getInputStream();
            String text = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            // Create document with metadata
            Document doc = new Document(text);
            doc.getMetadata().put("domain", domain);
            doc.getMetadata().put("source", path);

            // Store in vector DB
            vectorStore.add(List.of(doc));

            System.out.println("Ingested domain: " + domain + " from " + path);

        } catch (Exception e) {
            throw new RuntimeException("Failed to ingest file: " + path, e);
        }
    }
}