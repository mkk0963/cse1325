import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsOfSudoku 
{
    private static Sudoku[] suds = new Sudoku[81];
    private static HashSet<Sudoku> solutions = new HashSet<>();
    private static ExecutorService threadPool = null;
    private static int nextIndex = 0;

    public static void main(String[] args) 
    {
        try 
        {
            if(args.length < 2) 
            {
                System.err.println("usage: java ThreadsOfSudoku threads puzzleFilename [puzzleName]");
                System.exit(-1);
            }
            
            Scanner in = new Scanner(new File(args[1]));
            String name = (args.length > 1) ? args[2] : null;
            Sudoku sud = new Sudoku(in, name);

            System.out.println(sud + "\n\n");
            
            int numThreads = Integer.parseInt(args[0]);
            
            // Split the search field into 81 different sub-puzzles
            int index = 0;
            for(int y = 1; y <= 9; ++y) 
            {
                for(int x = 1; x <= 9; ++x) 
                {
                    suds[index++] = new Sudoku(sud, new int[]{x, y});
                }
            } 

            threadPool = Executors.newFixedThreadPool(numThreads);

            for (int i = 0; i < numThreads; ++i) 
            {
                final int id = i + 1;
                threadPool.execute(() -> solveSuds(id));
            }

            threadPool.shutdown();
            while (!threadPool.isTerminated()) {}
                        
            // Show the solution(s), if any
            System.out.println("Found " + solutions.size() + " solutions");

            for(var s : solutions) System.out.println(s);
        } 
        catch(Exception e) 
        {
            //System.err.println("ABORT: " + e);
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
    
    // Be sure to protect the solutions ArrayList from Thread Interference!
    private static void solveSuds(int id) 
    {
        System.out.println("Thread " + id + " starting");

        int index = getSudIndex();

        while(index >= 0)
        {
            System.out.println("Thread " + id + " solving " + index);

            if(suds[index].solve()) 
            {
                synchronized (solutions)
                {
                    solutions.add(suds[index]);
                }
            }

            index = getSudIndex();
        }
        //System.out.println("Thread " + id + " done");
    }

    private synchronized static int getSudIndex() 
    {
        int index = nextIndex++;

        if (index > 80) 
        {
            return -1;
        }

        return index;
    }
}
