package com.util;

import com.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CountService {

    public static int countByModel(List<Car> cars, String model) {

        int threads = 4;
        int chunkSize = cars.size() / threads;

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int t = 0; t < threads; t++) {

            int startIndex = t * chunkSize;
            int endIndex = (t == threads - 1)
                    ? cars.size()
                    : startIndex + chunkSize;

            Callable<Integer> task = () -> {
                int count = 0;

                for (int i = startIndex; i < endIndex; i++) {
                    if (cars.get(i).getModel().equals(model)) {
                        count++;
                    }
                }

                return count;
            };

            futures.add(executor.submit(task));
        }

        int result = 0;

        for (Future<Integer> f : futures) {
            try {
                result += f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
        return result;
    }
}