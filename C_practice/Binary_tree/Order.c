void preorder(Node*node)	//前序遍历
{
	if (node != NULL)
	{
		printf("%d\t", node->data);
		preorder(node->left);
		preorder(node->right);
	}
}

void inorder(Node* node)	//中序遍历
{
	if (node != NULL)
	{
		inorder(node->left);
		printf("%d\t", node->data);
		inorder(node->right);
	}
}

void postorder(Node* node)	//后序遍历
{
	if (node != NULL)
	{
		postorder(node->left);
		postorder(node->right);
		printf("%d\t", node->data);
	}
}
