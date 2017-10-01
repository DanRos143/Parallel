package ua.nure.filter;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelImageProcessor {

    private final static int CORES = 4;

    private ExecutorService executor;

    public ParallelImageProcessor() {
        this.executor = Executors.newFixedThreadPool(CORES);
    }

    public void shutDownProcessor() {
        executor.shutdown();
    }

    public List<Future> processParallel(BufferedImage image) throws ExecutionException, InterruptedException {
        int height = image.getHeight();

        int partSize = height/CORES;

        return IntStream.range(1, CORES + 1)
                .boxed()
                .map(value -> {
                    if (value < CORES) {
                        return executor.submit(() -> new MedianFilterImpl()
                                .filterImage(image, partSize * (value - 1), partSize * (value) + 1));
                    } else {
                        return executor.submit(() -> new MedianFilterImpl()
                                .filterImage(image, partSize * (value - 1), image.getHeight()));
                    }
                })
                .collect(Collectors.toList());

    }
}
