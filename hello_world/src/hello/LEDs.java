package hello;

import static java.lang.Math.floor;
import static java.lang.Math.min;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Arrays;

public class LEDs {
    int[] LedData = new int[100];

    public LEDs() {}

    public static void print(String s) {System.out.println(s);}

    public void run() {
        for (int i=0;i<12;i++) {
            if (i % 2 == 1) {
                setRGBW(i, 0, 1, 255, 3);
            } else {
                setRGBW(i, 0, 255, 2, 3);
            }
        }
        // setLED(0, 0, 255, 0);
        // setLED(1, 0, 0, 255);
        // setLED(2, 0, 0, 0);
        // setLED(3, 255, 0, 0);

        // setRGBW(0, 1, 255, 2, 3);
        // setRGBW(1, 1, 255, 2, 3);
        // setRGBW(2, 1, 255, 2, 3);

        System.out.println(Arrays.toString(LedData));
    }

    public void setLED(int index, int R, int G, int B) {
        LedData[index*3] = R;
        LedData[(index*3)+1] = G;
        LedData[(index*3)+2] = B;
    }
    public void setRGBW(int index, int R, int G, int B, int W) {
        int offsetindex = index + (int) floor(index/3); // INDEX OFFSET
        int trueindex = offsetindex*3;
        int nextTindex = (offsetindex+1)*3;

        print(offsetindex + "");
        print(index % 3 + "");

        switch (index % 3) {
            case 0:
                setLED(offsetindex, R, G, B);
                setLED(offsetindex+1, W, LedData[nextTindex], LedData[nextTindex+1]); // Wi, Ri+1, Bi+1
                break;
            case 1:
                setLED(offsetindex, LedData[trueindex], R, G);
                setLED(offsetindex+1, B, W, LedData[nextTindex]);
                break;
            case 2:
                setLED(offsetindex, LedData[trueindex],LedData[trueindex+1],R);
                setLED(offsetindex+1, G, B, W);
                break;
            default:break;
        }
    }

    public void render(Graphics g) {
        for (int i=0;i<LedData.length/4;i+=1) {
            int W = LedData[(i*4)+3];
            Color RGBW = new Color(
                min(LedData[(i*4)]+W,255),
                min(LedData[(i*4)+1]+W,255),
                min(LedData[(i*4)+2]+W,255));
            g.setColor(RGBW);
            
            g.fillRect(i*20,0,20,20);
        }
    }
}
