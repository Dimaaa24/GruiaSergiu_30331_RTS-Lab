import java.util.Scanner;

public class Menu
{
    public void MenuDisplay()
    {
        System.out.println("======Complex Calculator======");
        System.out.println("1.Addition");
        System.out.println("2.Subtraction");
        System.out.println("3.Multiplication");
        System.out.println("x.Exit");
        System.out.println("Input Choice:");
    }

    public ComplexNumber AskForComplexNumber()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Input real part:");
        int realPart = input.nextInt();
        System.out.println("Input imaginary part:");
        int imaginaryPart = input.nextInt();
        return  new ComplexNumber(realPart, imaginaryPart);
    }

    public void DisplayNumber(int realPart, int imaginaryPart )
    {
        if(imaginaryPart >= 0)
            System.out.println(String.format("%d+%dj",realPart,imaginaryPart));
        else
            System.out.println(String.format("%d%dj",realPart,imaginaryPart));
    }

    public void WrongOption()
    {
        System.out.println("Wrong Option!");
    }
}
