/**
 * Created by jasonhertz on 3/16/17.
  * adapted from code by Victor Adamchik
 * https://www.cs.cmu.edu/~adamchik/15-121/lectures/Sorting%20Algorithms/code/MergeSort.java
 */
public class mergeSort {

    public static void mergeSort(Comparable [ ] a) {
        Comparable[] tmp = new Comparable[a.length];
        mergeSort(a, tmp,  0,  a.length - 1);
    }


    private static void mergeSort(Comparable [ ] a, Comparable [ ] tmp, int left, int right)
    {
        if( left < right )
        {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }


    private static void merge(Comparable[ ] a, Comparable[ ] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left].compareTo(a[right]) <= 0)
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }

    public static void mergeSortTwo(Comparable [ ] a, Comparable [ ] b) {
        Comparable[] tmpA = new Comparable[a.length];
        Comparable[] tmpB = new Comparable[b.length];
        mergeSortTwo(a, tmpA,  0,  a.length - 1,
                        b, tmpB, 0, b.length - 1);
    }


    private static void mergeSortTwo (Comparable [ ] a, Comparable [ ] tmp, int left, int right,
                                      Comparable [ ] b, Comparable [ ] tmpb, int leftb, int rightb)
    {
        if( left < right )
        {
            int center = (left + right) / 2;
            mergeSortTwo(a, tmp, left, center, b, tmpb, leftb, center);
            mergeSortTwo(a, tmp, center + 1, right, b, tmpb, center +1, right);
            mergeTwo(a, tmp, left, center + 1, right, b, tmpb, leftb, center + 1, rightb);
        }
    }


    private static void mergeTwo(Comparable[ ] a, Comparable[ ] tmp, int left, int right, int rightEnd,
                                 Comparable[ ] b, Comparable[ ] tmpb, int leftb, int rightb, int rightEndb)
    {
        int leftEnd = right - 1;
        int leftEndb = rightb - 1;
        int k = left;
        int kb = leftb;
        int num = rightEnd - left + 1;
        int numb = rightEndb - leftb + 1;

        while(left <= leftEnd && right <= rightEnd
                && leftb <= leftEndb && rightb <= rightEndb) {
            if(a[left].compareTo(a[right]) <= 0) {
                tmp[k++] = a[left++];
                tmpb[kb++] = b[leftb++];

            } else {
                tmp[k++] = a[right++];
                tmpb[kb++] = b[rightb++];
            }

        }

        while(left <= leftEnd
                && leftb <= leftEndb) {   // Copy rest of first half
            tmp[k++] = a[left++];
            tmpb[kb++] = b[leftb++];

        }

        while(right <= rightEnd
                && rightb <= rightEndb) {
            // Copy rest of right half

            tmp[k++] = a[right++];
            tmpb[kb++] = b[rightb++];
        }
        // Copy tmp back
        for(int i = 0; i < num && i < numb; i++, rightEnd--, rightEndb--) {


            a[rightEnd] = tmp[rightEnd];
            b[rightEnd] = tmpb[rightEnd];
        }
    }
}
