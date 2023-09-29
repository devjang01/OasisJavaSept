import java.util.*;

class NumberGame
{
    public long start_game(long sys_gen_num, long points)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------------");

        // size : number of attempts
        int attempt = 1, data, size = 8;

        while(attempt != size)
        {
            System.out.println("----- Attempt " + attempt + " -----");
            System.out.println("Enter the number : ");
            data = scan.nextInt();

            if(data == sys_gen_num)
            {
                points = points + (size-attempt);
                System.out.println("-- You've have guessed it right!! --\n");
                return points;
            }
            else
            {
                if(data < sys_gen_num)
                {
                    System.out.println("Try higher number!!\n");
                }
                else
                {
                    System.out.println("Try lower number!!\n");
                }
            }
            attempt++;
        }
        return 0;
    }

    public void show_history(long points, ArrayList<Long> table)
    {
        table.add(points);
        
        System.out.println("*********************************");
        System.out.println("\t-- POINTS TABLE --");
        System.out.println("Game round  \t  Points");
        
        int total = 0;
        
        for(int i = 0; i < table.size(); i++)
        {
            total += table.get(i);
            if(table.get(i) == 0)
            {
                total = 0;
            }
            System.out.println("   " + (i+1) + " \t\t   " + table.get(i));    
        }
        System.out.println();
        
        System.out.println("TOTAL SCORE : " + total);
        System.out.println("*********************************");
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        NumberGame ng = new NumberGame();

        ArrayList<Long> point_table = new ArrayList<Long>();

        long choice, data;
        String ch = "y";

        System.out.println("\t             ----- NUMBER GUESSING GAME -----");

        System.out.println("-----------------------------------------------------------------");
        System.out.println("                                Rules");
        System.out.println("1. System will generate a number and you have to guess the number.");
        System.out.println("2. You will have total of 8 attempts to crack the number");
        System.out.println("3. Hints will be provided eventually");
        System.out.println("4. If you lose your points get reset to 0.");
        System.out.println("5. Guessed early == More points");
        System.out.println("-----------------------------------------------------------------\n");

        long points = 0;

        while(ch.equals("y") == true)
        {
            System.out.println("Enter the game ? [ y / n ]");
            ch = scan.next();
    
            if(ch.equals("n") == true)
            {
                System.out.println("\t     -- Game exited --");
                System.err.println("\t -- THANKS FOR PLAYING --\n\n");
                System.exit(0);
            }
    
            // generating random number 
            long max = 100, min = 1;
            long sys_gen_num = Math.round(Math.random() * (max - min + 1) + min);
    
            System.out.println("\nThe system has generated the number... its your turn to guess it!!\n");
    
            long status = ng.start_game(sys_gen_num, points);

            if(status != 0)
            {
                System.out.println("----- Congratulations on the win!! -----\n");
                points = status;
            }
            else
            {
                System.out.println("----- Better luck next time! -----\n");
                points = 0;
            }

            ng.show_history(points, point_table);
        }
    }
}