name = input('请输入您的用户名：')
money = 5000000


def menu():
    """
    这个函数用于主菜单
    """
    print('---------------主菜单---------------')
    print(f'{name}，你好，欢迎来到银行ATM，请选择操作：')
    print('查询余额\t[输入1]\n存款\t\t[输入2]\n取款\t\t[输入3]\n退出\t\t[输入4]')


def query():
    """
    这个函数用于查询余额
    """
    print('----------------查询----------------')
    print(f'{name}，您好，您的账户余额为：{money}元')


def save():
    """
    这个函数用于存钱
    :return:返回的值为存钱后的余额
    """
    print('----------------存款----------------')
    global money
    x = int(input('请输入您的存款额：'))
    money += x
    print(f'{name}，您好，您存款{x}元成功')
    print(f'您的账户剩余{money}元')
    return money


def take():
    """
    这个函数用于取钱
    :return: 返回的值为取钱后的余额
    """
    print('----------------取款----------------')
    global money
    y = int(input('请输入您的取款额：'))
    money -= y
    print(f'{name}，您好，您取款{y}元成功')
    print(f'您的账户剩余{money}元')
    return money


z = 0
while z < 4:
    menu()
    z = int(input('请输入您的选择：'))
    if z == 1:
        query()
    elif z == 2:
        save()
    elif z == 3:
        take()
    else:
        break
print('感谢您的使用，再见')
