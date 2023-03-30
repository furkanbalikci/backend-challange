# write-stars


```
Bir for döngüsü ile aşağıdaki çıktıyı yazar mısınız. 

*
**
****
******
********
**********

##Code

public static void writeStars(int rowNumber) {
        //1,2,4,6,8,10
        int n = 1;
        int k = 1;
        while (n <= rowNumber) {
            for (int i = 0; i < k; i++) {
                System.out.print("*");
            }
            System.out.println();
            k = n*2;
            n++;
        }
    }```


#### Output
