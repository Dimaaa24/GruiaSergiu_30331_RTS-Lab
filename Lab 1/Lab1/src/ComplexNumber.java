public class ComplexNumber
{
    private int realPart;
    private int imaginaryPart;

    ComplexNumber(int realPart, int imaginaryPart)
    {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public int getRealPart() {
        return realPart;
    }

    public int getImaginaryPart() {
        return imaginaryPart;
    }

    public void setRealPart(int realPart) {
        this.realPart = realPart;
    }

    public void setImaginaryPart(int imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }
}
