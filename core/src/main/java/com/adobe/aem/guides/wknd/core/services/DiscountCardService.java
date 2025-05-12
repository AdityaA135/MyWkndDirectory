package com.adobe.aem.guides.wknd.core.services;

public interface DiscountCardService {
    double getDiscountOnProduct(double price);
    double getDiscountPercentage(double price);
}
