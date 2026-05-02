package com.example.federatedrag.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RoutingService {

    public List<String> detectDomains(String q) {
        q = q.toLowerCase();
        List<String> d = new ArrayList<>();

        if (q.contains("kyc") || q.contains("edd") || q.contains("sanction"))
            d.add("kyc");

        if (q.contains("loan") || q.contains("salary") || q.contains("disbursement"))
            d.add("lending");

        if (d.isEmpty()) {
            d.add("kyc");
            d.add("lending");
        }

        return d;
    }
}