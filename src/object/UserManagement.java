package object;

import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    public static List<User> listUser = new ArrayList<User>();

    public List<User> getUsers() {
        return listUser;
    }


    public static void createUser(String firstName, String lastName){
        User user = new User(firstName, lastName);
        Agenda agenda = new Agenda(user);
        user.setMonAgenda(agenda);
        listUser.add(user);
        AgendaManagement.listAgendas.add(agenda);
    }

    public static void showUser(){
        for (int i = 0; i < listUser.size(); i++){
            User monUser = listUser.get(i);
            detailUser(monUser);
        }
    }

    public static User getUserById(int userId) {
        User user = new User();
        for (int i = 0; i < listUser.size(); i++) {
            int id = listUser.get(i).getId();
            if (id == userId) {
                user = listUser.get(i);
            }
        }
        return user;
    }


    public static void deleteUser(int userId) {
        for (int i = 0; i < listUser.size(); i++) {
            int id = listUser.get(i).getId();
            if (id == userId) {
                listUser.remove(i);
                AgendaManagement.listAgendas.remove(i);
            }
        }
    }

    public static void detailUser(User user){
        System.out.println(user.getFirstName() + ' ' + user.getLastName() + " id: " + user.getId() + " AgendaId: " + user.getMonAgenda().getId());
    }
}
