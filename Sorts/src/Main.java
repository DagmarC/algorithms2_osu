import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int ARR_LENGTH = 20;
    public static int ASTROUNAUTS_FIELDS_COUNT = 5;

    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Product> products = generateProducts();
        sortByPriceInsertion(products);
        printProducts(products);

        System.out.println("------- SORT PRODUCTS BY PRICE -------");

        // ------- SORT PRODUCTS BY PRICE -------
        sortByPriceInsertion(products);
        printProducts(products);

        // ------- SORT PRODUCTS BY NAME -------
        System.out.println("------- SORT PRODUCTS BY NAME -------");
        sortByName(products, 0, products.size() - 1);
        printProducts(products);

        // LINKED LIST
        System.out.println("------- Linked LIST -------");

        LinkedList list = new LinkedList();

        // 1. Load Data
        Product p1 = new Product("Laptop", Category.ELECTRONICS, 1200.00);
        Product p2 = new Product("Aspirin", Category.DRUGS, 5.50);
        Product p3 = new Product("Burger", Category.MEAL, 10.00);

        list.addLast(p1);
        list.addLast(p2);
        list.addLast(p3);

        // 2. Algorithm: Search by Name (using Predicate)
        System.out.println("Searching for 'Aspirin'...");
        Product found = list.search(p -> p.getName().equals("Aspirin"));

        if (found != null) {
            System.out.println("Found: " + found);
            // Output: Found: Aspirin (DRUGS) - $5.50
        }

        // 3. Algorithm: Search by Price Filter
        System.out.println("Searching for item > $1000...");
        Product expensive = list.search(p -> p.getPrice() > 1000);
        System.out.println("Found: " + expensive);

        // 4. Algorithm: Remove
        list.remove(p2); // Removes Aspirin
        list.printList();

        // ------ STACK------
        System.out.println("========STACK=======");
        StackGenerics<Product> productStack = new StackGenerics<>();

        // 1. Push products (LIFO - Last In, First Out)
        productStack.push(new Product("p1", getRandomCategory(), 1.50));
        productStack.push(new Product("p2",  getRandomCategory(), 2.00));
        productStack.push(new Product("p3",  getRandomCategory(), 3.50));

        // 2. Peek (See what's on top)
        System.out.println("Top item: " + productStack.peek()); // Output: Chocolate

        // 3. Pop (Remove from top)
        System.out.println("Popped: " + productStack.pop());    // Output: Chocolate
        System.out.println("Popped: " + productStack.pop());    // Output: Chips

        // 4. Check remaining
        System.out.println("Stack size: " + productStack.size()); // Output: 1

        // QUEUE
        System.out.println("========QUEUE=========");
        Queue<Product> shopQueue = new Queue<>();

        shopQueue.enqueue(new Product("p1", getRandomCategory(), 1.20));
        shopQueue.enqueue(new Product("p2", getRandomCategory(), 0.80));

        System.out.println("Serving: " + shopQueue.dequeue()); // Milk

        // Works with Strings too (because it's Generic)
        Queue<String> messages = new Queue<>();
        messages.enqueue("Hello");
        messages.enqueue("World");

        System.out.println("Message: " + messages.dequeue()); // Hello

        // CSV LOADER
        System.out.println("========LOAD CSV=========");

        String csvFile = "astronauts_final.csv";
        String delimiter = ";";

        try {
            System.out.println("------- ASTRONAUTS-------");

            List<Astronaut> astronauts = AstronautLoader.loadAstronauts(csvFile, delimiter);
            astronauts.stream().limit(5).forEach(System.out::println);


            // CUSTOM SORTER OF OBJECTS
            QuickSort<Astronaut> sorter = new QuickSort<>();

            // Sort Females by Space Flight

            // 1. Filter female astronauts
            List<Astronaut> femaleAstronauts = astronauts.stream()
                    .filter(a -> "Female".equalsIgnoreCase(a.getGender()))
                    .collect(Collectors.toList());

            // 2. Custom quicksort
            System.out.println("------- BY_FLIGHTS_HRS_DESC-------");

            sorter.sort(femaleAstronauts, AstronautSorters.BY_FLIGHTS_HRS_DESC);
            femaleAstronauts.forEach(a -> System.out.println(a.getName() + " - " + a.getFlightHours()));

            System.out.println("------- MAX NUMBER OF FLIGHTS-------");

            // Find Astronaut with MAC number of flights
            List<Astronaut> astronautsMaxFlights = getAstronautsMaxFlights(astronauts);

            // sort by number of flight hrs
            sorter.sort(astronautsMaxFlights, AstronautSorters.BY_FLIGHTS_HRS);
            astronautsMaxFlights.forEach(a -> System.out.println(a.getName() + " - " + a.getSpaceFlights()+ " - " + a.getFlightHours()));

            System.out.println("------- OLDEST YEAR-------");

            // Find Astronaut with Oldest Year number of flights
            List<Astronaut> astronautsOldest = getOldestFlightYear(astronauts);

            // sort by number of flight hrs
            sorter.sort(astronautsOldest, AstronautSorters.BY_FLIGHTS_HRS);
            astronautsOldest.forEach(a -> System.out.println(a.getName() + " - " + a.getYear()+ " - " + a.getFlightHours()));


        } catch (IOException e) {
            System.err.println("Error loading csv file: " + e.getMessage());
        }

    }

    private static List<Astronaut> getAstronautsMaxFlights(List<Astronaut> astronauts) {
        if ( astronauts == null || astronauts.isEmpty()) {
            return new ArrayList<>();
        }

        int maxFlights = 0;
        for (Astronaut a : astronauts) {
            if (a.getSpaceFlights() > maxFlights) {
                maxFlights = a.getSpaceFlights();
            }
        }

        // Need 'final' or 'effectively final' var for lambda expression
        int finalMaxFlights = maxFlights;
        return astronauts.stream()
                .filter(astronaut -> astronaut.getSpaceFlights() == finalMaxFlights)
                .collect(Collectors.toList());
    }

    private static List<Astronaut> getOldestFlightYear(List<Astronaut> astronauts) {
        if ( astronauts == null || astronauts.isEmpty()) {
            return new ArrayList<>();
        }

        int oldestYear = astronauts.getFirst().getYear();
        for (Astronaut a : astronauts) {
            if (a.getYear() < oldestYear) {
                oldestYear = a.getYear();
            }
        }

        // Need 'final' or 'effectively final' var for lambda expression
        int finalOldestYear = oldestYear;
        return astronauts.stream()
                .filter(astronaut -> astronaut.getYear() == finalOldestYear)
                .collect(Collectors.toList());
    }



    public static ArrayList<Product> generateProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Random rand = new Random();

        String[] adjectives = {"Super", "Ultra", "Eco", "Smart", "Budget", "Luxury", "Mega", "iProduct"};
        String[] nouns = {"Phone", "Laptop", "Fridge", "Table", "Chair", "Bottle", "Watch", "TV", "Kebab"};

        for(int i = 0; i < ARR_LENGTH; i++) {

            String randomName = adjectives[rand.nextInt(adjectives.length)] + " " +
                    nouns[rand.nextInt(nouns.length)] + " " +
                    rand.nextInt(100);

            double randPrice = 1.0 + (10000.0 - 1.0) * rand.nextDouble();

            Category randCategory = getRandomCategory();

            Product p = new Product(randomName, randCategory, randPrice);

            products.add(p);
        }
        return products;
    }


    private static void printProducts(ArrayList<Product> products) {
        products.forEach(System.out::println);
    }

    private static Category getRandomCategory() {
        Random rand = new Random();
        return Category.values()[rand.nextInt(Category.values().length)];
    }

    //  Insertion sort
    public static void sortByPriceInsertion(ArrayList<Product> products) {
        for(int i = 1; i < products.size() - 1; i++) {
            int j = i + 1;
            Product temp = products.get(j);

            int k = i;
            while(k >= 0 && products.get(k).getPrice() > temp.getPrice()) {
                products.set(k + 1, products.get(k)); // Copy element from k-th position to k+1 -> shift right
                k--;
            }
            products.set(k + 1, temp); // insert the product to the sorted array
        }
    }

    //  Selection sort
    public static void sortByPriceSelection(ArrayList<Product> products) {
          for(int i = 0; i < products.size() - 1; i++) {
              Product minProduct = products.get(i+1);
              int minIndex = i+1;

              // Look for the minimum from i+1 - n
              for(int j = i + 1; j < products.size(); j++) {
                  if(products.get(j).getPrice() < minProduct.getPrice()) {
                      minProduct = products.get(j);
                      minIndex = j;
                  }
              }
              if(products.get(i).getPrice() > minProduct.getPrice()) {
                  Product temp = products.get(i);
                  products.set(i, minProduct);
                  products.set(minIndex, temp);
              }
          }
    }

    // Quick Sort
    public static void sortByName(ArrayList<Product> products, int start, int end) {
        if (start >= end) {
            return; // base case
        }

        // Partition
        int pivotIdx = quicksort(products, start, end);

        // Recursively call on left and right side
        sortByName(products,  start, pivotIdx - 1);
        sortByName(products, pivotIdx + 1, end);
    }

    private static int quicksort(ArrayList<Product> products, int start, int end) {

        Product pivotProduct = products.get(end);

        int i = start - 1;
        for (int j = start; j < end; j++) {
            Product currentProduct = products.get(j);

            int nameCompare = currentProduct.getName().compareToIgnoreCase(pivotProduct.getName());

            if(nameCompare < 0) {
                i++;
                // swap element at i position with element at jth position
                swap(products, i, j);
            }
        }
        i++;
        swap(products, i, end);
        return i;
    }

    private static void swap(ArrayList<Product> list, int i, int j) {
        Product temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}