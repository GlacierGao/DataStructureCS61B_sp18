/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
//        return null;
        // find the max character nums among all asciis
        int maxCharacterNums = Integer.MIN_VALUE;
        for (String ascii : asciis) {
            if (ascii.length() >= maxCharacterNums) {
                maxCharacterNums = ascii.length();
            }
        }

        // pad all the String with empty values ont their right
//        for (String ascii : asciis) { !!!WRONG! 想想这句话的实质，后面只改变ascii变量的话没法储存在array里！
        for (int i = 0; i < asciis.length; i++) {
            if (asciis[i].length() < maxCharacterNums) {
                while (asciis[i].length() != maxCharacterNums) {
                    asciis[i] = asciis[i].concat("_");
                }
            }
        }
//        System.out.println(Arrays.toString(asciis));
        // sort the String arr--asciis
        for (int i = maxCharacterNums - 1; i >= 0; i--) {
            asciis = sortHelperLSD(asciis, i);
        }

        return asciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
//        return;
        // step -1 : convert the chars of all the "ascii".charAt[index] to int that are ascii numbers
        int[] asciisIndexCharNum = new int[asciis.length];
        for (int i = 0; i < asciisIndexCharNum.length; i++) {
            asciisIndexCharNum[i] = (int) asciis[i].charAt(index);
        }
        // step 0: find max and min among all the ascii numbers
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : asciisIndexCharNum) {
            if (num >= max) {
                max = num;
            }
            if (num <= min) {
                min = num;
            }
        }

        // step 1: count the frequency
        int[] counts = new int[max - min + 1];
        for (int num : asciisIndexCharNum) {
            counts[num - min]++;
        }

        // step 2: initialize the index for each "count"
        int[] numIndex = new int[counts.length];
        int initialNum = 0;
        for (int i = 0; i < numIndex.length; i++) {
            numIndex[i] = initialNum;
            initialNum += counts[i];
        }

        // step 3: allocate the initial asciis to their right places according to the specific sorted index
        // very ugly implementation because my brain has already shut down
//        String[] oldAsciis = asciis.clone();
        String[] newAsciis = new String[asciis.length];
        for (int ii = 0; ii < asciis.length; ii++) { // traverse the ascii numbers of the asciis--index--char
            int num = asciisIndexCharNum[ii];
            int i = numIndex[num - min]; // "i" refers to the sorted place of all "ascii" / actual refers to the sorted place of all ascii numbers
            newAsciis[i] = asciis[ii]; // sort the old asciis
            numIndex[num - min]++;
        }
        return newAsciis;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
//
//    public static void main(String[] args) {
//        String[] test = {"rr", "baobei", "baby", "apple", "love", "heart", "ok"};
//        System.out.println(Arrays.toString(test));
//        String[] sortedTest = sort(test);
//        System.out.println(Arrays.toString(sortedTest));
//    }
}
