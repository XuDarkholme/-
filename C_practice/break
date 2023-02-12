#include <stdio.h>
#include <stdlib.h>

void main(){
	int money = 100000;
	int money_all = money;
	int count = 0;
	while(1){
		if(money>50000){
			money -= money*0.05;
			count++;
		}
		else if(money<=50000){
			if(money-1000<=0)
				break;
			money -= 1000;
			count++;
		}
	}
	printf("%d元可以经过%d次,还剩%d元",money_all,count,money);
	getchar();
	getchar();

}
