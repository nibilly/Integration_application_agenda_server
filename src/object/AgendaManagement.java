package object;

import java.util.ArrayList;
import java.util.List;

public class AgendaManagement {
    public static List<Agenda> listAgendas = new ArrayList<Agenda>();

    public static void showAgendas(){
        for (int i = 0; i < listAgendas.size(); i++){
            Agenda monAgenda = listAgendas.get(i);
            detailAgenda(monAgenda);
        }
    }

    public static void detailAgenda(Agenda agenda){
        System.out.println(" AgendaId: " + agenda.getId() +  ", Owner firstName:" + agenda.getOwner().getFirstName() + ", Number of events: " + agenda.mesEvents.size());
    }
}
