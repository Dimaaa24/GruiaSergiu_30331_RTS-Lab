public class Main {
    public static void main(String[] args)
    {
       int[][] matrixOne = {
                                        {2 , 3 , 1},
                                        {7 , 1 , 6},
                                        {9 , 2 , 4}
                                    };
        int[][] matrixTwo = {
                                        {8 , 5 , 3},
                                        {3 , 9 , 2},
                                        {2 , 7 , 3}
                                    };
        int[][] result = new int[3][3];

        for(int i=0 ; i < 3 ;i ++)
        {
            for(int j=0 ; j<3 ; j++)
            {
                result[i][j]=matrixOne[i][j]+matrixTwo[i][j];
            }
        }

        System.out.println("Result of addition of matrixes:");

        for(int i=0 ; i < 3 ;i ++)
        {
            for(int j=0 ; j<3 ; j++)
            {
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }

        for(int i=0 ; i < 3 ;i ++)
        {
            for(int j=0 ; j<3 ; j++)
            {
                int sum=0;
                for(int k=0 ; k<3 ; k++)
                {
                    sum += matrixOne[i][k]*matrixTwo[k][j];
                }
                result[i][j] = sum;
            }
        }

        System.out.println("Result of multiplication of matrixes:");

        for(int i=0 ; i < 3 ;i ++)
        {
            for(int j=0 ; j<3 ; j++)
            {
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }

    }
}