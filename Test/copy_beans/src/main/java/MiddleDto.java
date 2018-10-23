import java.util.Date;

/**
 * Created by root on 7/2/15.
 */
public class MiddleDto {
  boolean flag;
  long id;
  int[] item;
  double price;
  String type;

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int[] getItem() {
    return item;
  }

  public void setItem(int[] item) {
    this.item = item;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  Date date;
}
