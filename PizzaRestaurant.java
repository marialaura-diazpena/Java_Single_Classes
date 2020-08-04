
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date; 
/**
 * Yoshi's Pizza Restaurant - I have to develop a program for Yoshi's Pizza
 * restaurant. This program will calculate and display the cost of a 
 * customer's order including GST and deposit. Each customer may order any 
 * number of each type of food and beverage.The restaurant needs to track an 
 * display the total revenue for the day, the total of each type of sale and 
 * the total number of customers for the day. The restaurant's name and 
 * today's date must be displayed at top of the output. The program will 
 * conclude with a statement.
 * 
 * Inputs:
 *      Item of Choice
 *      Size of Pizza (in case of choosing pizza)
 *      Size of Cola (in case of choosing cola)
 *      Quantity of Items of Choice
 * 
 * Processing: Calculations:
 *      GST ($)
 *      Total w/o GST
 *      Total w/ GST
 *      Deposit For Small Bottles
 *      Deposit for Large Bottles
 * 
 * Outputs
 *      Greeting Message and Today's Date
 *      Total Cost of Current Order 
 *      Total Bill with GST by Current Customer
 *      Daily Summary of Quantity of Items Sold
 *      Total Revenue for the Day
 *      Total Number of Customers
 * 
 * @Author Maria Laura Diaz Pena 
 * @02/25/2020
 */
public class PizzaRestaurant {
    static boolean ordering = true;

    public static void main (String [] args) 

    {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");  

        Scanner input = new Scanner (System.in);
        //Constants of each item in the menu
        final double GST = 0.05;
        final double PIZZA_S = 12.75;
        final double PIZZA_L = 18.75;
        final double SIDE_SALAD =  7.25;
        final double COLA_1 = 1.75;
        final double COLA_6 = 8.00;
        final double COLA_2L = 3.75;
        final double SB_DEPOSIT = 0.10;
        final double LB_DEPOSIT = 0.25;
        //Variables that I will call later on the program
        double subTotal = 0;
        double subTotalOverall = 0;
        int customerCount = 0;
        int pizzaSQuantity = 0;
        int pizzaSQuantityOverall = 0;
        int pizzaLQuantity = 0;
        int pizzaLQuantityOverall = 0;
        int saladsQuantity = 0;
        int saladsQuantityOverall = 0;
        int cola1Quantity = 0;
        int cola1QuantityOverall = 0;
        int cola6Quantity = 0;
        int cola6QuantityOverall = 0;
        int cola2LQuantity = 0;
        int cola2LQuantityOverall = 0;

        //Display greeting message with date
        Date date = new Date();
        String starDivider ="*******************************************";
        System.out.println(starDivider);
        System.out.println("*** Welcome to Yoshi's Pizza Restaurant ***");
        System.out.println("***     Today's date is "+formatter.format(date)+"     ***");
        System.out.println(starDivider);

        
        //start with the do loop 
        do{
            System.out.println("");
            String divider ="-----------------------------";
            System.out.println(divider);
            System.out.printf("%s %10s %13s\n", "Item", "Size", "Price");
            System.out.println(divider);
            System.out.printf("%s %13s %4s%.5s\n", "Pizza", "Small   ",  "$",PIZZA_S);
            System.out.printf("%s %13s %4s%.5s\n", "Pizza", "Large   ",  "$",PIZZA_L);
            System.out.printf("%s %14s %5s%.5s\n", "Side", "Salad   ",  "$",SIDE_SALAD);
            System.out.printf("%s %14s %5s%.5s\n", "Cola", "1 can   ",  "$",COLA_1);
            System.out.printf("%s %12s %7s%.5s0\n", "Cola", "6-pack", "$",COLA_6);
            System.out.printf("%s %9s %10s%.5s\n", "Cola", "2 L", "$", COLA_2L);
            System.out.println(divider);

            
            //ask for user input on item choice
            System.out.print("Enter item choice (P - Pizza, S - Sides, C - Cola, or X - exit): ");
            char choice = input.next().charAt(0);
            choice = Character.toLowerCase(choice);
            
            //switch statement casing each item choice 
            switch(choice)
            {
                case 'p':   
                System.out.print("Enter size of pizza (s or l): ");
                char pizzaSize = input.next().charAt(0);
                pizzaSize = Character.toLowerCase(pizzaSize);
                if (pizzaSize == 's'){
                    System.out.print("Enter number of small pizzas to order: ");
                    int smallPizza = input.nextInt();
                    pizzaSQuantity += smallPizza;
                    pizzaSQuantityOverall += smallPizza;
                    //calculating the GST
                    double gstSPizza = pizzaSQuantity * PIZZA_S * GST;
                    double sPizzaCost = pizzaSQuantity * PIZZA_S + gstSPizza;
                    
                    System.out.printf("%s %.2f\n","Pizza cost is (including GST): $" , sPizzaCost);
                    subTotal += sPizzaCost;
                    subTotalOverall += sPizzaCost;
                }
                if (pizzaSize == 'l'){
                    System.out.print("Enter number of large pizzas to order: ");
                    int largePizza = input.nextInt();
                    pizzaLQuantity += largePizza;
                    pizzaLQuantityOverall += largePizza;
                    //calculating GST
                    double gstLPizza = pizzaLQuantity * PIZZA_L * GST;
                    //calculating the cost
                    double lPizzaCost = pizzaLQuantity * PIZZA_L + gstLPizza;
                    
                    System.out.printf("%s %.2f\n","Pizza cost is (including GST): $" , lPizzaCost);
                    subTotal += lPizzaCost;
                    subTotalOverall += lPizzaCost;
                }
                break;

                case 's':
                System.out.print("Enter number of salads to order: ");
                int sideSalads = input.nextInt();
                saladsQuantity += sideSalads;
                saladsQuantityOverall += sideSalads;
                //calculating the GST
                double gstSalad = saladsQuantity * SIDE_SALAD * GST;
                //calculating the cost
                double saladCost = saladsQuantity * SIDE_SALAD + gstSalad;
                System.out.printf("%s %.2f\n","Side cost is (including GST): $" , saladCost);
                subTotal += saladCost;
                subTotalOverall += saladCost;
                break;

                case 'c':
                System.out.print("Enter size of cola (one, six or two): ");
                String colaSize = input.next().toLowerCase();
                
                if (colaSize.equals("one")){
                    System.out.print("Enter the number of single colas to order: ");
                    int cola1Count = input.nextInt();
                    cola1Quantity += cola1Count;
                    cola1QuantityOverall += cola1Count;
                    //calculating the deposit before the GST
                    double cola1Deposit = SB_DEPOSIT * cola1Count;
                    //calculating the cost without GST
                    double cola1CostNoGst = ((COLA_1 * cola1Count) + cola1Deposit);
                    //calculating the GST
                    double gstCola1 = cola1CostNoGst * GST;
                    //calculating the cost with GST
                    double cola1Cost = cola1CostNoGst + gstCola1;
                    System.out.printf("%s %.2f\n","Cola cost is (including GST and deposit); $" , cola1Cost);
                    subTotal += cola1Cost;
                    subTotalOverall += cola1Cost;
                }
                if (colaSize.equals("six")){
                    System.out.print("Enter the number of cola of 6-packs to order: ");
                    int cola6Count = input.nextInt();
                    cola6Quantity += cola6Count;
                    cola6QuantityOverall += cola6Count;
                    //calculating the deposit before the GST
                    double cola6Deposit = SB_DEPOSIT * 6 * cola6Count;
                    //calculating the cost without GST
                    double cola6CostNoGst = ((COLA_6 * cola6Count) + cola6Deposit);
                    //calculating the GST
                    double gstCola6 = cola6CostNoGst * GST;
                    //calculating the cost with GST
                    double cola6Cost = cola6CostNoGst + gstCola6;

                    System.out.printf("%s %.2f\n","Cola cost is (including GST and deposit); $" , cola6Cost);
                    subTotal += cola6Cost;
                    subTotalOverall += cola6Cost;
                }
                if (colaSize.equals("two")){
                    System.out.print("Enter number of number of 2L colas to order: ");
                    int cola2LCount = input.nextInt();
                    cola2LQuantity += cola2LCount;
                    cola2LQuantityOverall += cola2LCount;
                    //calculating the deposit before the GST
                    double cola2LDeposit = LB_DEPOSIT * cola2LCount;
                    //calculating the cost without GST
                    double cola2LCostNoGst = ((COLA_2L * cola2LCount) + cola2LDeposit);
                    //calculating the GST
                    double gstCola2L = cola2LCostNoGst * GST;
                    //calculating the cost
                    double cola2LCost = cola2LCostNoGst + gstCola2L;
                    System.out.printf("%s %.2f\n","Cola cost is (including GST and deposit); $" , cola2LCost);
                    subTotal += cola2LCost;
                    subTotalOverall += cola2LCost;
                }
                break;

                //display daily summary with quantity sold and total revenue and customers
                case 'x':
                ordering = false;
                System.out.println("Daily Summary");
                String longerDivider = "--------------------------------------------";
                System.out.println(longerDivider);
                System.out.printf("%s %10s %13s %14s\n", "Item", "Size", "Price", "Qty.Sold");
                System.out.println(longerDivider);
                System.out.printf("%s %13s %4s%.5s %14s\n", "Pizza", "Small   ",  "$",PIZZA_S, pizzaSQuantityOverall);
                System.out.printf("%s %13s %4s%.5s %14s\n", "Pizza", "Large   ",  "$",PIZZA_L, pizzaLQuantityOverall);
                System.out.printf("%s %14s %5s%.5s %14s\n", "Side", "Salad   ",  "$",SIDE_SALAD, saladsQuantityOverall);
                System.out.printf("%s %14s %5s%.5s %14s\n", "Cola", "1 can   ",  "$",COLA_1, cola1QuantityOverall);
                System.out.printf("%s %12s %7s%.5s0 %14s\n", "Cola", "6-pack", "$",COLA_6, cola6QuantityOverall);
                System.out.printf("%s %9s %10s%.5s %14s\n", "Cola", "2 L", "$", COLA_2L, cola2LQuantityOverall);
                System.out.printf("%s %.2f\n","The total revenue for the day is $" , subTotalOverall);
                System.out.println("The total number of customers today is " + customerCount);
                System.out.println("Good bye!");

                break;

                default:
                System.out.println("Invalid option");
                break;
            }
            if (ordering) {
                System.out.print("Is this customer's order complete (Y/N): ");
                char complete = input.next().charAt(0);
                if (complete == 'y'){
                    System.out.printf("%s %.2f\n","The total cost for this customer is $" , subTotal);
                    //Resetting the values for every new customer
                    subTotal = 0;
                    pizzaSQuantity = 0;
                    pizzaLQuantity = 0;
                    saladsQuantity = 0;
                    cola1Quantity = 0;
                    cola6Quantity = 0;
                    cola2LQuantity = 0;
                    customerCount++;
                }   
            }
        }

        while(ordering);{
        }   
    }
}
