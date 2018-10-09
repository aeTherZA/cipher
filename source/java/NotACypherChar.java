public class NotACypherChar extends Exception{
    private char invalidChar;

    public NotACypherChar(char ic)
    {
        invalidChar = ic;
    }

    public char getInvalidChar()
    {
        return invalidChar;
    }
}
