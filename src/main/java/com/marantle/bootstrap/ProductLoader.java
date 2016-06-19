package com.marantle.bootstrap;

import com.marantle.entity.Product;
import com.marantle.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;

    private Logger log = Logger.getLogger(ProductLoader.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Product plane = new Product();
        plane.setDescription("An airplane");
        plane.setPrice(new BigDecimal("18.95"));
        plane.setImageUrl("Airplane.png");
        plane.setProductId("235268845711068308");
        productRepository.save(plane);
        
        log.info("Saved plane - id: " + plane.getId());

        Product cycle = new Product();
        cycle.setDescription("A bicycle");
        cycle.setImageUrl("Bicycle.png");
        cycle.setProductId("168639393495335947");
        productRepository.save(cycle);

        log.info("Saved cycle - id: " + cycle.getId());
        
    }
}