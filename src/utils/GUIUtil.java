package utils;

import com.pagosoft.plaf.PlafOptions;

import javax.swing.*;

import java.awt.*;
import java.io.File;

import static javax.swing.UIManager.*;

public class GUIUtil {
    //
    private static String imgFolder = "D:\\Idea-workspace\\BillManager\\img";
    private static String LNF = "com.pagosoft.plaf.PgsLookAndFeel";
    //需要添加到配置表中

    public static void useLNF(){
        try {
            setLookAndFeel(LNF);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void changeLNF(JFrame frame){

    }

    public static void testComponent(JComponent component,float stretch){
        useLNF();
        JFrame f = new JFrame("testing");
        f.setSize(600,550);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerPanel cp = new centerPanel(stretch);
        f.setContentPane(cp);
        f.setVisible(true);
        cp.show(component);
    }

    public static void setImgIcon(JButton b,String filename,String tip){
        ImageIcon i = new ImageIcon(new File(imgFolder, filename).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61,81));
        b.setToolTipText(tip);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setText(tip);
    }

    public static void setColor(Color color,JComponent... components){
        for (JComponent component:components){
            component.setForeground(color);
        }
    }

    public static void main(String[] args) {
        testComponent(new JButton("testing"),1.0f);
    }
}
