package object;

public class User {
    public static int compteurId = 0;
    String firstName;
    String lastName;
    int id;

    public Agenda getMonAgenda() {
        return monAgenda;
    }

    public void setMonAgenda(Agenda monAgenda) {
        this.monAgenda = monAgenda;
    }

    Agenda monAgenda;


    // Utiliser la fonction dans UserManagement pour créer un User.
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = compteurId;
        compteurId++;
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
