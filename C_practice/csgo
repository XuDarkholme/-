#include <stdio.h>

void main()
{
	printf("-计算CSGO倒余额折扣率\n-输入0退出程序\n\n");
	while (1)
	{
		double price_pt = 0;			//支出费用
		double price_sc = 0;			//市场价格
		double price_me = 0;			//我收到的余额
		printf("支出费用：");
		scanf_s("%lf", &price_pt, sizeof(price_pt));
		if (price_pt == 0)
		{
			break;
		}
		printf("市场价格：");
		scanf_s("%lf", &price_sc, sizeof(price_sc));
		if (price_sc == 0)
		{
			break;
		}
		price_me = price_sc / 1.15;
		printf("你获得的余额为：%lf\n",price_me);
		printf("折扣率为:\t%lf\n\n",price_pt/price_me);
	}
}
