package DataAccess;
import java.sql.*;
import java.util.ArrayList;

public class Setup {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            ArrayList<String> queries = new ArrayList<>();

            String person =
                    "CREATE TABLE public.person(" +
                    "id SERIAL PRIMARY KEY NOT NULL," +
                    "name VARCHAR(255)," +
                    "last_name VARCHAR(255)," +
                    "phone VARCHAR(50)," +
                    "phone_alt VARCHAR(50)," +
                    "type VARCHAR(50)," +
                    "email VARCHAR(255))";
            queries.add(person);

            String play =
                    "CREATE TABLE public.play(" +
                    "id SERIAL PRIMARY KEY NOT NULL," +
                    "name VARCHAR(255)," +
                    "responsible INT REFERENCES person (id)ON DELETE CASCADE ON UPDATE CASCADE," +
                    "description VARCHAR(510)," +
                    "cancelled BOOLEAN DEFAULT FALSE)";
            queries.add(play);

            String show =
                    "CREATE TABLE public.show(" +
                    "id SERIAL PRIMARY KEY NOT NULL," +
                    "date DATE," +
                    "time TIME," +
                    "play INT REFERENCES play (id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "price DECIMAL(12,2)," +
                    "cancelled BOOLEAN DEFAULT FALSE)";
            queries.add(show);

            String actor_list =
                    "CREATE TABLE public.actor_list(" +
                    "show INT REFERENCES show (id)," +
                    "actor INT REFERENCES person (id))";
            queries.add(actor_list);

            String zone =
                    "CREATE TABLE public.zone(" +
                    "id SERIAL PRIMARY KEY NOT NULL," +
                    "discount_percent INT)";
            queries.add(zone);

            String seat =
                    "CREATE TABLE public.seat(" +
                    "id SERIAL PRIMARY KEY NOT NULL," +
                    "row VARCHAR(10)," +
                    "number INT," +
                    "zone INT REFERENCES zone (id) ON DELETE CASCADE ON UPDATE CASCADE)";
            queries.add(seat);

            String seating =
                    "CREATE TABLE public.seating(" +
                    "id SERIAL PRIMARY KEY NOT NULL," +
                    "seat INT REFERENCES seat (id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "state VARCHAR(50)," +
                    "show INT REFERENCES show (id) ON DELETE CASCADE ON UPDATE CASCADE)";
            queries.add(seating);

            String purchase =
                    "CREATE TABLE public.purchase(" +
                    "id SERIAL PRIMARY KEY NOT NULL," +
                    "client INT REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE," +
//                    "tickets INT," +
                    "total DECIMAL(12,2))";
            queries.add(purchase);

            String ticket =
                    "CREATE TABLE public.ticket(" +
                    "id SERIAL PRIMARY KEY NOT NULL," +
                    "seating INT REFERENCES seating (id)," +
                    "returned BOOLEAN DEFAULT FALSE," +
                    "price DECIMAL(12,2)," +
                    "purchase INT REFERENCES purchase(id))";
            queries.add(ticket);

            for (String query : queries){
                statement.executeUpdate(query);
            }

            System.out.println("Setup finished.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
