
package problem_solve;

import java.lang.Math;
import java.util.Scanner;
class SparseTree{
    long [][] tree;
    SparseTree(long [] array)
    {
        int n = array.length;
        int m= (int) Math.ceil(Math.log(n));
        tree = new long [n+5][m+5];
        for(int j = 0;j<=m;j++)
        {
            for(int i=0; i+(1<<j)-1<n; i++)
            {
                if(j==0)
                {
                    tree[i][j] = array[i];
                    continue;
                }
//                System.out.println("J : "+j+", I : "+i+" "+(1<<(j-1)));
//                System.out.println(""+tree[i][j-1]);
//                System.out.println(""+tree[i+(1<<(j-1))][j-1]);
                tree[i][j] = Math.min(tree[i][j-1],tree[i+(1<<(j-1))][j-1]);
//                System.out.println("I : "+(i+1)+" to J : "+(i+(1<<j)));
//                System.out.println(""+tree[i][j]);
            }
        }
    }
    public long RangeMinimumQuery(int l,int r)
    {
        int k = (int) Math.log(r-l+1);
        long ans = Math.min(tree[l][k], tree[r-(1<<k)+1][k]);
        return ans;
    }
}
public class Problem_solve {

    public static void main(String[] args) {
        long [] array = { 81,17,54,55,68,29,71,8};
        SparseTree obj = new SparseTree(array);
        int query,left,right;
        Scanner myobj = new Scanner(System.in);
        query = myobj.nextInt();
        while(query>0)
        {
            left = myobj.nextInt();
            right = myobj.nextInt();
            System.out.println("Answer : "+obj.RangeMinimumQuery(left-1, right-1));
            query--;
        }
    }
}
