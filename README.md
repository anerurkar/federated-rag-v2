<<<<<<< HEAD
# federated-rag-v2
Federated RAG Version2 POC
=======
# Federated RAG Version2

README - Federated RAG Version2
📘 Federated RAG V2 — Enterprise AI Knowledge Architecture
🧭 Overview

Federated RAG V2 is a domain-aware Retrieval-Augmented Generation (RAG) system designed for enterprise BFSI use cases such as:

🏦 KYC (Know Your Customer)
💰 Lending & Credit Policy
💳 Payments & Fraud Context (extensible)

It implements a multi-domain knowledge architecture with intelligent routing, vector search, and metadata-driven retrieval using Spring Boot, Spring AI, and pgvector.

🚀 Key Capabilities
🔍 Domain-based query routing (KYC / Lending / etc.)
🧠 Vector-based semantic search using pgvector
📄 Document ingestion from structured knowledge sources
🏷️ Metadata tagging for domain isolation
⚡ REST API for real-time query retrieval
🧩 Extensible architecture for federated knowledge hubs
🏗️ Architecture
User Query
    ↓
Intent Router (Domain Detection)
    ↓
+----------------------+
|  KYC Knowledge Hub   |
|  Lending Hub         |
|  Payments Hub        |
+----------------------+
    ↓
Vector Search (pgvector)
    ↓
Top-K Relevant Chunks
    ↓
Response Aggregation Layer
    ↓
Final Answer
📁 Project Structure
federated-rag-v2
│
├── src/main/java
│   └── service/
│       ├── IngestionService.java
│       ├── FederatedQueryService.java
│       └── RouterService.java
│
├── src/main/resources
│   └── data/
│       ├── kyc/
│       │    └── kyc.txt
│       ├── lending/
│       │    └── lending.txt
│
├── docker-compose.yml
├── application.yml
└── README.md
⚙️ Tech Stack
Java 17
Spring Boot 3.x
Spring AI
PostgreSQL + pgvector
Docker Compose
Ollama (local LLM + embeddings)
REST APIs
🐳 Infrastructure Setup
Start PostgreSQL with pgvector
docker compose up -d

Ensure database:

ragdb
🧠 AI Models (Local Setup)

Using Ollama:

Chat Model: llama3.2
Embedding Model: nomic-embed-text
📥 Data Ingestion Flow

On application startup:

KYC Policy → Chunking → Embedding → Vector Store
Lending Policy → Chunking → Embedding → Vector Store

Each document is tagged with:

{
  "domain": "kyc",
  "source": "data/kyc/kyc.txt"
}
🔎 API Usage
▶️ KYC Query
curl "http://localhost:8082/ask?q=what triggers edd in kyc"
▶️ Lending Query
curl "http://localhost:8082/ask?q=minimum salary for personal loan"
▶️ Cross-Domain Query
curl "http://localhost:8082/ask?q=loan blocked after kyc clearance"
🧪 Sample Responses
KYC
PEP match or sanctions hit triggers Enhanced Due Diligence (EDD).
Lending
Minimum salary requirement for personal loan is INR 25,000.
🧩 Design Highlights
✔ Federated Knowledge Hubs

Each domain is isolated for governance and compliance.

✔ Metadata-Aware Retrieval

Ensures correct domain filtering during search.

✔ Extensible Routing Layer

New domains can be added without changing core logic.

🔐 BFSI Alignment

This architecture supports:

AML / KYC compliance workflows
Audit-friendly knowledge retrieval
Domain isolation for regulatory requirements
Explainable AI responses
🚀 Future Enhancements (V3 Roadmap)
🔁 Multi-hop reasoning across domains
🧠 Reranking layer (cross-encoder)
📊 Confidence scoring per response
📜 Audit logs for regulatory compliance
🔄 Kafka-based ingestion pipeline
🤖 Agentic AI query orchestration
👨‍💻 Author

Anand Nerurkar
Enterprise Architect | AI & Cloud Transformation Leader
Specializing in BFSI AI modernization & federated architectures
