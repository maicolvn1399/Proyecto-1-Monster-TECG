package cr.ac.tec.proyecto1;

public class Card {

    public Card(Value value) {
        this.value = value;
    }

    enum Value {
        Spell1, Spell2, Spell3, Spell4, Spell5, Spell6, Spell7, Spell8, Spell9, Spell10, Esbirro1, Esbirro2, Esbirro3, Esbirro4, Esbirro5, Esbirro6, Esbirro7, Esbirro8, Esbirro9, Esbirro10, Secret1, Secret2, Secret3, Secret4, Secret5, Secret6, Secret7, Secret8, Secret9, Secret10;

        private static final Value[] values = Value.values();
        public static Value getValue(int i){
            return Value.values[i];
        }
    }
    private final Value value;

    public Value getValue() {
        return this.value;
    }

}
