package utils;

import java.awt.*;

public class colorUtil {
    //
    public static Color blueColor = Color.decode("#0000FF");
    public static Color geryColor = Color.decode("#9370DB");
    public static Color fontColor = Color.decode("#00CED1");
    public static Color sampleColor = Color.decode("#FFE4C4");
    public static Color backgroundColor = Color.decode("#E6E6FA");
    public static Color backgroundPanelColor = Color.decode("#E3E3E3");
    public static Color warningColor = Color.decode("#8B0000");

    //渐变色http://tool.sccnn.com/ys/ColorBlender.htm
    public static Color getByProgress(int progress){
        if(progress > 100)progress = 100;
        int r = 255;
        int g = 153;
        int b = 204;
        int rDelta = 0;
        int gDelta = -153;
        int bDelta = -204;
        float rate = progress / 100f;
        r += (int)(rDelta * rate);
        g += (int)(gDelta * rate);
        b += (int)(bDelta * rate);
        return new Color(r,g,b);
    }
    public static Color getByProgressExceed(int progress){
        progress -= 100;
        int r = 255;
        int g = 0;
        int b = 0;
        int rDelta = -102;
        int gDelta = 0;
        int bDelta = 0;
        float rate = progress / 100f;
        r += (int)(rDelta * rate);
        g += (int)(gDelta * rate);
        b += (int)(bDelta * rate);
        return new Color(r,g,b);
    }
}
