package controllers;

import models.Member;
import models.Todo;
import play.Logger;
import play.db.jpa.Blob;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Todo> todolist = member.todolist;
    render("dashboard.html", member, todolist);
  }

  public static void addTodo(String title, Blob image)
  {
    Member member = Accounts.getLoggedInMember();
    Todo todo = new Todo(title);
    todo.image = image;
    member.todolist.add(todo);
    member.save();
    Logger.info("Adding Todo" + title);
    redirect("/dashboard");
  }

  public static void deleteTodo(Long id, Long todoid)
  {
    Member member = Member.findById(id);
    Todo todo = Todo.findById(todoid);
    member.todolist.remove(todo);
    member.save();
    todo.delete();
    Logger.info("Deleting " + todo.title);
    redirect("/dashboard");
  }

  public static void getImage(Long id)
  {
    Todo todo = Todo.findById(id);
    Blob image = todo.image;
    if (image.exists())
    {
      response.setContentTypeIfNotSet(image.type());
      renderBinary(image.get());
    }
  }

}
