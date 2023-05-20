# Problem Statement
## LeetCode : 11

You are given an integer array  `height`  of length  `n`. There are  `n`  vertical lines drawn such that the two endpoints of the  `ith`  line are  `(i, 0)`  and  `(i, height[i])`.
Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return  _the maximum amount of water a container can store_.

**NOTE**  that you may not slant the container.

---
### Approach 1

It is a two pointer approach.
Lets say, `height` is the given height array
and, 

    lo = 0;
    hi = height.length - 1

**What we will do now??**
We will calculate area at each step and try to track maximum.

**But we will go to next step after we calculate and update current area?**
Basically, we have three scenarios,
*Scenario 1:*
When `height[lo] < height[hi]` 
At this point, area will be `height[lo] * (hi - lo)`. If we think through we will get we already have calculated max area possible with one side as `height[lo]` because, if we keep `lo` same, and reduce `hi` we may get larger height but that will not be of any use, instead area will be `height[lo] * (hi - 1 - lo)` , which is less.

So, we will move lo pointer to next.

*Scenario 2:*
Similar as scenario1, but this time `height[lo] > height[hi]` , in this step area will be `height[hi] * (hi - lo)`, if we keep `hi` as it is, and move lo to next then we will get area as `height[hi] * (hi - (lo + 1))` which will be less only.

*Scenario 3*
When `height[lo] == height[hi]` then move `lo` to `lo + 1` and `hi` to `hi - 1`.
