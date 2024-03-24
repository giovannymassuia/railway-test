package io.giovannymassuia.railwaytest;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping
    public ResponseEntity<?> get() {
        Runtime runtime = Runtime.getRuntime();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long currentMemory = allocatedMemory - freeMemory;

        int cores = runtime.availableProcessors();

        return ResponseEntity.ok(Map.of(
            "memory", Map.of(
                "allocated.mb", allocatedMemory / 1024 / 1024,
                "free.mb", freeMemory / 1024 / 1024,
                "current.mb", currentMemory / 1024 / 1024
            ),
            "cores", cores
        ));
    }

    @PostMapping("/cpuIntensive")
    public ResponseEntity<?> superCpuIntensive() {
        long n = 50;
        long fib = fibonacci(n);
        return ResponseEntity.ok(Map.of("fibonacci", fib));
    }

    private long fibonacci(long n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @PostMapping("/memoryIntensive")
    public ResponseEntity<?> superMemoryIntensive() {
        long[][] matrix = new long[10000][10000];
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                matrix[i][j] = i * j;
            }
        }
        return ResponseEntity.ok(Map.of("matrix", matrix));
    }

    @PostMapping("/lightMemoryIntensive")
    public ResponseEntity<?> lightMemoryIntensive() {
        long[][] matrix = new long[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                matrix[i][j] = i * j;
            }
        }
        return ResponseEntity.ok(Map.of("matrix", matrix));
    }

}
