import java.util.ArrayList;


public class BigInt {
	
	//Attribute
	private ArrayList<Byte> bigNum = new ArrayList<Byte>();
	private boolean mark; // true - positive , false - negative
	//A constant use in the divide method to make it more efficient
	private final int MAX_INT=2147483647; 
	//Constructor to be use by the user to create the object with a string
	public BigInt (String number) throws  IllegalArgumentException{
		for (int i=number.length()-1;i>0; i--) {
			if (number.charAt(i)<'0' || number.charAt(i)>'9') {//make sure the char is a digit
				System.out.println("bad char is"+number.charAt(i));
				throw new IllegalArgumentException();
			}
			this.bigNum.add((byte)(number.charAt(i)-'0'));
		}
		if (number.charAt(0)=='+')
			mark=true;
		else if (number.charAt(0)=='-')
			mark=false;
		else {//Char is not '+' or '-'
			System.out.println("bad mark");
			throw new IllegalArgumentException();
		}
		this.fix();
	}
	//A private constructor for the class use only
	private BigInt() {
		this.mark=true;
	}
	//Add two BigInt
	public BigInt plus (BigInt other) {
		BigInt sum ;
		byte keep=0; //keep the '1' when sum of 2 digits>9
		int dig;
		if (this.mark==true&&other.mark==false) {
			other.mark=true;
			sum = this.minus(other);
			other.mark=false;
		}
		else if(this.mark==false&&other.mark==true) {
			this.mark=true;
			sum = other.minus(this);
			this.mark=false;
		}
		else {//this and other has the same mark
			sum = new BigInt();
			for (int i=0; i<this.bigNum.size() || i<other.bigNum.size() ;i++ ) {
				if (i<this.bigNum.size() && i<other.bigNum.size())
					dig=this.bigNum.get(i)+other.bigNum.get(i)+keep;
				else if (i<this.bigNum.size())
					dig=this.bigNum.get(i)+keep;
				else//other is longer then this
					dig=other.bigNum.get(i)+keep;
				if (dig>9) {
					dig-=10;
					keep=1;
				}
				else
					keep=0;
				sum.bigNum.add((byte)dig);
			}
			if (keep==1)
				sum.bigNum.add((byte)keep);
			sum.mark=this.mark;
		}
		return sum;
	}
	//Subtract other BigInt from this
	public BigInt minus(BigInt other) {
		BigInt sum ;
		byte keep=0;//keep the '-1' when result of 2 digits<0
		int dig;
		if (this.mark==true&&other.mark==false) {
			other.mark=true;
			sum = this.plus(other);
			other.mark=false;
		}
		else if (this.mark==false&&other.mark==true) {
			other.mark=false;
			sum=this.plus(other);
			other.mark=true;
		}
		else if (this.mark==false&&other.mark==false){
			other.mark=true;
			this.mark=true;
			sum=other.minus(this);
			other.mark=false;
			this.mark=false;
		}
		else { //Both positive
			if (this.compareTo(other)<0){//If this smaller then other
				sum=other.minus(this);
				sum.mark=false;
			}
			else {//Both positive and this>other
				sum = new BigInt();
				for (int i=0;i<this.bigNum.size()||i<other.bigNum.size();i++ ) {
					if (i<this.bigNum.size()&& i<other.bigNum.size()) {
						dig=this.bigNum.get(i)-other.bigNum.get(i)-keep;
					}
					else //this is longer then other (cannot be the other way around)
						dig=this.bigNum.get(i)-keep;
					if (dig<0) {
						dig+=10;
						keep=1;
					}
					else
						keep=0;
					sum.bigNum.add((byte)dig);
				}
			}
		}
		sum.fix();
		return sum;
	}
	//If this>other return 1, if other>this return -1, if equal return 0
	public int compareTo(BigInt other) {
		if (this.mark==true&&other.mark==false)
			return 1;
		if (this.mark==false&&other.mark==true)
			return -1;
		if (this.mark==false&&other.mark==false) {
			this.mark=true;
			other.mark=true;
			int i=other.compareTo(this);
			this.mark=false;
			other.mark=false;
			return i;
		}
		//Both numbers are positive
		if (this.bigNum.size()>other.bigNum.size())
			return 1;
		if (this.bigNum.size()<other.bigNum.size())
			return -1;
		//both numbers are positive and the same length
		for (int i=this.bigNum.size()-1; i>=0;i--) {
			if (this.bigNum.get(i)<other.bigNum.get(i))
				return -1;
			if (this.bigNum.get(i)>other.bigNum.get(i))
				return 1;
		}
		return 0;
	}
	//equal check uses polymorfisme base on the compareTo method
	public boolean equal(Object other) {
		if (other instanceof BigInt) 
			return (0==this.compareTo((BigInt) other));
		else
			return false;
	}
	private void fix() {//Remove zeros from the end of the bigNum arrayList, and if the value is 0 mark to '+' (true)
		for (int i=this.bigNum.size()-1;i>0&&this.bigNum.get(i)==0;i--) {
			this.bigNum.remove(i);
		}
		if (this.bigNum.size()==1&&this.bigNum.get(0)==0)//Value is 0
			this.mark=true;
	}
	//Multiply two BigInt
	public BigInt multiply(BigInt other) {
		BigInt mul = new BigInt();
		BigInt temp= new BigInt();
		int keep=0, num, j;
		for (int i=0;i<this.bigNum.size();i++) {
			for (j=0;j<i;j++)
				temp.bigNum.add((byte)0);
			for (j=0;j<other.bigNum.size();j++) {
				num=(this.bigNum.get(i)*other.bigNum.get(j))+keep;
				keep=num/10;
				num=num%10;
				temp.bigNum.add((byte)num);
			}
			if (keep>0)
				temp.bigNum.add((byte)keep);
			mul=mul.plus(temp);
			temp.bigNum.clear();
			keep=0;
		}
		mul.mark=!(this.mark ^ other.mark); //use gate XOR to find mark
		return mul;
	}
	//Divide other BigInt from this
	public BigInt divide(BigInt other)throws ArithmeticException {
		if (other.bigNum.size()==0 ||(other.bigNum.size()==1&&other.bigNum.get(0)==0))//if other is zero
			throw new ArithmeticException();
		int count=0;
		BigInt temp= new BigInt();
		BigInt div= new BigInt();
		boolean opr=this.mark, arg=other.mark; //keeps the marks of this and other
		this.mark=true;
		other.mark=true;
		while (this.compareTo(temp)>0) {
			while (count<MAX_INT && this.compareTo(temp)>0) {//use int count to make the method more efficient, if get bigger than int use plus and start a new count
			temp=temp.plus(other);
			count++;
			}
			if (this.compareTo(temp)<0)
				count--;
			div=div.plus(new BigInt("+"+String.valueOf(count)));//add the count to 'div'
			count=0;
		}
		this.mark=opr;
		other.mark=arg;
		div.mark=!(this.mark ^ other.mark); //use gate XOR to find mark
		div.fix();
		return div;
	}
	//Print the BigInt as String in the format
	public String toString() {
		String str = "";
		if (this.mark)
			str+='+';
		else
			str+='-';
		for (int i=this.bigNum.size()-1; i>=0;i--)
			str+=String.valueOf((int)this.bigNum.get(i));
		return str;
	}
}
