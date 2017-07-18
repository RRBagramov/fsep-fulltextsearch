package ru.fsep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.fsep.repositories.CommentDao;
import ru.fsep.repositories.impl.CommentSimpleHeadlineSearchDaoImpl;
import ru.fsep.repositories.impl.CommentSimpleSearchDaoImpl;

/**
 * 17.07.2017
 *
 * @author Robert Bagramov.
 */

@PropertySource(value = "/properties/search.properties")
@Configuration
public class SearchConfig {

    @Autowired
    private Environment environment;

    @Bean
    public CommentDao searchImpl() {
        if (environment.getProperty("searchType").equals("typo")) {
            return new CommentSimpleHeadlineSearchDaoImpl();
        }
        else
        {
            return new CommentSimpleSearchDaoImpl();
        }
    }
}

