import ua.nure.filter.ParallelImageProcessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Future;

class Runner {

    public static void main(String[] a)throws Throwable{
        Instant starts = Instant.now();

        File f = new File("./photo.jpeg");
        File output = new File("./output.jpeg");
        BufferedImage image = ImageIO.read(f);
        ParallelImageProcessor parallelImageProcessor = new ParallelImageProcessor();
        List<Future> futures = parallelImageProcessor.processParallel(image);
        for (Future future : futures) {
            future.get();
        }
//        MedianFilter filter = new MedianFilterImpl();
//        filter.filterImage(image, 0, image.getHeight());

        ImageIO.write(image, "jpg", output);

        Instant ends = Instant.now();
        System.out.println(Duration.between(starts, ends));

        parallelImageProcessor.shutDownProcessor();
    }
}

