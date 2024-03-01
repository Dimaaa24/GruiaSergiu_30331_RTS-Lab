import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
        Random random = new Random();

        int[] numbers = {random.nextInt(100) , random.nextInt(100)
                                , random.nextInt(100) , random.nextInt(100)
                                , random.nextInt(100) , random.nextInt(100)
                                , random.nextInt(100) , random.nextInt(100)
                                , random.nextInt(100) , random.nextInt(100) };

        System.out.println("Unsorted random generated array:");

        for (int i = 0 ; i < 10 ; i++ )
            System.out.printf(numbers[i]+" ");

        for (int i = 0 ; i < 10 ; i++)
            for (int j = 0 ; j < 10 ; j++)
                if(numbers[i]<numbers[j])
                {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }

        System.out.println("\nSorted random generated array:");

        for (int i = 0 ; i < 10 ; i++ )
            System.out.printf(numbers[i]+" ");
    }

}