import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        ComplexNumber a;
        ComplexNumber b;

        Menu menu = new Menu();
        menu.MenuDisplay();

        Scanner input = new Scanner(System.in);
        String inputValue = input.nextLine();

        while(true)
        {
            switch(inputValue)
            {
                case "1":
                    a = menu.AskForComplexNumber();
                    b = menu.AskForComplexNumber();
                    menu.DisplayNumber(a.getRealPart()+b.getRealPart(), a.getImaginaryPart()+b.getImaginaryPart());
                    break;
                case "2":
                    a = menu.AskForComplexNumber();
                    b = menu.AskForComplexNumber();
                    menu.DisplayNumber(a.getRealPart()-b.getRealPart(), a.getImaginaryPart()-b.getImaginaryPart());
                    break;
                case "3":
                    a = menu.AskForComplexNumber();
                    b = menu.AskForComplexNumber();
                    menu.DisplayNumber(a.getRealPart()*b.getRealPart()-a.getImaginaryPart()*b.getImaginaryPart(),a.getRealPart()*b.getImaginaryPart()+a.getImaginaryPart()*b.getRealPart());
                    break;
                case "x":
                    System.exit(1);
                    break;
                default:
                    menu.WrongOption();
                    break;
            }
            menu.MenuDisplay();
            inputValue = input.nextLine();
        }

    }
}