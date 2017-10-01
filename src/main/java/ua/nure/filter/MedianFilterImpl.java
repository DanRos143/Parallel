package ua.nure.filter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MedianFilterImpl implements MedianFilter {


    @Override
    public void filterImage(BufferedImage image, int start, int limit) {
        int[] pixel = new int[9];
        int[] R = new int[9];
        int[] B = new int[9];
        int[] G = new int[9];
        for (int i = 1; i < image.getWidth() - 1; i++) {
            for (int j = start + 1; j < limit -1; j++) {
                pixel[0] = image.getRGB(i - 1, j - 1);
                pixel[1] = image.getRGB(i - 1, j);
                pixel[2] = image.getRGB(i - 1, j + 1);
                pixel[3] = image.getRGB(i, j + 1);
                pixel[4] = image.getRGB(i + 1,j + 1);
                pixel[5] = image.getRGB(i + 1, j);
                pixel[6] = image.getRGB(i + 1, j - 1);
                pixel[7] = image.getRGB(i, j - 1);
                pixel[8] = image.getRGB(i, j);
                for (int k = 0; k < pixel.length; k++) {
                    R[k] = (pixel[k] >> 16) & 0xFF;
                    G[k] = (pixel[k] >> 8) & 0xFF;
                    B[k] = (pixel[k] >> 0) & 0xFF;
                }
                Stream.of(R, B, G).forEach(Arrays::sort);
                image.setRGB(i, j, new Color(R[4], B[4], G[4]).getRGB());
            }
        }
    }
}
