# Finding n-th permutation without computing others



Given `S` of size `n` and `k`, return the `k`th lexicographic permutation sequence. (Note: Given `n` will be between `1` and `10` inclusive.)

For `n = 10` variable symbols we have `10!` possible permutations. The first step is to find the first symbol.
If we fix a first symbol, then the remaining `9` symbols will have `9!` possible permutations. Let `i` be the index (in S) of the 
first fixed symbol. Then `k = i*((n-1)!) + r`. We want to find `i`, so that `i*((n-1)!)` is the largest possible number less than or equal to `k`. Why? Consider the following:

Let `k=1000000`. If we fix the symbol `'a'` at the first position. That is, the symbol with index `i = 0` in `S` we will then have `(n-1)! = 9!` possible combinations. Which is `362880` and less from what we want. 

If we fix the symbol `'b'` at the first position, we will have `362880` permutations (of the preceding permutations that started with `'a'`)
plus `362880` permutations that start with `'b'` (`a..., ... a..., b..., ...., b...`) which is `2*362880=725760` and less than what we want. 
That is, the words that start with `'b'` are in the range `[362880, 725759]` which doesn't include `k=1000000`:
```
  ab..., ..., ac...,  ba..., ..., bc...
  
 |________________|   |________________|
 
         |                     |
	 
 362880 (0-362879)        362880 (permutation 362880 - 725759)
 
 |_____________________________________|
 
                    |
		    
                  725760					

```
If we fix the symbol with index `i = 2` (`'c'`) we will have a total number of `3*362880 = 1088640` permutations (`a..., b..., c....`) which includes `k = 1000000`. In other words, the words that start with `'c'` are in the range `[725760, 1088639]` which includes `k=1000000`:
```
  ab..., ..., ac...,  ba..., ..., bc...,   ca..., ..., cb...
  
 |________________|   |________________|  |________________|
 
         |                     |                   |
	 
       362880                362880              362880
       
 |_________________________________________________________|
 
                               |
			       
                            1088640	
				  
```
Obviously if we fix the symbol with index `i = 3`, that is, the symbol `'d'`, the range of the words starting with symbol `'d'` will be
`[1088640, 1451519]` which does not include `k=1000000`.

The *block* in which `k` is located or the index `i` of the symbol with which the `k`th permutation starts is:
```
i = Math.floor(k/((n-1)!)) and r = k % (n-1)!
```

**Example:**

Let `k = 1000000`. Then `1000000 = 2*(9!) + 274240`. That is the first symbol is the symbol with index `2` in `S`, which is `'c'`. Append `'c'` to the result. Then, remove `'c'` from `S` because we have used this symbol, so `S` becomes `[a,b,d,e,f,g,h,i,j]` and `n = 9`. For the next step, `k` becomes the rest `274240` because we now want to find the `274240`th permutation in the `2`nd block (permutations that start with `'c'`). That is, `k = 1000000 % 9! = 274240`.

The next symbol index is `i = Math.floor(274240/(8!)) = 6` which is `'h'` (remember we have one symbol less in the `S` that is why `(n-1)! = 8 !`). Append `'h'` to the result. Remove `'h'` from `S` so that `S` becomes `[a,b,d,e,f,g,i,j]` with `n = 8`. `k` becomes `32320`. 

Repeat these steps until `n = 0`.
