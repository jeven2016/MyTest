package zjtech.bmf.inject.zjtech.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by root on 15-3-27.
 */
@Entity
@Table(name = "log", schema = "smf_admin", catalog = "")
public class LogEntity {
  private int id;
  private String name;
  private Timestamp time;

  @Id
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "time")
  public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LogEntity logEntity = (LogEntity) o;

    if (id != logEntity.id) return false;
    if (name != null ? !name.equals(logEntity.name) : logEntity.name != null)
      return false;
    if (time != null ? !time.equals(logEntity.time) : logEntity.time != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (time != null ? time.hashCode() : 0);
    return result;
  }
}
