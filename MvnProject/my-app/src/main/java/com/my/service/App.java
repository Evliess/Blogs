package com.my.service;

import com.my.utils.ColorProgressBarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);


  public static void main(String[] args) throws Exception {
    for (int i = 1; i <= 100; i++) {
      String progress = ColorProgressBarUtil.buildProgressBar(i, 100);
      System.out.print(progress);
      Thread.sleep(100);
      for (int j = 1; j <= progress.length(); j++) {
        System.out.print("\b");
      }
      if (i == 100) {
        for (int j = 1; j <= progress.length(); j++) {
          System.out.print("\b");
        }
        System.out.print(ColorProgressBarUtil.buildDone());
      }
    }
  }



}
