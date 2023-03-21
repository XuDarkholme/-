#include <stdio.h>

void main(){

```c
int i = 0;//控制层数
int j = 0;//控制砖块
int s = 8;//总层数
for(i=1;i<=s;i++){
	for(j=1;j<=s-i;j++){
		printf(" ");
	}
	for(j=1;j<=2*i-1;j++){
		if(j==1||j==2*i-1)
			printf("*");
		else
			printf(" ");
	}
	printf("\n");
}
for(i=s;i>0;i--){
	for(j=s;j>i;j--){
		printf(" ");
	}
	for(j=s;j>s-2*i+1;j--){
		printf("*");
	}
	printf("\n");
}
getchar();
getchar();
```

}
