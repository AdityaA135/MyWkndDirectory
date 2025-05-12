package com.adobe.aem.guides.wknd.core.services.impl;
import com.adobe.aem.guides.wknd.core.models.CardOfProductModelSuperClass;
import com.adobe.aem.guides.wknd.core.services.DiscountCardService;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = DiscountCardService.class,immediate = true)
public class DiscountCardServiceImpl implements DiscountCardService {
    private static final Logger log = LoggerFactory.getLogger(DiscountCardServiceImpl.class);

    @Override
    public double getDiscountOnProduct(double price)
    {
        log.info("Price is: {}",price);
        if(price>=1000)
        {
            return 0.8*price;
        }
        else if(price<1000 && price>=500)
        {
            return 0.9*price;
        }
        else return price;
    }

    @Override
    public double getDiscountPercentage(double price)
    {
        if(price>=1000)
        {
            return 20;
        }
        else if(price<1000 && price>=500)
        {
            return 10;
        }
        else return 0;
    }
}
