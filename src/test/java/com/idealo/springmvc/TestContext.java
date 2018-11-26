package com.idealo.springmvc;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.idealo.springmvc.service.CategoryService;

@Configuration
public class TestContext {

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    messageSource.setBasename("/resources/messages");
    messageSource.setUseCodeAsDefaultMessage(true);

    return messageSource;
  }

  @Bean
  public CategoryService categoryService() {
    return Mockito.mock(CategoryService.class);
  }
}
