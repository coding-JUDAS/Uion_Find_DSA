import java.util.ArrayList;
import java.util.List;

public class CartesianProductUtility {

    public CartesianProductUtility(){}

    /**
     * Generic method to form a cartesian product from a given list of lists.
     * (AxB)xC... = [a,b]x[c,d]x[e,f] = [[a,c,e],[a,d,e],[b,c,e],[b,d,e],[a,c,f],[a,d,f],[b,c,f],[b,d,f]...]
     * @param toJoinLists List containing lists.
     * @return cartesian product.
     * @param <T>
     */
    public static <T> List<List<T>> cartesianProduct(List<List<T>> toJoinLists){
        if(toJoinLists.isEmpty()){
            return new ArrayList<>();
        }
        toJoinLists = new ArrayList<>(toJoinLists);
        ArrayList<T> firstList = (ArrayList<T>) toJoinLists.remove(0);
         List<List<T>> firstProduct = joinLists(new ArrayList<>(),new ArrayList<>(), firstList);

         for(List<T> temp : toJoinLists){
             firstProduct = new ArrayList<>(joinProduct(firstProduct, temp));
         }
         return firstProduct;
    }

    /**
     * Method is used to join a List to an existing list of cartesian products. Method assumes
     * AxBxC = (AxB)xC...
     * @param firstProduct (AxB)
     * @param temp C
     * @return (AxB)xC
     * @param <T>
     */
    private static <T> List<List<T>> joinProduct(List<List<T>> firstProduct, List<T> temp) {
        List<List<T>> productTemp = new ArrayList<>();
        for(List<T> productList : firstProduct){
            productTemp = new ArrayList<>(joinLists(productTemp, (ArrayList<T>)productList, (ArrayList<T>) temp));
        }
        return productTemp;
    }

    /**
     * Method is used to join form a join (cartesian product) using two lists.
     * AxB = [a,b]x[c,d] = [[a,c],[a,d],[b,c],[b,d]]
     * @param product is a generic list of generic type lists.
     * @param currentList to be added to as the initial list [a,b].
     * @param toJoin list to be joined [c,d].
     * @return list containing products [[a,c],[a,d],[b,c],[b,d]].
     * @param <T> method level generic type parameter.
     */
    private static <T> List<List<T>> joinLists(List<List<T>> product, ArrayList<T> currentList, ArrayList<T> toJoin) {
        // Traverse over list to be joined
        for(T element : toJoin){
            // create new ArrayList temp with contents of currentList.
            ArrayList<T> temp = new ArrayList<>(currentList);
            // add element, from list to be joined, to temp array.
            temp.add(element);
            //  add temp array to list of cartesian products.
            product.add(temp);
        }
        return product;
    }
}
