import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class DataGeneration {
    public static final int ARRAY_SIZE = 10_000;

    public static final int SEARCH_TEST_SIZE = 100;

    public static final int DELETE_SIZE = 1000;

    public static void main(String[] args) throws FileNotFoundException {
        AATree tree = new AATree();
        int[] array = randomArray();

        insertData(tree, array);

        Arrays.sort(array);

        searchData(tree, array);
        deleteData(tree, array);
    }
    public static void insertData(AATree tree, int[] array) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("insert data.txt");
        int totalIters = 0;
        long startTimeTotal = System.nanoTime();
        for (int j = 0; j < array.length; j++) {
            int i = array[j];
            long startTime = System.nanoTime();
            int iters = tree.insert(i);
            totalIters+=iters;
            float duration = ((System.nanoTime() - startTime) / 1000f);
            out.println(j+" "+iters+" "+duration);
        }
        int avgIters = totalIters/array.length;
        float avgTime = (System.nanoTime() - startTimeTotal)/1000/1000f/array.length;
        out.println(avgIters+" "+ avgTime);
        out.close();
    }

    public static void searchData(AATree tree, int[] array) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("search data.txt");
        int totalIters = 0;
        long startTimeTotal = System.nanoTime();
        for (int j = 0; j < SEARCH_TEST_SIZE; j++) {
            int i = array[(int)(Math.random()*array.length)];
            long startTime = System.nanoTime();
            int iters = tree.lookFor(i);
            totalIters+=iters;
            float duration = ((System.nanoTime() - startTime) / 1000f);
            out.println(j+" "+iters+" "+duration);
        }
        int avgIters = totalIters/SEARCH_TEST_SIZE;
        float avgTime = (System.nanoTime() - startTimeTotal)/1000/1000f/SEARCH_TEST_SIZE;
        out.println(avgIters+" "+ avgTime);
        out.close();
    }

    public static void deleteData(AATree tree, int[] array) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("delete data.txt");
        int totalIters = 0;
        long startTimeTotal = System.nanoTime();
        for (int j = 0; j < DELETE_SIZE; j++) {
            int i = array[(int)(Math.random()*array.length)];
            long startTime = System.nanoTime();
            int iters = tree.delete(i);
            totalIters += iters;
            float duration = ((System.nanoTime() - startTime)/1000/1000f);
            out.println(j+" "+iters+" "+duration);
        }
        int avgIters = totalIters/DELETE_SIZE;
        float avgTime = (System.nanoTime() - startTimeTotal)/1000/1000f/DELETE_SIZE;
        out.println(avgIters+" "+ avgTime);
        out.close();
    }

    public static int[] randomArray(){
        int[] array = new int[ARRAY_SIZE];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*100_000_000);
        }

        return array;
    }
}