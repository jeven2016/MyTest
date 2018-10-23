package aop.simple;

import java.util.ArrayList;
import java.util.Random;


public class ParameterFirst implements IParameter {
  private Random random = new Random();

  @Override
  public void showInfo(ArrayList<Person> persons, City city) {

  }

  @Override
  public int getLiveTTL(String name, int beginTime) {
    return random.nextInt();
  }

  @Override
  public void throwExcep() {
    throw new RuntimeException("this is for test");
  }

  @Override
  public void throwExcep2() throws Exception{
     throw new Exception("form throwExcep2");
  }


}
