package kryo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jujucom on 5/30/15.
 */
public class World {
  private String country;
  private int age;

  private Map<Integer, City> citys = new HashMap<>();

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Map<Integer, City> getCitys() {
    return citys;
  }

  public void setCitys(Map<Integer, City> citys) {
    this.citys = citys;
  }
}
