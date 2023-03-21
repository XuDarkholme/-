int get_height(Node* node)
{
	if (node == NULL)
	{
		return 0;
	}
	else
	{
		int left_h = get_height(node->left);
		int right_h = get_height(node->right);
		int max = left_h;
		if (right_h > max)
		{
			max = right_h;
		}
		return max + 1;
	}
}
