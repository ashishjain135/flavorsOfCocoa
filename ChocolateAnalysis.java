import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChocolateAnalysis {

    public static void main(String[] args) {
        countTotalReviews();
        findTopCompanies();
    }

    // task 1: Count total chocolate reviews
    public static void countTotalReviews() {
        String filePath = "flavors_of_cocoa.csv";
        int rowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    rowCount++;
                }
            }

            System.out.println("\n✅ Total chocolate reviews: " + rowCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // task 2: Find top companies by chocolate count
    public static void findTopCompanies() {
        String filePath = "flavors_of_cocoa.csv";
        Map<String, Integer> companyCountMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);  // CSV-safe split
                if (values.length > 1) {
                    String company = values[1].trim();
                    companyCountMap.put(company, companyCountMap.getOrDefault(company, 0) + 1);
                }
            }

            // Sort by value descending
            List<Map.Entry<String, Integer>> sortedCompanies = new ArrayList<>(companyCountMap.entrySet());
            sortedCompanies.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            System.out.println("\n🏆 Top Companies by Chocolate Count:");
            for (int i = 0; i < Math.min(10, sortedCompanies.size()); i++) {
                Map.Entry<String, Integer> entry = sortedCompanies.get(i);
                System.out.println((i + 1) + ". " + entry.getKey() + " - " + entry.getValue() + " chocolates");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

