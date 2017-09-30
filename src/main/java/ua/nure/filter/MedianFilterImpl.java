package ua.nure.filter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MedianFilterImpl implements MedianFilter {


    @Override
    public void filterImage(BufferedImage image, int start, int limit) {
        Color[] pixel = new Color[9];
        int[] R = new int[9];
        int[] B = new int[9];
        int[] G = new int[9];
        for (int i = 1; i < image.getWidth() - 1; i++) {
            for (int j = start + 1; j < limit -1; j++) {
                pixel[0] = new Color(image.getRGB(i - 1, j - 1));
                pixel[1] = new Color(image.getRGB(i - 1, j));
                pixel[2] = new Color(image.getRGB(i - 1, j + 1));
                pixel[3] = new Color(image.getRGB(i, j + 1));
                pixel[4] = new Color(image.getRGB(i + 1,j + 1));
                pixel[5] = new Color(image.getRGB(i + 1, j));
                pixel[6] = new Color(image.getRGB(i + 1, j - 1));
                pixel[7] = new Color(image.getRGB(i, j - 1));
                pixel[8] = new Color(image.getRGB(i, j));
                IntStream.range(0, pixel.length).forEach(k -> {
                    R[k]=pixel[k].getRed();
                    B[k]=pixel[k].getBlue();
                    G[k]=pixel[k].getGreen();
                });
                Stream.of(R, B, G).forEach(Arrays::sort);
                image.setRGB(i, j, new Color(R[4], B[4], G[4]).getRGB());
            }
        }
    }
}
