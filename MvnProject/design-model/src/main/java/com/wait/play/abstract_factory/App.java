package com.wait.play.abstract_factory;


/*

抽象工厂模式：
多个抽象产品类，每个抽象产品类可以派生出多个具体产品类。
一个抽象工厂类，可以派生出多个具体工厂类。
每个具体工厂类可以创建多个具体产品类的实例。

*/


public class App {


  public static void main(String[] args) {

    FactoryA factoryA = new FactoryA();
    factoryA.produceATv().open();
    factoryA.produceCar().start();


    FactoryB factoryB = new FactoryB();
    factoryB.produceATv().open();
    factoryB.produceCar().start();
  }
}
