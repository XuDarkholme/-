void insert(Tree* tree, int value)		 
{
	Node* node = malloc(sizeof(Node));		//创建了一个待插入的节点node
	node->data = value;
	node->left = NULL;
	node->right = NULL;

	if (tree->root == NULL)			
	{
		tree->root = node;			//如果没有根节点，则插入节点为根节点
	}
	else
	{
		Node* temp = tree->root;	//创建一个指向根节点的辅助指针temp，用于比较元素大小
		while (temp != NULL)
		{
			if (value < temp->data)			//比较当前元素和待插入元素，小于放左边，大于放右边
			{
				if (temp->left == NULL)		//如果当前节点左侧为空
				{
					temp->left = node;		//插入于此
					return;
				}
				else
				{
					temp = temp->left;		//继续搜索
				}
			}
			else
			{
				if (temp->right == NULL)	//如果当前节点右侧为空
				{
					temp->right = node;		//插入于此
					return;
				}
				else
				{
					temp = temp->right;		//继续搜索
				}
			}
		}
	}
}
