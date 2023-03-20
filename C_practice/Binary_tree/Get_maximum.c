int get_maximum(Node* node)
{
	int m1 = get_maximum(node->left);
	int m2 = get_maximum(node->left);
	int m3 = node->data;
	int max = m1;
	if (m2 > max)
	{
		max = m2;
	}
	if (m3 > max)
	{
		max = m3;
	}
	return max;
}
