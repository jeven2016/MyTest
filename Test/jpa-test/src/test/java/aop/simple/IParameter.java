package aop.simple;

import java.util.ArrayList;

/**
 * Created by root on 14-12-13.
 */
public interface IParameter {
  void showInfo(ArrayList<Person> persons, City city);

  int getLiveTTL(String name, int beginTime);

  void throwExcep();

  void throwExcep2() throws Exception;
}
