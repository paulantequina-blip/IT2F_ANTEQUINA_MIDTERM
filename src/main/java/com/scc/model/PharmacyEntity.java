package com.scc.model;

public record PharmacyEntity(
        int id,
        String brandName,
        String category,
        double price,
        int stock
) {}