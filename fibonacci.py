'''
Name: Justin Duhaime
Class: CIS 4930
Project: 3 part 3
'''
def list_to_string(the_list):
    """
    takes a list as a parameter and returns a the_string
    with the list's contents
    """
    return "[" + ", ".join(str(x) for x in the_list) + "]"

class Fibonacci(object):
    """
    Fibonacci class holds a list of the fibonacci numbers
    given in the constructur
    overrides the print function and the iterator
    """
    def __init__(self, start_num):
        self.fib_list = []
        self.index = 0
        self.start_num = start_num
        if start_num == 1:
            self.fib_list = [0]
        elif start_num == 2:
            self.fib_list = [0, 1]
        else:
            num_1 = 0
            num_2 = 1
            num_3 = 1
            self.fib_list.append(num_1)
            self.fib_list.append(num_2)
            while start_num > 2:
                num_3 = num_1 + num_2
                num_1 = num_2
                num_2 = num_3
                self.fib_list.append(num_3)
                start_num -= 1

    def __iter__(self):
        """'
        override of the iterator
        """
        return (x for x in self.fib_list)

    def next(self):
        """
        used by the yield to determine the next iterator
        """
        if self.index >= len(self.fib_list):
            raise StopIteration
        ret = self.fib_list[self.index]
        self.index = self.index + 1
        return ret

    def __str__(self):
        """
        override of the print function
        """
        the_string = "The first "
        the_string += str(self.start_num)
        the_string += " Fibonacci numbers are "
        the_string += str(list_to_string(self.fib_list))
        return the_string

    def get_nums(self):
        """
        returns a list of the fibonacci numbers
        """
        return self.fib_list

def fibonacci_gen(num):
    """
    yields the next fibonacci numbers on the fly
    """
    num_1 = 0
    num_2 = 1
    yield num_1
    yield num_2
    while num > 2:
        num_3 = num_1 + num_2
        num_1 = num_2
        num_2 = num_3
        num = num - 1
        yield num_3
