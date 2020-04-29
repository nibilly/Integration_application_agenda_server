package object;

public class Event {
    // POUR OBTENIR LE BON FORMAT DE DATE EXEMPLE
    /*
    SimpleDateFormat formater = null;
    formater = new SimpleDateFormat("dd-MM-yyyy hh'H'mm");
    Date aujourdhui = new Date();
    System.out.println(formater.format(aujourdhui));

    CES 4 LIGNES DE CODES AFFICHENT 28-04-2020 19H00
    CE QUI CORRESPOND AU BON FORMAT.
     */

    public static int compteurId;
    String startDate;       // Exemple  28-04-2020 17H35
    String finishDate;      // 28-04-2020 19H35
    String jourDate;        // 28-04-2020
    String heurDebut;       // 17H35
    String heurFin;         // 19H35
    String name;
    int id;

    public Event(String startDate, String finishDate, String jourDate, String heurDebut, String heurFin, String name) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.jourDate = jourDate;
        this.heurDebut = heurDebut;
        this.heurFin = heurFin;
        this.name = name;
        this.id = compteurId;
        compteurId++;
    }

    public void showDetails(){
        System.out.println(name + "(id:" + id + "): Commence le " + startDate + " et fini le " + finishDate);
    }

    public Event(){}

    public String getJourDate() {
        return jourDate;
    }

    public void setJourDate(String jourDate) {
        this.jourDate = jourDate;
    }

    public String getHeureDebut() {
        return heurDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heurDebut = heureDebut;
    }

    public String getHeureFin() {
        return heurFin;
    }

    public void setHeureFin(String heureFin) {
        this.heurFin = heureFin;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
