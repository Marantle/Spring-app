package com.marantle.bootstrap;

import com.marantle.entity.Users;
import com.marantle.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class UsersLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UsersRepository usersRepository;

    private Logger log = Logger.getLogger(UsersLoader.class);

    @Autowired
    public void setProductRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Users marko = new Users();
        marko.setFirstName("Marko");
        marko.setLastName("Marko1");
        marko.setUserName("Markonen");
        marko.setPassword("suola");
        marko.setEmail("hello@hello.com");
        usersRepository.save(marko);

        log.info("Saved marko - id: " + marko.getId());
    }
}