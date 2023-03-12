#include <stdio.h>
#include <string.h>

struct MyFamilyAccount
{
	int flag;				//用于判断有无至少一条收支信息
	char details[3000];		//详情信息
	double balance;			//账户余额
};

char key = ' ';				//输入菜单选项
int loop = 1;				//控制是否退出菜单
char note[20] = "";			//收支说明
double money = 0.0;			//金额
char temp[100] = "";		//用于临时存放收支信息
char choice = ' ';			//用于选择Y/N



void mingxi(struct MyFamilyAccount* myFamilyAccount)
{
	if ((*myFamilyAccount).flag) { printf("%s", (*myFamilyAccount).details); }
	else { printf("当前暂无收支信息，余额%.2f元", (*myFamilyAccount).balance); }
}


void shouru(struct MyFamilyAccount* myFamilyAccount)
{
	printf("\n 本次收入金额：");
	scanf_s("%lf", &money, sizeof(note) / sizeof(note[0]));
	getchar();
	(*myFamilyAccount).balance += money;
	printf("\n 本次收入说明：");
	scanf_s("%s", &note, sizeof(note));
	getchar();
	sprintf_s(temp, sizeof(temp), "\n收入\t%2.f\t\t%.2f\t%s", money, (*myFamilyAccount).balance, note);
	strcat_s((*myFamilyAccount).details, sizeof((*myFamilyAccount).details), temp);
	(*myFamilyAccount).flag = 1;
}


void zhichu(struct MyFamilyAccount* myFamilyAccount)
{
	printf("\n本次支出金额：");
	scanf_s("%lf", &money, sizeof(note) / sizeof(note[0]));
	getchar();
	if (money <= (*myFamilyAccount).balance)
	{
		(*myFamilyAccount).balance -= money;
		printf("\n本次支出说明：");
		scanf_s("%s", &note, sizeof(note));
		getchar();
		sprintf_s(temp, sizeof(temp), "\n支出\t%2.f\t\t%.2f\t%s", money, (*myFamilyAccount).balance, note);
		strcat_s((*myFamilyAccount).details, sizeof((*myFamilyAccount).details), temp);
		(*myFamilyAccount).flag = 1;
	}
	else if (money > (*myFamilyAccount).balance)
	{
		printf("你的余额不足！");
	}
}


void tuichu()
{
youyou:
	printf("你确定要退出吗？（Y/N）");
	scanf_s("%c", &choice, sizeof(choice));
	getchar();
	if (choice == 'Y')
	{
		loop = 0;
	}
	else if (choice == 'N')
	{
		return;
	}
	else
	{
		printf("\n你的输入有误，请重新输入！\n");
		goto youyou;
	}
}


void mainMenu(struct MyFamilyAccount * myFamilyAccount)				//用于显示菜单
{
	do
	{
		printf("\n\n--------家庭收支记账软件--------");
		printf("\n\t1、收支明细");
		printf("\n\t2、登记收入");
		printf("\n\t3、登记支出");
		printf("\n\t4、退	 出");
		printf("\n请选择（1-4）：");
		scanf_s("%c", &key, sizeof(key));
		getchar();

		switch (key)
		{
		case'1':
			mingxi(myFamilyAccount);
			break;

		case'2':
			shouru(myFamilyAccount);
			break;

		case'3':
			zhichu(myFamilyAccount);
			break;

		case'4':
			tuichu();
			;
		}
	} while (loop);
	printf("-已退出软件\n\n");
}


void main()
{
	//初始化结构体
	struct MyFamilyAccount MFA;
	memset(MFA.details, 3000, 0);
	strcpy_s(MFA.details,sizeof(MFA.details), "\n--------家庭收支明细查询--------\n收支\t收支金额\t余额\t备注");
	MFA.flag = 0;
	MFA.balance = 1000.0;

	//调用菜单函数
	mainMenu(&MFA);
}
