package ua.nure.filter;

import java.awt.image.BufferedImage;

public interface MedianFilter {

    void filterImage(BufferedImage image, int start, int limit);
}
