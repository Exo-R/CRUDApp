package ru.ex0r.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ex0r.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int PEOPLE_COUNT;
/*  private List<Person> people;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "exor";

    private static Connection connection;

    static {
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    /*{
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Nick", "nick@gmail.com", 25));
        people.add(new Person(++PEOPLE_COUNT, "Dick", "dick@gmail.com", 34));
        people.add(new Person(++PEOPLE_COUNT, "Thick", "thick@gmail.com", 54));
        people.add(new Person(++PEOPLE_COUNT, "Lick", "lick@gmail.com", 44));
        people.add(new Person(++PEOPLE_COUNT, "Sick", "sick@gmail.com", 65));
    }*/

    public List<Person> index(){

        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));

        /*List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){

                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return people;*/
    }

    public Person show(int id){

        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);

        /*Person person = null;
        try {
            String SQL = "SELECT * FROM Person WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setAge(resultSet.getInt("age"));

        }catch (Exception ex){ex.printStackTrace();}

        return person;*/

        //return null;// people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){

        jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?)",
                /*person.getId()*/ ++PEOPLE_COUNT, person.getName(), person.getEmail(), person.getAge());
/*
        try{
            String SQL = "INSERT INTO Person VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, person.getAge());

            preparedStatement.executeUpdate();

        }catch (Exception ex){ex.printStackTrace();}*/
    }

    public void update(int id, Person updatedPerson){

        jdbcTemplate.update("UPDATE Person SET name=?, email=?, age=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getEmail(), updatedPerson.getAge(), id);
/*
        try {
            String SQL = "UPDATE Person SET name=?, email=?, age=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setString(2, updatedPerson.getEmail());
            preparedStatement.setInt(3, updatedPerson.getAge());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        }
        catch (SQLException ex){ex.printStackTrace();}
*/
        /*Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setAge(updatedPerson.getAge());*/
    }

    public void delete(int id){

        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);

        /*
        PreparedStatement preparedStatement = null;
        try {
            String SQL = "DELETE FROM Person WHERE id=?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex){ex.printStackTrace();}

        //people.removeIf(p -> p.getId() == id);*/
    }

}
