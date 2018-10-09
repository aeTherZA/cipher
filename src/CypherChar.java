public class CypherChar {
    private static final char alphabet[] =
            {'0','1','2','3','4','5','6','7','8','9',
                    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                    'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    /*
    method to check if a given char is within the alphabet
    @param c: the char that could potentially be within the alphabet
    @return: True if c is within the alphabet
        false otherwise
     */
    public static boolean isCypherChar(char c)
    {
        boolean isCypherChar = false;
        for (int x = 0; x < alphabet.length; ++x)
        {
            if (c == alphabet[x])
                isCypherChar = true;
        }

        return isCypherChar;
    }

    /*
    method for performing a caesar shift on a character
    @param cc: CypherChar to perform caesar shift upon
    @param shift: the size of the caesar shift to be performed
    @return: the resultant char from performing a caesar shift of size shift upon cc
     */
    public static char shiftChar(char cc,int shift) throws NotACypherChar
    {
        if (!isCypherChar(cc))
            throw new NotACypherChar(cc);

        int currCC = -1;
        for (int x = 0; x < alphabet.length; ++x)
        {
            if (cc == alphabet[x])
            {
                currCC = x;
            }
        }

        int newCC = currCC + shift;
        if (newCC >= alphabet.length)
        {
            newCC = newCC - alphabet.length;
        }

        return alphabet[newCC];
    }
}
