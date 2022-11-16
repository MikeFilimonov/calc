Any equation can contain only two operands and one operator between them.
This calc operates with both Arabic and Roman numbers. Both operands should be of one kind, otherwise the app is terminated having thrown an exception.
Same thing happens if the input is invalid due to either faulty operands or operators. The calc operates with ints in range from 1 to 10.
The kind of input numbers defines the kind of the output.
The quotient fractional part, is always omitted.
The result of the calculation on the Arabic numbers can be positive, negative or of zero value.
The result of the calculation on the Roman numbers can be only positive, otherwise the app throws an exception.

Samples:
1)Input:
1 + 2
Output:
3

2)Input:
VI / III
Output:
II

3)Input:
I - II
Output:
throws Exception //the result can't be negative

4)Input:
I + 1
Output:
throws Exception //both operands should be of one kind

4)Input:
1
Output:
throws Exception //that's not an operation

5)Input:
1 + 2 + 3
Output:
throws Exception //the number of operands and operators exceeds limits in the given equation  