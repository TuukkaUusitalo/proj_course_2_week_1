import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        System.setProperty("file.encoding", "UTF-8");
        Scanner sc = new Scanner(System.in);

        System.out.println("Select language / Valitse kieli / Välj språk:");
        System.out.println("1 = English");
        System.out.println("2 = Finnish");
        System.out.println("3 = Swedish");
        System.out.print("> ");

        int lang = sc.nextInt();
        sc.nextLine();

        Locale locale = switch (lang) {
            case 2 -> new Locale("fi");
            case 3 -> new Locale("sv");
            default -> Locale.ENGLISH;
        };

        ResourceBundle msg = ResourceBundle.getBundle("messages", locale);

        System.out.println(msg.getString("enter_items"));
        int itemCount = sc.nextInt();

        double[] prices = new double[itemCount];
        int[] quantities = new int[itemCount];

        for (int i = 0; i < itemCount; i++) {
            System.out.println(msg.getString("enter_price") + " #" + (i + 1));
            prices[i] = sc.nextDouble();

            System.out.println(msg.getString("enter_quantity") + " #" + (i + 1));
            quantities[i] = sc.nextInt();
        }

        // --- Siirretään laskenta ShoppingCart-luokalle ---
        ShoppingCart cart = new ShoppingCart();
        double total = cart.calculateTotal(prices, quantities);

        System.out.println(msg.getString("total_cost") + ": " + total);
    }
}