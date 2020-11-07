package cr.ac.tec.proyecto1;

import cr.ac.tec.proyecto1.linearStructures.BattleLog;

public class Listener implements Observer {
    private String name;
    private PlayerGUI playergui = new PlayerGUI();
    BattleLog history = new BattleLog();

    @Override
    public void update(String m) {
        System.out.println("Blablablablal");
        history.push(m);
        history.printlist(history.head);
        PlayerGUI.historial.appendText("\n" + history.getData(history.head));
        //agregar evento a lista y hacer que se muestre en el Gui
    }

    @Override
    public void listen(PlayerGUI pg) {
        playergui = pg;
    }
}
