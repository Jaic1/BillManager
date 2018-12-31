package GUI.panel;

import javax.swing.*;

public abstract class workingPanelAbstractClass extends JPanel {
    //用于在进入该面板时刷新
    //在ToolBarListener和每个相应面板更新时用到
    public abstract void updateData();
    //用于添加监听器
    public abstract void addListener();
}
