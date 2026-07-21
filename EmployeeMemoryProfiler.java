import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class EmployeeMemoryProfiler {

    // Intentional Memory Leak
    private static final List<Employee> leakyCache = new ArrayList<>();

    // Bounded Cache (LRU Cache)
    private final Map<Integer, Employee> boundedCache =
            new LinkedHashMap<Integer, Employee>(16, 0.75f, true) {

                @Override
                protected boolean removeEldestEntry(
                        Map.Entry<Integer, Employee> eldest) {
                    return size() > 1000;
                }
            };

    // Memory Leak
    public void addWithLeak(Employee employee) {
        leakyCache.add(employee);
    }

    // Optimized Version
    public void addOptimized(Employee employee) {
        boundedCache.put(employee.getId(), employee);
    }

    // Print JVM Heap Usage
    private static void printHeapUsage() {
        Runtime runtime = Runtime.getRuntime();

        long used =
                runtime.totalMemory() - runtime.freeMemory();

        long total = runtime.totalMemory();

        long max = runtime.maxMemory();

        System.out.printf(
                "Used Heap : %d MB | Total Heap : %d MB | Max Heap : %d MB%n",
                used / 1024 / 1024,
                total / 1024 / 1024,
                max / 1024 / 1024
        );
    }

    public static void main(String[] args)
            throws IOException, InterruptedException {

        EmployeeMemoryProfiler profiler =
                new EmployeeMemoryProfiler();

        System.out.println("======================================");
        System.out.println("Employee Memory Profiling Demo");
        System.out.println("======================================");
        System.out.println("1. Start JVisualVM");
        System.out.println("2. Attach this Java process");
        System.out.println("3. Open the Monitor tab");
        System.out.println("4. Watch the Heap graph");
        System.out.println();
        System.out.println("Press ENTER to start...");
        System.in.read();

        //----------------------------------------------------------
        // MEMORY LEAK DEMO
        //----------------------------------------------------------

        System.out.println("\n===== MEMORY LEAK DEMO =====");

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 50000; i++) {

            Employee employee =
                    new Employee(
                            i,
                            "Employee-" + i,
                            "IT",
                            30000 + i
                    );

            profiler.addWithLeak(employee);

            if (i % 10000 == 0) {
                System.out.println("\nEmployees Added : " + i);
                printHeapUsage();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("\nCompleted in "
                + (end - start) + " ms");

        System.out.println("Leaky Cache Size : "
                + leakyCache.size());

        printHeapUsage();

        System.out.println("\nObserve VisualVM:");
        System.out.println("Heap continuously increases.");
        System.out.println("Garbage Collector cannot reclaim memory.");

        System.out.println("\nPress ENTER to clear cache...");
        System.in.read();

        //----------------------------------------------------------
        // REMOVE REFERENCES
        //----------------------------------------------------------

        leakyCache.clear();

        System.gc();

        Thread.sleep(3000);

        System.out.println("\nCache Cleared");

        printHeapUsage();

        System.out.println("Observe VisualVM after GC.");

        System.out.println("\nPress ENTER for optimized demo...");
        System.in.read();

        //----------------------------------------------------------
        // OPTIMIZED VERSION
        //----------------------------------------------------------

        System.out.println("\n===== OPTIMIZED CACHE DEMO =====");

        start = System.currentTimeMillis();

        for (int i = 1; i <= 50000; i++) {

            Employee employee =
                    new Employee(
                            i,
                            "Employee-" + i,
                            "IT",
                            30000 + i
                    );

            profiler.addOptimized(employee);

            if (i % 10000 == 0) {
                System.out.println("\nEmployees Added : " + i);
                System.out.println("Cache Size : "
                        + profiler.boundedCache.size());

                printHeapUsage();
            }
        }

        end = System.currentTimeMillis();

        System.out.println("\nCompleted in "
                + (end - start) + " ms");

        System.out.println("Final Cache Size : "
                + profiler.boundedCache.size());

        printHeapUsage();

        System.out.println("\nObserve VisualVM:");
        System.out.println("Heap remains stable.");
        System.out.println("Old employees are automatically removed.");

        System.out.println("\nPress ENTER to exit...");
        System.in.read();
    }
}

class Employee {

    private final int id;
    private final String name;
    private final String department;
    private final int salary;

    // Simulate memory usage (~5 KB)
    private final byte[] data = new byte[5000];

    public Employee(
            int id,
            String name,
            String department,
            int salary) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }
}