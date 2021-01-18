import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    List items = new ArrayList<>();

    public Shop(List<String> items) {
        for(int i = 0; i < items.size(); i++) {
            String[] itemDetails = items.get(i).split(",");
            this.items.add(itemDetails);
        }
    }

    public void outputShop(Player person){
        System.out.println("[ Wallet: " + person.worth);
        for(int i = 0; i < items.size(); i++) { //display options
            System.out.println("[ " +(i+1) + " to buy +"
                    + ((String[])this.items.get(i))[1] + " "
                    + ((String[])this.items.get(i))[0] + " for "
                    + ((String[])this.items.get(i))[2] + " coins");
        }
        System.out.println("[ " + items.size()+1 + " to leave");
        Scanner Scanner = new Scanner(System.in);
        try {
            int option = Integer.parseInt(Scanner.nextLine())-1;
            if(option <  0 || option > items.size()) { //check if option is in bounds
                System.out.println("[ This item does not exist");
            } else if(option == items.size()) { //check if want to leave
                System.out.println("[ Bye....");
                return;
            } else {
                if(person.worth >= Integer.parseInt(((String[])items.get(option))[2])) { //check if have enough money
                  switch (((String[])items.get(option))[0]) { //check what item u buying
                       case "Strength":
                           person.worth = person.worth - Integer.parseInt(((String[])items.get(option))[2]);
                           person.strength = person.strength + Integer.parseInt(((String[])items.get(option))[1]);
                           break;
                       case "Health":
                           person.worth = person.worth - Integer.parseInt(((String[])items.get(option))[2]);
                           person.health = person.health + Integer.parseInt(((String[])items.get(option))[1]);
                           break;
                   }
                    System.out.println("[ You Bought " + ((String[])items.get(option))[0] + " for " + ((String[])items.get(option))[2]);
                }else {
                    System.out.println("[ You're Broke");
                }
            }
        }catch(NumberFormatException | InputMismatchException e){
            System.out.println("[ Error: " + e);
        }
        outputShop(person); //repeat
    }

}