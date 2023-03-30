# write-stars

#### Bir for döngüsü ile aşağıdaki çıktıyı yazar mısınız. 

```
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
    }
    
```

#### Output
<img width="832" alt="Screenshot 2023-03-30 at 16 31 55" src="https://user-images.githubusercontent.com/46796424/228856931-f3924287-0576-4e60-a2ad-8a0ce6f17eaa.png">
