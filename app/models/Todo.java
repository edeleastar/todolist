package models;

import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Todo extends Model
{
  public String title;
  public Blob image;

  public Todo(String title)
  {
    this.title = title;
  }
}
