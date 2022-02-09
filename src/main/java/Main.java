import static spark.Spark.*;

import com.twilio.rest.api.v2010.account.Call;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;

public class Main {
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    Spark.staticFiles.location("/public");

    Dotenv dotenv = Dotenv.load();
    String ACCOUNT_SID = dotenv.get("ACCOUNT_SID");
    String AUTH_TOKEN = dotenv.get("AUTH_TOKEN");
    String MY_PHONE = dotenv.get("MY_PHONE");
    String TWILIO_PHONE = dotenv.get("TWILIO_PHONE");

    get("/voz", (req, res) -> {

      String result = new String();
      try {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Call call = Call.creator(
                        new com.twilio.type.PhoneNumber(MY_PHONE),
                        new com.twilio.type.PhoneNumber(TWILIO_PHONE),
                        URI.create("http://demo.twilio.com/docs/voice.xml"))
                .create();
        result = call.getSid();
      } catch (Exception  exception){
        LOG.error(exception.getMessage());
      }


      return result;

    });

    get("/sms", (req, res) -> {
      String result = new String();
      try {
      Message message = Message.creator(
                      new com.twilio.type.PhoneNumber(MY_PHONE),
                      new com.twilio.type.PhoneNumber(TWILIO_PHONE),
                      "Mensaje enviado desde Twilio")
              .create();

      result = message.getSid();
      } catch (Exception  exception){
        LOG.error(exception.getMessage());
      }
      return result;
    });
  }


}
