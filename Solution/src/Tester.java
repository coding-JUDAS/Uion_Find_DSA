import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
       /* UnionFind unionFind = new UnionFind();
        unionFind.makeSet("Teri Nieves");
        unionFind.makeSet("Shane Mullins");
        unionFind.makeSet("Ailish Page");
        unionFind.makeSet("Aoife Bauer");

        unionFind.union("Ailish Page","Teri Nieves");
        unionFind.union("Shane Mullins", "Ailish Page");

        System.out.println(unionFind.getNumberOfComponents() + " connected components.");
        unionFind.display();
        /*unionFind.makeSet("0");
        unionFind.makeSet("1");
        unionFind.makeSet("2");
        unionFind.makeSet("3");
        unionFind.makeSet("4");

        System.out.println("before union");
        System.out.println("parent of 4: " + unionFind.find("4"));
        System.out.println("parent of 1: " + unionFind.find("1"));
        System.out.println("parent of 2: " + unionFind.find("2"));

        // connections: 0-1, 1-2, 3-4
        System.out.println("After...");
        unionFind.union("0", "1");
        unionFind.union("1", "2");
//        unionFind.union("2", "3");
        unionFind.union("3", "4");
        System.out.println(unionFind.getNumberOfComponents() + " connected components.");

        System.out.println("parent of 4: " + unionFind.find("4"));
        System.out.println("parent of 1: " + unionFind.find("1"));
        System.out.println("parent of 2: " + unionFind.find("2"));

        System.out.println();

        System.out.println("parent of 4: " + unionFind.find("4"));
        System.out.println("parent of 1: " + unionFind.find("1"));
        System.out.println("parent of 2: " + unionFind.find("2"));

        System.out.println();
        unionFind.display();*/
        List<List<String>> lists = new ArrayList<>();
        ArrayList<String> first = new ArrayList<>(Arrays.asList("a", "b"));
        ArrayList<String> second = new ArrayList<>(Arrays.asList("c", "d"));
        ArrayList<String> third = new ArrayList<>(Arrays.asList("e", "f"));
        ArrayList<String> forth = new ArrayList<>(Arrays.asList("g", "h"));

        lists.add(first);
        lists.add(second);
        lists.add(third);
        lists.add(forth);
        System.out.println("Lists : " + lists);
        System.out.println("Creating cartesian product. . .");
        List<List<String>> cartesianProduct = CartesianProductUtility.cartesianProduct(lists);
        System.out.println("(AxB)XC... : " + cartesianProduct);
    }
}
