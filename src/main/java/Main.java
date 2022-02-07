import static spark.Spark.*;
import spark.Spark;

public class Main {
  public static void main(String[] args) {

    Spark.staticFiles.location("/public");


    get("/hi", (req, res) -> {
      //TODO: Place your code here
      return "Hello World";
    });
    get("/hello", (req, res) -> {
      //TODO: Place your code here
      return "Ohohoh!";
    });
  }


}
