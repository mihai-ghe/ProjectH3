import java.sql.Connection;
import java.sql.ResultSet;

public class Main {


    public static void main(String[] args) {
        Connection con;
        ResultSet rs;

        con = DB_connection.connect();

        //Patient window
      PFrame frame = new PFrame(con);

      //Doc's window
      DFrame frame1 = new DFrame(con);


    }



}
