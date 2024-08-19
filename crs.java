import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, String[]> recommendations = new HashMap<>();

    public static void main(String[] args) {
        loadDataset();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter soil type (or 'exit' to quit): ");
            String soilType = scanner.nextLine();
            if (soilType.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Enter moisture level (in %): ");
            int moistureLevel = scanner.nextInt();
            scanner.nextLine();

            String condition = getMoistureCondition(moistureLevel);

            String key = soilType + "_" + condition;
            String[] crops = recommendations.getOrDefault(key, new String[]{"No suitable crops found"});

            System.out.println("Recommended Crops:");
            for (String crop : crops) {
                System.out.println(crop);
            }
        }

        scanner.close();
    }

    private static void loadDataset() {
      
        String[] dataset = {
            "Loamy,>50,Wheat;Barley",
            "Sandy,<30,Millets;Maize",
            "Clayey,between 30 and 50,Rice;Sugarcane",
            "Peaty,>60,Potatoes;Carrots",
            "Saline,<20,Onions;Garlic",
            "Silty,between 40 and 60,Beans;Peas",
            "Chalky,>40,Tomatoes;Cucumbers",
            "Loamy,between 30 and 50,Beets;Radishes",
            "Sandy,between 20 and 40,Peanuts;Sunflowers",
            "Clayey,<30,Turnips;Cabbage",
            "Silty,>50,Spinach;Lettuce"
        };

        for (String line : dataset) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String soilType = parts[0];
                String condition = parts[1];
                String[] crops = parts[2].split(";");

                String key = soilType + "_" + condition;
                recommendations.put(key, crops);
            }
        }
    }

    private static String getMoistureCondition(int moistureLevel) {
        if (moistureLevel > 60) return ">60";
        if (moistureLevel < 20) return "<20";
        if (moistureLevel > 50) return ">50";
        if (moistureLevel < 30) return "<30";
        return "between 30 and 50";
    }
}
