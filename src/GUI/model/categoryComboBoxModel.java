package GUI.model;

import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class categoryComboBoxModel implements ComboBoxModel<String> {
    //
    public List<String> combos = new ArrayList<>();
    public String c;

    public categoryComboBoxModel(){
        combos.add("吃饭");
        combos.add("购物");
        c = combos.get(0);
    }

    @Override
    public void setSelectedItem(Object anItem) {

    }

    @Override
    public Object getSelectedItem() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getElementAt(int index) {
        return null;
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
