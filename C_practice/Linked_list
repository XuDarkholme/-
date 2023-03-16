#include <stdio.h>
#include <stdlib.h>

// 定义链表节点结构体
typedef struct node {
    int data;           // 数据域
    struct node* next;  // 指针域，指向下一个节点
} Node;

// 创建链表函数
Node* createList() {
    Node* head, * p, * q;
    int n, i, data;
    head = (Node*)malloc(sizeof(Node));  // 创建头节点
    head->next = NULL;  // 空链表
    printf("请输入节点数: ");
    scanf_s("%d", &n,sizeof(n));
    for (i = 0, p = head; i < n; i++) {
        printf("请输入第%d个节点的数据: ", i + 1);
        scanf_s("%d", &data,sizeof(data));
        q = (Node*)malloc(sizeof(Node));  // 创建新节点
        q->data = data;
        q->next = NULL;
        p->next = q;  // 将新节点插入到链表尾部
        p = q;
    }
    return head;
}

// 遍历链表函数
void traverseList(Node* head) {
    Node* p = head->next;  // 跳过头节点
    printf("链表节点数据: ");
    while (p != NULL) {
        printf("%d ", p->data);
        p = p->next;
    }
    printf("\n");
}

// 插入节点函数
void insertNode(Node* head, int index, int data) {
    Node* p = head, * q;
    int i;
    for (i = 0; i < index - 1 && p != NULL; i++) {
        p = p->next;
    }
    if (p == NULL) {
        printf("插入位置无效\n");
        return;
    }
    q = (Node*)malloc(sizeof(Node));  // 创建新节点
    q->data = data;
    q->next = p->next;  // 将新节点插入到指定位置
    p->next = q;
}

// 删除节点函数
void deleteNode(Node* head, int index) {
    Node* p = head, * q;
    int i;
    for (i = 0; i < index - 1 && p != NULL; i++) {
        p = p->next;
    }
    if (p == NULL || p->next == NULL) {
        printf("删除位置无效\n");
        return;
    }
    q = p->next;  // 指向待删除节点
    p->next = q->next;  // 将待删除节点从链表中删除
    free(q);
}

int main() {
    Node* head = createList();  
    traverseList(head);         
    insertNode(head, 3, 100);   
    traverseList(head);         
    deleteNode(head, 2);        
    traverseList(head);         
    return 0;
}
