#include <stdio.h> 
#include <string.h>


// 定义结构体
struct Customer
{
	int id;				//用户编号
	int age;			//年龄
	char name[10];		//姓名
	char gender;		//性别
	char phone[16];		//电话号码
	char email[20];		//邮箱
};


// 定义初始结构体数组
struct Customer customers[100];


int loop = 1;			//控制是否退出主菜单
int key;				//菜单选项
int customerNum = 1;	//当前客户数量
char choice;			//确认是否退出主菜单


// 单个客户的信息
void GetInfo(struct Customer *p)
{
	printf("\n%d\t%s\t%c\t%d\t%s\t%s", p->id, p->name, p->gender, p->age, p->phone, p->email);
}


// 根据输入的id找到对应的索引号
int findIndex(int id)
{
	int index = -1;
	for (int i = 0; i < customerNum; i++)
	{
		if (customers[i].id == id)
		{
			index = i;
		}
	}
	return index;
}


// 判断目标是否能被删除，并完成删除后的序号更改
int del(int id)
{
	int index = findIndex(id);
	if (index == -1)
	{
		return 0;
	}
	else
	{
		for (int i = index + 1; i < customerNum; i++)
		{
			customers[i - 1] = customers[i];
			customers[i - 1].id--;
		}
		customerNum--;
		return 1;
	}
}


// 1、添加客户
void add()
{
	customers[customerNum].id = customerNum + 1;
	printf("\n------------------添加客户------------------");
	printf("\n姓名：");
	scanf_s("%s", customers[customerNum].name,sizeof(customers[customerNum].name));
	getchar();
	printf("\n性别()：");
	scanf_s("%c", &(customers[customerNum].gender), sizeof(customers[customerNum].gender));
	getchar();
	printf("\n年龄：");
	scanf_s("%d", &(customers[customerNum].age), sizeof(customers[customerNum].age));
	getchar();
	printf("\n电话：");
	scanf_s("%s", customers[customerNum].phone, sizeof(customers[customerNum].phone));
	getchar();
	printf("\n邮箱：");
	scanf_s("%s", &(customers[customerNum].email), sizeof(customers[customerNum].email));
	getchar();
	printf("\n-添加完成");
	customerNum++;
}


// 2、删除用户功能
void delView()
{	
	int id;
	char choice = ' ';
	printf("\n------------------删除客户------------------");
	printf("\n请输入要删除的客户编号(输入-1退出)：");
	scanf_s("%d", &id);
	getchar();
	if (id == -1)
	{
		printf("\n-取消删除");
		return;
	}
	printf("\n确定删除用户%d%s吗？",id, customers[id].name);
	printf("\n确定(Y) 取消(N)：");
	scanf_s("%c", &choice,sizeof(choice));
	getchar();
	if (choice == 'Y')
	{
		if (!del(id))
		{
			printf("-用户不存在");
		}
		else
		{
			printf("-删除成功");
		}
	}
}


// 3、修改客户信息
void Change()
{
	int id;
	printf("\n------------------修改客户信息------------------");
	printf("\n请输入要修改的客户编号：");
	scanf_s("%d", &id);
	int index = findIndex(id);
	if (index == -1) {
		printf("\n客户不存在！");
		return;
	}
	printf("\n当前客户信息如下：\n");
	GetInfo(&customers[index]);
	printf("\n请输入新的客户信息：");
	printf("\n姓名：");
	scanf_s("%s", customers[index].name,sizeof(customers[index].name));
	printf("\n性别(M/F)：");
	scanf_s(" %c", &customers[index].gender,sizeof(customers[index].gender));
	printf("\n年龄：");
	scanf_s("%d", &customers[index].age, sizeof(customers[index].age));
	printf("\n电话号码：");
	scanf_s("%s", customers[index].phone, sizeof(customers[index].phone));
	printf("\n邮箱：");
	scanf_s("%s", customers[index].email, sizeof(customers[index].email));
	printf("\n客户信息修改成功！修改后的信息如下：\n");
	GetInfo(&customers[index]);
}


// 4、显示客户信息列表
void List()
{
	printf("\n------------------客户列表------------------");
	printf("\n编号\t姓名\t性别\t年龄\t电话\t邮箱");
	for (int i = 0; i < customerNum; i++)
	{
		GetInfo(&customers[i]);
	}
}


// 显示主菜单
void mainMenu()
{
	do
	{
		printf("\n--------------客户信息管理软件--------------");
		printf("\n\t      1、添 加 客 户");
		printf("\n\t      2、删 除 客 户");
		printf("\n\t      3、修 改 客 户");
		printf("\n\t      4、客 户 列 表");
		printf("\n\t      5、退       出");
		printf("\n请选择(1-5):");
		scanf_s(" %d", &key,sizeof(key));
		getchar();

		switch (key)
		{
		case 1:
			add();
			break;
		case 2:
			delView();
			break;
		case 3:
			Change();
			break;
		case 4:
			List();
			break;
		case 5:
			do
			{
				printf("\n你确定要退出吗？");
				printf("\n确定(Y) 取消(N)：");
				scanf_s("%c", &choice, sizeof(choice));
				getchar();
			} while (choice!='Y'&&choice!='N');
			if (choice == 'Y')
			{
				loop = 0;
			}
			break;
		default:
			printf("\n 输入有误，请重新输入！");
		}
	} while (loop);
	printf( "\n-已退出客户管理系统" );
}

void main()
{
	customers[0].id = 1;
	customers[0].age = 10;
	strcpy_s(customers[0].email, sizeof(customers[0].email), "yy@gmail.com");
	customers[0].gender = 'M';
	strcpy_s(customers[0].name, sizeof(customers[0].name), "yyk");
	strcpy_s(customers[0].phone, sizeof(customers[0].phone), "110");
	mainMenu();
}
