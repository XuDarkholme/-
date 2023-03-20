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
