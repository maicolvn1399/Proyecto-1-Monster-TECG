package cr.ac.tec.proyecto1;

public class Card extends Object {


    public int id;
    public String category;
    public int damage;
    public int cost;
    public String action;
    public String image;

    /**
     * Card class that allows to create the 40 cards for the game, gets all the attributes from a JSON file and transforms it into an object
     */

    public void enlistCard(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("New card created, these are the attributes:");
        System.out.println(this.id);
        System.out.println(this.category);
        System.out.println(this.damage);
        System.out.println(this.cost);
        System.out.println(this.action);
        System.out.println(this.image);
        System.out.println("--------------------------------------------------------------------------------");
    }

    public String getCardInfo(){
        String cardInfo = this.getId() + "\n" + this.getCategory() + "\n"+ this.getDamage() + "\n"
                +  this.getCost() + "\n" + this.getAction() + "\n" + this.getImage();

        return cardInfo;
    }


    /**
     * getters and setters for each attribute of card class
     * @return attibutes from the cards
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
