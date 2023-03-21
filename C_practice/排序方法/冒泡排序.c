void bubble(int arr[], int size)
{
	int j,i;
	int temp;
	for (j = 0; j < size - 1; j++)
	{
		for (i = 0; i < size - 1 - j; i++)
		{
			if (arr[i] > arr[i + 1])
			{
				temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}
		}
	}	
}
