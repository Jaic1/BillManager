package utils;

import com.pagosoft.plaf.PlafOptions;

import javax.swing.*;

import java.awt.*;
import java.io.File;

import static javax.swing.UIManager.*;

public class GUIUtil {
    //private static String imgFolder = "D:\\Idea-workspace\\BillManager\\img";
    private static String imgFolderName = "img";
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
        File file = new File("");
        File imgFolder = new File(file.getAbsolutePath(), imgFolderName);
        ImageIcon i = new ImageIcon(new File(imgFolder.getAbsolutePath(), filename).getAbsolutePath());
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

    //检查是否数，是否空，是否零等
    //传入的参数为JTextField控件和它的名称
    public static boolean checkEmpty(JTextField textField,String name){
        String text = textField.getText().trim();
        if(text.length() == 0){
            JOptionPane.showMessageDialog(null,name+"不能为空");
            textField.grabFocus();
            return false;
        }
        return true;
    }
    public static boolean checkNumber(JTextField textField,String name){
        if(!checkEmpty(textField,name)){
            return false;
        }
        String text = textField.getText().trim();
        try{
            Integer.parseInt(text);
            return true;
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,name+"需要是整数");
            textField.grabFocus();
            return false;
        }
    }
    public static boolean checkZero(JTextField textField,String name){
        if(!checkNumber(textField,name)){
            return false;
        }
        String text = textField.getText().trim();
        if(Integer.parseInt(text) == 0){
            JOptionPane.showMessageDialog(null,name+"输入不能为0");
            textField.grabFocus();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        testComponent(new JButton("testing"),1.0f);
    }
}
