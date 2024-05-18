
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private ArrayList<UnionFind> list;

    public Program(){
        list = new ArrayList<>();
    }
    public void run(){
        System.out.println("Enter a filename to load data : ");
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        int choice = 0;
        loadData(fileName);
        displayComponents();
        String result = "";
        while(choice != 4){
            System.out.println("1. Run Query");
            System.out.println("2. Write Data");
            System.out.println("3. Display Building");
            System.out.println("4. Exit");
            System.out.println("Select Option");
            choice = Integer.parseInt(in.nextLine());
            if(choice == 1){
                result = query(in);
                if(result.length() > 1){
                    System.out.println(result);
                }
            }
            else if(choice == 2){
                writeData();
            }
            else if(choice == 3){
                System.out.println("Enter a building number");
                Scanner input = new Scanner(System.in);
                int buildingNumber = Integer.parseInt(input.nextLine());
                displayComponent(buildingNumber);
            }
        }
        System.out.println("Exiting program");
    }

    private void loadData(String fileName){
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName + ".txt");
            readData(fileInputStream);
            System.out.println("Done!");
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found ! " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void readData(FileInputStream fin){
        Scanner in = new Scanner(fin);
        String line = in.nextLine();
        while (in.hasNext()){
            if(line.contains("#")){
                line = in.nextLine();
            }
            UnionFind uf = new UnionFind();
            if(line.contains(",")){
                Scanner names = new Scanner(line).useDelimiter(",");
                for (Scanner it = names; it.hasNext(); ) {
                    String s = it.next();
                    uf.makeSet(s);
                }
                line = in.nextLine();
            }
            while(line.contains("==")){
                String[] connections = line.split("==");
                uf.union(connections[0], connections[1]);
                if(in.hasNext()){
                    line = in.nextLine();
                }
                else{
                    break;
                }
            }
            list.add(uf);
        }
        in.close();
    }
    public void writeData(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("WaystarEmployeesOut.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found ! " + e.getMessage());
        }

        int x = 1;
        for(UnionFind unionFind : list){
            writer.println("Building: " + x);
            x++;
            unionFind.writeData(writer);
            writer.println("#");
        }
        writer.close();
    }

    public void displayComponents(){
        int i = 0;
        for(UnionFind unionFind : list){
            System.out.printf("Building: %d \n" +
                    "social connections: %d \n" , i++, unionFind.getNumberOfComponents());
            unionFind.display();
            System.out.println();
        }
    }
    public void displayComponent(int comp){
        UnionFind component = list.get(comp);
        System.out.printf("Building: %d \n" +
                "social connections: %d \n" , comp, component.getNumberOfComponents());
        component.display();

    }
    public String query(Scanner in){
        System.out.println("Enter a building number: ");
        int buildingNr = Integer.parseInt(in.nextLine());
        System.out.println("Enter a name for the first person: ");
        String name1 = in.nextLine();
        System.out.println("Enter a name for the second person:");
        String name2 = in.nextLine();

        UnionFind temp = list.get(buildingNr);

        try {
            if(temp.find(name1).equals(temp.find(name2))){
                return "In Building " + buildingNr + ", " + name1 + " and " + name2 + " will use the same fire escape.";
            }
            return "In Building " + buildingNr + ", " + name1 + " and " + name2 + " will NOT use the same fire escape.";
        } catch (NullPointerException e) {
            System.out.println("Object not found ! " + e.getMessage());
        }
        return "";
    }
}
