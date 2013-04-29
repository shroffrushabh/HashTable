import java.io.*;

class HashTable
{
   static int size=400;
   LinkedList lst[];
	
   public HashTable()
   {
	lst=new LinkedList[size];
   }

   public static long hashCode(String str)
   {
      int b = 378551;
      int a = 63689;
      long hash = 0;
      for(int i = 0; i < 3; i++)
      {
	 hash = hash * a + str.charAt(i);
         a = a * b;
      }
      return hash%size;
   }
   
   public static long hashedCode(String str)
   {
      long seed = 131; 
      long hash = 0;
      for(int i = 0; i < str.length()%5; i++)
         hash = (hash * seed) + str.charAt(i);
      return hash%size;
   }
	
   public boolean checkCollision(int code)
   {
	if (lst[code]==null)
	  return false;
	else return true;
   }

   public void insert(String key,int val)
   {
        int code=(int)hashedCode(key);
	if(!checkCollision(code))
	{
	lst[code] = new LinkedList();
	lst[code].insert(key,val);
	}
	else 
	{
	System.out.println("Collision!!!");
	lst[code].insert(key,val);
	}
   }
   
   public int search(String key)
   {
        int code=(int)hashedCode(key);
	Node tmp=lst[code].search(key);
	if(tmp == null)
	return -1;
	return tmp.getData();
   }
   public void display()
   {
	for(int i=0;i<size;i++)
	{
	if(lst[i]!=null)
	lst[i].display();
	}	
        System.out.println();
   }	

   public void delete(String key)
   {
	int code=(int)hashedCode(key);
	lst[code].delete(key);
   }
	
   public static void main(String [] args) throws Exception
   {
	BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	HashTable ht=new HashTable();
	
	while(true)
	{
	System.out.println("1. Insert into hash table\n2. Search hash table\n3. Delete key/value pair from hash table\n4. Display Hashtable\n5. Insert from file");
	int choice = Integer.parseInt(br.readLine());
	
	switch(choice)
	{
	case 1:
	System.out.println("Enter Key:");
	String key=br.readLine();
	
	System.out.println("Enter Value:");
	int val=Integer.parseInt(br.readLine());
	
	ht.insert(key,val);
	break;

	case 3:
        System.out.println("Enter Key:");
        key=br.readLine();

	ht.delete(key);
	break;
		
	case 4:
	ht.display();
	break;
	
	case 5:
        FileInputStream fstream = new FileInputStream("sample.txt");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader brr = new BufferedReader(new InputStreamReader(in));
  	String strLine;
 	while ((strLine = brr.readLine()) != null) {
	ht.insert(strLine,1);  	 
  	}
	in.close();
	break;	

	default:
	System.out.println("Enter key value:");
        key=br.readLine();
	int value=ht.search(key);
	if(value!=-1)
	System.out.println("Value is:"+value);
	else System.out.println("Key not present in the hash table.");
	break;
	}
	}

   }
}
