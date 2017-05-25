package com.xeline.core.config;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public abstract class AbstractMessageSourceConfig {

  @Bean
  // @Production
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
    bean.setBasenames(messageSourceBasenames());
    return bean;
  }

  // @Bean(name = "messageSource")
  // @Dev
  // public MessageSource messageSourceForDev() {
  // ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
  // bean.setBasenames(messageSourceBasenames());
  // bean.setCacheSeconds(20);
  // return bean;
  // }

  protected String[] messageSourceBasenames() {
    return new String[] {path("messages")};
  }

  abstract protected String messageSourceBasepath();

  protected String path(String basename) {
    List<String> list = Lists.newArrayList(Splitter.on("/").split(messageSourceBasepath()));
    list.add(basename);
    return Joiner.on("/").join(list);
  }

}
