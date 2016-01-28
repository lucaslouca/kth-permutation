# Finding n-th permutation without computing others

Given S of size n and k, return the kth lexicographic permutation sequence. (Note: Given n will be between 1 and 10 inclusive.)

For n=10 variable symbols we have 10! possible permutations. The first step is to find the first symbol.
If we fix a first symbol, then the remaining 9 symbols will have 9! possible permutations. Let i be the index of the 
first fixed symbol. Then k = i*((n-1)!) + r. We want to find i, so that i*((n-1)!) is largest possible number less than or equal to k. Why? Consider the following:
If we fix the symbol 'a' at the first position. That is, the symbol with index i=0 in S we will then have (n-1)! = 9! possible combinations. Which is
362880 and less from what we want. 

If we fix the symbol 'b' at the first position, we will have 362880 permutations (of the preceding permutations that started with 'a')
plus 362880 permutations that start with 'b' (a..., ... a..., b..., ...., b...) which is 2*362880=725760 and less than what we want. 
That is, the words that start with 'b' are in the range [362881, 725760] which doesn't include k=1000000.
  ab..., ..., ac...,  ba..., ..., bc...
 |________________|   |________________|
         |                     |
        362880              362880 (indices 362881 - 725760)
 |_____________________________________|
                    |
                  725760					


If we fix the symbol with index i=2 ('c') we will have a total of number of 3*362880 = 1088640 (a..., b..., c....) which includes k = 1000000. 
In other words, the words that start with 'c' are in the range [725761, 1088640] which includes k=1000000.

  ab..., ..., ac...,  ba..., ..., bc...,   ca..., ..., cb...
 |________________|   |________________|  |________________|
         |                     |                   |
	   362880                362880              362880
 |_________________________________________________________|
                               |
                            1088640	
				  

Obviously if we fix the symbol with index 3, that is, the symbol 'd', the range of the words starting with symbol 'd' will be
[1088641, 1451520] which does not include k=1000000.

In other words, i = Math.floor(k/((n-1)!)) and r = k % (n-1)!.

Let k = 1000000. Then 1000000 = 2*(9!) + 274240. That is the first symbol is the symbol with index 2 in S, which is 'c'. Append 'c' to the result.
For the next step k becomes the rest. That is, k = 1000000 % 9! = 274240. Then, remove 'c' from S, so S becomes [a,b,d,e,f,g,h,i,j] and n = 9. 

The next symbol index i = Math.floor(274240/(8!)) = 6 which is 'h'. Append 'h' to the result. k becomes 32320. Remove 'h' from S so that S becomes [a,b,d,e,f,g,i,j] with n=8.
Repeat these steps until n = 0.
