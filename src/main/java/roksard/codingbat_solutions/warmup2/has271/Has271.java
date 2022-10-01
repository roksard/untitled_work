package roksard.codingbat_solutions.warmup2.has271;

public class Has271 {
    public boolean has271(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int baseValue = nums[i];
            for (int n = i; n < nums.length; n++) {
                int num = nums[n];
                if (n == i) {
                    continue;
                } else if (n == i+1) {
                    if (num == baseValue + 5) {
                        continue;
                    } else {
                        break;
                    }
                } else if (n == i+2) {
                    if ((num >= (baseValue-1-2)) && (num <= (baseValue-1+2)) ) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean has271v1(int[] nums) {
        int value = 0;
        int seqId = 1;
        for (int num : nums) {
            if (seqId == 1) {
                value = num;
                seqId++;
            } else if (seqId == 2) {
                if (num == value + 5) {
                    seqId++;
                } else {
                    seqId = 1;
                    value = num;
                }
            } else {
                if ((num >= (value-1-2)) && (num <= (value-1+2)) ) {
                    return true;
                } else {
                    seqId = 1;
                    value = num;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Has271 obj = new Has271();

        System.out.println(obj.has271(new int[] {2,7,1}));
    }
}
