# TwelveIslandersPuzzle
The 12 Islanders Puzzle from the show Brooklyn 99.

The java file contains a working function which given an integer array of the twelve men, 
returns the index of the man with the differing weight. 
Only 3 comparisons are used. It also contains a function creating the array with custom 
weights and for a given index of the outlier and numerous test cases showing that our function works.

The trick is to that no two islanders are on the same side of the seesaw in all three comparisons.
For example, 11 is only in the third round. This is not the case for any other villager, so if the first two
rounds are even, our outlier must be 11.
0 is on the left in all test cases, so all three rounds must be weighted in the same direction etc.
