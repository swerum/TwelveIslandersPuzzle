public class TwelveMen {

    public static void main(String[] args) {
        if (textCases()) System.out.println("All test Cases have passed!");
        else System.out.println("At least one test case has failed.");
    }

    //checks a bunch of different test cases
    private static boolean textCases() {
        for (int i = 0; i < 12; i++) {
            if (!checkForIndex(i, 0, 1)) return false;
            if (!checkForIndex(i, 0, -1)) return false;
            if (!checkForIndex(i, 2, 1)) return false;
            if (!checkForIndex(i, 2, 3)) return false;
            if (!checkForIndex(i, -2, -1)) return false;
        }
        return true;
    }

    //checks if for a given index, the algorithm returns the correct the result
    private static boolean checkForIndex(int i, int weight, int outlyerWeight) {
        int[] arr = getRandomArray(i, 0, 1);
        int result = getIndexOfMan(arr);
        if (i != result) System.out.println("If the man's index is "+i+", the algorithm returns "+result);
        return i == result;
    }

    //creates an array of length twelve with all islanders having the same weight except one, who has the outlyerWeight
    private static int[] getRandomArray(int r, int weight, int outlyerWeight) {
        int[] arr = new int[12];
        for (int i = 0; i < 12; i++) arr[i] = weight;
        arr[r] = outlyerWeight;
        return arr;
    }

    private static int getIndexOfMan(int[] arr) {
        //our 3 comparisons
        int round1 = compareTwoGroupsOfFour(arr, 0, 1, 2, 3,    4, 5, 6, 7);
        int round2 = compareTwoGroupsOfFour(arr, 0, 1, 8, 10,   2, 3, 4, 9);
        int round3 = compareTwoGroupsOfFour(arr, 0, 5, 8, 9,    1, 2, 6, 11);
        //helpful booleans: were 2 rounds nonzero and the same or nonzero and different
        boolean r1r2Same = round1 == round2;
        boolean r2r3Same = round2 == round3;
        boolean r1r3Same = round1 == round3;
        
        //if the two sides are equal in round1
        if (round1 == 0) {
            //none of the men weighed in round1 can be the outlyer --> 8,9,10,11
            if (round2 == 0) {
                //8, 9, 10 are excluded
                return 11;
            } else if (round3 == 0) {
                //8,9,11 are excluded
                return 10;
            } else if (r2r3Same) {
                //8 is on the same side in both r2 and r3
                return 8;
            } else return 9;
        } 
        //if the two sides are equal in round2
        else if (round2 == 0) {
            ///0,1,2,3,4,8,9,10 are excluded --> 5,6,7,11
            //11 would have already been caught if round1 was zero --> 5,6,7
            if (r1r3Same) {
                //6 was on the same side both times
                return 6;
            } else if (round3 == 0) {
                //5 and 6 are excluded
                return 7;
            } else { return 5;}
        }
        else if (round3 == 0) {
            //exclude 0,1,2, 5,6 ,8,9,11 --> 3,4,7,10
            //we've already had case 7 and 10 --> 3,4
            if (r1r2Same) {
                //4 is on the same side both times
                return 4;
            } else return 3;
        } 
        //the cases where non of the rounds are 0
        else {
            //the remaining cases are 0, 1, 2
            if (r1r2Same & r2r3Same) {
                //0 was on the same side all three times
                return 0;
            } else if (r1r2Same) {
                //1 was on the same time the first two times
                return 1;
            } else return 2;
        }
    }

    //returns 1 if first half is heavier, 0, if they're the same, -1 if the second half is heavier
    private static int compareTwoGroupsOfFour(  int[] arr, int i0, int i1, int i2, int i3,  int k0, int k1, int k2, int k3) {
        int sideA = arr[i0] + arr[i1] + arr[i2] + arr[i3];
        int sideB = arr[k0] + arr[k1] + arr[k2] + arr[k3];
        return (sideA - sideB);
    }


}