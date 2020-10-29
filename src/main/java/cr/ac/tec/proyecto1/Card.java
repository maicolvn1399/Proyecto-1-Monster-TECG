package cr.ac.tec.proyecto1;

public class Card {

    public int id;
    public String category;
    public int damage;
    public int cost;
    public String action;
    public String image;


    public void enlistCard(){
        System.out.println("New card created, these are the attributes:");
        System.out.println(this.id);
        System.out.println(this.category);
        System.out.println(this.damage);
        System.out.println(this.cost);
        System.out.println(this.action);
        System.out.println(this.image);
    }





}
