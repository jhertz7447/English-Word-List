import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by jasonhertz on 3/16/17.
 */
public class Codex {
    private String[] c;
    private int searchIndex;
    String[] asciiLD;
    String[] sortedASCIILD;
    BigInteger[] sortedADD;


    //constructor, builds codex of dictionary file and associated arrays
    // (asciiLD string representation of ascii values of word,
    // sorted ASCIILD sorted list of string representations,
    // sortedADD big integer representation of sorted ascii string representations list)
    public Codex() {

        BufferedReader br =  null;
        BufferedReader brA =  null;

        try {
            //countes entries in file
            br = new BufferedReader(new FileReader("/Users/jasonhertz/IdeaProjects/EngDictSearch/src/wordsEn.txt"));
            String sCurrentLine = br.readLine();
            int counter = 0;
            while (sCurrentLine != null) {
                counter++;
                //System.out.println(sCurrentLine);
                sCurrentLine = br.readLine();
            }
            //allocates space for String array
            c = new String[counter];

            //fills String array
            brA = new BufferedReader(new FileReader("/Users/jasonhertz/IdeaProjects/EngDictSearch/src/wordsEn.txt"));
            String sCurrentLineA = brA.readLine();
            int counterA = 0;
            while (sCurrentLineA != null) {
                c[counterA] = sCurrentLineA;
                //System.out.println(c[counterA]);
                sCurrentLineA = brA.readLine();
                counterA++;
            }
            System.out.println("Codex built " + c.length);

            asciiLD = this.getCodexASCII();
            System.out.println("ASCII Codex built " + asciiLD.length);

            sortedASCIILD = Codex.mergeSort(asciiLD);
            System.out.println("ASCII Codex sorted " + sortedASCIILD.length);

            sortedADD = Codex.getBigIntList(sortedASCIILD);
            System.out.println("ASCII Codex sorted converted to BigInteger[] " + sortedADD.length);


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(br != null) {
                    br.close();
                }

            }
            catch (IOException ioe) {
                System.out.println("Error in closing the BufferedReader");
            }
        }
    }
    //get methods
    public String[] getCodex() {
        return c;
    }
    public String[] getASCIIList() {
        return asciiLD;
    }
    public String[] getSortedASCIIList() {
        return sortedASCIILD;
    }
    public BigInteger[] getBigIntSortASCIIList() {
        return sortedADD;
    }

    public String[] getCodexASCII() {
        String[] ASCIIList = new String[c.length];
        int counter = 0;
        int letterASCII = 0;
        String sLASCII = null;
        String temp = null;
        String newWord = "";
        //converts each entry to String representation, and adds to array for return, ASCIIList
        while (counter < c.length) {
            newWord = "";
            for(int i =0; i<c[counter].length(); i++) {

                letterASCII = (int) c[counter].charAt(i);
                sLASCII = String.valueOf(letterASCII);
                //pads letters with values below 100
                if(letterASCII <100)  {
                    temp = "0";
                    temp += sLASCII;
                    sLASCII = temp;
                }
                //System.out.print("sLASCII " + sLASCII);
                newWord += sLASCII;
            }
            //System.out.print("newWord " + newWord);
            ASCIIList[counter] = newWord;
            //System.out.println("");
            counter++;
        }
        return ASCIIList;
    }
    public void display() {
        //displays entire dictionary
        int counter = 0;
        while (counter < c.length) {
            System.out.println(c[counter]);
            displayWord(c[counter]);
            counter++;
        }
    }
    static void display(String[] list) {
        //displays provided list
        int counter = 0;
        while (counter < list.length) {
            System.out.println(list[counter]);
            counter++;
        }
    }
    static void convertDisplay(String[] list) {
        //displays provided list of ascii string representations
        int counter = 0;
        while (counter < list.length) {
            System.out.println(convertASCII(list[counter]));
            counter++;
        }
    }
    static String getMax(String[] list) {
        Double max = 97.0;
        String sMax = "a";
        String line = "";
        int counter = 0;
        while (counter < list.length) {
            line = list[counter];
            //System.out.println(line);
            Double dLine = Double.parseDouble(line);
            //System.out.println(dLine);

            if(dLine>max) {
                max = dLine;
                sMax = line;
            }
            counter++;
        }
        System.out.println("max" + max);

        return sMax;

    }

    static String getMin(String[] list) {
        Double min = 122.0;
        String sMin = "z";
        String line = "";
        int counter = 0;
        while (counter < list.length) {
            line = list[counter];
            //System.out.println(line);
            Double dLine = Double.parseDouble(line);
            //System.out.println(dLine);

            if(dLine<min) {
                min = dLine;
                sMin = line;
            }
            counter++;
        }
        System.out.println("minimum" + min);

        return sMin;

    }

    static String convertASCII(String text) {
        String convertedASCII = "";
        String asciiBlock = "";
        char letter;
        int block;
        //converts three digit blocks
        // char representations to block
        // on 3: char representation --> integer ascii --> char letter
        for(int i=0;i<text.length();i++) {
            //System.out.print(text.charAt(i));
            asciiBlock += text.charAt(i);

            if((i+1)%3==0) {
                //System.out.println("block " + i + " " + asciiBlock);
                block = Integer.parseInt(asciiBlock);
                //System.out.println(block);
                letter = (char) block;
                //System.out.println(letter);
                convertedASCII += letter;
                asciiBlock = "";
            }
        }
        return convertedASCII;
    }
    static String convertASCIILower(String text) {
        String convertedASCII = "";
        String asciiBlock = "";
        char letter;
        int block;

        for(int i=0;i<text.length();i++) {
            //System.out.print(text.charAt(i));
            asciiBlock += text.charAt(i);

            if((i+1)%2==0) {
                //System.out.println("block " + i + " " + asciiBlock);
                block = Integer.parseInt(asciiBlock);
                //System.out.println(block);
                letter = (char) block;
                //System.out.println(letter);
                convertedASCII += letter;
                asciiBlock = "";
            }
        }
        return convertedASCII;
    }

    static String[] mergeSort(String[] list) {

        BigInteger[] listD = new BigInteger[list.length];
        int counter = 0;
        while (counter < list.length) {
            listD[counter] = new BigInteger(list[counter]);
            counter++;
        }
        //sorts both arrays simultaneously by order of BigInteger in listD
        mergeSort.mergeSortTwo(listD, list);

        return list;
    }

    static BigInteger[] getBigIntList(String[] list) {
        //returns same list with BigInteger representations
        BigInteger[] listD = new BigInteger[list.length];
        int counter = 0;
        while (counter < list.length) {
            listD[counter] = new BigInteger(list[counter]);
            counter++;
        }
        return listD;
    }

    public BigInteger binarySearch(BigInteger[] list, int first, int last, BigInteger desiredItem) {
        int mid = (first + last) / 2;

        if(first > last) {
            return null;
        }
        if(desiredItem.equals(list[mid])) {
            //sets index in sorted array and returns BigInteger representation
            searchIndex = mid;
            return list[mid];
        } else if(desiredItem.compareTo(list[mid]) < 0) {
            return binarySearch(list,first,mid-1,desiredItem);
        } else {
            return binarySearch(list, mid+1,last,desiredItem);
        }
    }

    public String searchTerm(String term) {


        searchIndex = 0;

        String sTerm = term;

        String newWord = "";
        int letterASCII;
        String sLASCII;
        String temp;
        //converts search term to BigInteger representation
        for(int i =0; i<sTerm.length(); i++) {

            letterASCII = (int) sTerm.charAt(i);
            sLASCII = String.valueOf(letterASCII);
            if(letterASCII <100)  {
                temp = "0";
                temp += sLASCII;
                sLASCII = temp;
            }
            newWord += sLASCII;
        }

        BigInteger searchTerm = new BigInteger(newWord);

        if(binarySearch(sortedADD, 0, sortedADD.length, searchTerm) != null) {
            return Codex.convertASCII(sortedASCIILD[searchIndex]);
        } else {
            return null;
        }
    }

    public void displayWord(String word) {
        String newWord = "";
        int letterASCII;
        String sLASCII;
        String temp;
        //display various representations of word, using BigInteger methods on ASCII value
        for(int i =0; i<word.length(); i++) {

            letterASCII = (int) word.charAt(i);
            sLASCII = String.valueOf(letterASCII);
            if(letterASCII <100)  {
                temp = "0";
                temp += sLASCII;
                sLASCII = temp;
            }
            newWord += sLASCII;
        }

        //System.out.println("string of ascii: " + newWord);

        BigInteger rep1 = new BigInteger(newWord);
        BigInteger mod1 = new BigInteger("2");
        BigInteger mod2 = new BigInteger("3");
        BigInteger mod3 = new BigInteger("5");



        System.out.println("ASCII: " + rep1.toString());
        System.out.println("ASCII mod 2 " + rep1.mod(mod1));
        System.out.println("ASCII mod 3 " + rep1.mod(mod2));
        System.out.println("ASCII mod 5 " + rep1.mod(mod3));

        System.out.println("ASCII log2 " + Math.log(rep1.doubleValue()));
        System.out.println("ASCII sin" + Math.sin(rep1.doubleValue()));



        System.out.println("big integer.byteValue(): " + rep1.byteValue());
        System.out.println("big integer.intValue(): " + rep1.intValue());
        System.out.println("big integer.longValue(): " + rep1.longValue());
        System.out.println("big integer.doubleValue(): " + rep1.doubleValue());
        System.out.println("big integer.floatValue(): " + rep1.floatValue());
        System.out.println("big integer.toString(2): " + rep1.toString(2));
        System.out.println("big integer.toString(8): " + rep1.toString(8));
        System.out.println("big integer.toString(16): " + rep1.toString(16));

    }



}
