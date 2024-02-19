import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Main();
	}
	
	ArrayList<String> employeeIDList = new ArrayList<String>();
	ArrayList<String> employeeNameList = new ArrayList<String>();
	ArrayList<String> genderList = new ArrayList<String>();
	ArrayList<String> positionList = new ArrayList<String>();
	ArrayList<Integer> wageList = new ArrayList<Integer>();
	
	int managerCount=0, supervisorCount=0, adminCount=0;
	
	public Main() {
		int choice =0;
		do {
			choice = Menu(scan);
			scan.nextLine();
			switch(choice) {
			case 1:
				Input();
				break;
			case 2:
				View();
				break;
			case 3:
				Update();
				break;
			case 4:
				Delete();
				break;
			case 5:
				System.out.println("Have a nice day");
				System.exit(0);
			default:
				System.out.println("Please choose a number from 1 to 5");
				break;
			}
		}while(choice!=5);
	}
	
	public void Input() {
		String employeeID, employeeName, gender, position;
		int wage;
		do {
			System.out.print("Employee Name [>=3]: ");
			employeeName = scan.nextLine();
		}while(employeeName.length()<3);
		
		do {
			System.out.print("Gender [Male | Female] (Case Sensitive): ");
			gender = scan.nextLine();
		}while(!(gender.equals("Male") || gender.equals("Female")));
		
		do {
			System.out.print("Position [Manager | Supervisor | Admin] (Case Sensitive): ");
			position = scan.nextLine();
		}while(!(position.equals("Manager") || position.equals("Supervisor") || position.equals("Admin")));
		
		if(position.equals("Manager")) {
			wage = 8000000;
			managerCount+=1;
		}else if(position.equals("Supervisor")) {
			wage = 6000000;
			supervisorCount+=1;
		}else {
			wage = 4000000;
			adminCount += 1;
		}
		
		
		double bonus = 0.0;
		
		if(managerCount==4) {
			bonus = 0.10;
			for(int i=0; i<wageList.size(); i++) {
				if(positionList.get(i).equals("Manager"))
					wageList.set(i, wageList.get(i) + (int) (wageList.get(i) * bonus));
			}
			managerCount=1;
		}else if(supervisorCount==4) {
			bonus = 0.075;
			for(int i=0; i<wageList.size(); i++) {
				if(positionList.get(i).equals("Supervisor"))
					wageList.set(i, wageList.get(i) + (int) (wageList.get(i) * bonus));
			}
			supervisorCount=1;
		}else if(adminCount==4) {
			bonus = 0.05;
			for(int i=0; i<wageList.size(); i++) {
				if(positionList.get(i).equals("Admin"))
					wageList.set(i, wageList.get(i) + (int) (wageList.get(i) * bonus));
			}
			adminCount=1;
		}
		
		Random rand = new Random();
		employeeID = "" + (char)(rand.nextInt(26)+'A') + (char)(rand.nextInt(26)+'A') + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10)+ rand.nextInt(10);
		
		employeeIDList.add(employeeID);
		employeeNameList.add(employeeName);
		genderList.add(gender);
		positionList.add(position);
		wageList.add(wage);
		
		sortAscending();
		
		System.out.println("Successfully added employee with ID " + employeeID);
		System.out.println("Press ENTER to return");
		scan.nextLine();
	}
	
	public void sortAscending() {
		int size = employeeNameList.size();
		for(int i=0; i<size; i++) {
			for(int j=0; j<size-i-1; j++) {
				if(employeeNameList.get(j).compareToIgnoreCase(employeeNameList.get(j+1))>0){
					String temp = employeeIDList.get(j);
					employeeIDList.set(j, employeeIDList.get(j+1));
					employeeIDList.set(j+1, temp);
					
					temp = employeeNameList.get(j);
					employeeNameList.set(j, employeeNameList.get(j+1));
					employeeNameList.set(j+1, temp);
					
					temp = genderList.get(j);
					genderList.set(j, genderList.get(j+1));
					genderList.set(j+1, temp);
					
					temp = positionList.get(j);
					positionList.set(j, positionList.get(j+1));
					positionList.set(j+1, temp);
					
					int tempWage = wageList.get(j);
					wageList.set(j, wageList.get(j+1));
					wageList.set(j+1, tempWage);
				}
			}	
		}
	}
	
	public void Display() {
		if(employeeNameList.isEmpty())
			System.out.println("No data yet");
		else {
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
			System.out.println("|No  |Employee ID      |Employee Name                 |Gender         |Position      |Wage         |");
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
			int count = employeeNameList.size();
			for(int i=0; i<count; i++)
				System.out.printf("|%4d|%17s|%30s|%15s|%14s|%13d|\n", i+1, employeeIDList.get(i), employeeNameList.get(i), genderList.get(i), positionList.get(i), wageList.get(i));
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");	
		}
	}
	
	public void View() {
		Display();
		System.out.println("Press ENTER to return");
		scan.nextLine();
	}
	
	public void Update() {
		Display();
		if(employeeNameList.isEmpty()) {
			System.out.println("Press ENTER to return");
			scan.nextLine();
		}else {
			int number;
				
			do {
				System.out.print("Enter index to update[1-" + employeeNameList.size()+"]: ");
				number = scan.nextInt();
			}while(number<1 || number>employeeNameList.size());
			
			number -= 1;
			
			String employeeName, gender, position;
			int wage=0;
			
			do {
				System.out.print("Employee Name [>=3]: ");
				scan.nextLine();
				employeeName = scan.nextLine();
				if(employeeName.equals("0")) break;
				else
					employeeNameList.set(number, employeeName);
			}while(employeeName.length()<3);
			
			do {
				System.out.print("Gender [Male | Female] (Case Sensitive): ");
				gender = scan.nextLine();
				if(gender.equals("0")) break;
				else
					genderList.set(number, gender);
			}while(!(gender.equals("Male") || gender.equals("Female")));
			
			do {
				System.out.print("Position [Manager | Supervisor | Admin] (Case Sensitive): ");
				position = scan.nextLine();
				if(position.equals("0")) break;
				else
					positionList.set(number, position);
			}while(!(position.equals("Manager") || position.equals("Supervisor") || position.equals("Admin")));
			
			if(position.equals("Manager")) {
				wage = 8000000;
				managerCount+=1;
			}else if(position.equals("Supervisor")) {
				wage = 6000000;
				supervisorCount+=1;
			}else if(position.equals("Admin")){
				wage = 4000000;
				adminCount += 1;
			}
				
			
			double bonus = 0.0;
			
			if(managerCount==4) {
				bonus = 0.10;
				for(int i=0; i<wageList.size(); i++) {
					if(positionList.get(i).equals("Manager"))
						wageList.set(i, wageList.get(i) + (int) (wageList.get(i) * bonus));
				}
				managerCount=1;
			}else if(supervisorCount==4) {
				bonus = 0.075;
				for(int i=0; i<wageList.size(); i++) {
					if(positionList.get(i).equals("Supervisor"))
						wageList.set(i, wageList.get(i) + (int) (wageList.get(i) * bonus));
				}
				supervisorCount=1;
			}else if(adminCount==4) {
				bonus = 0.05;
				for(int i=0; i<wageList.size(); i++) {
					if(positionList.get(i).equals("Admin"))
						wageList.set(i, wageList.get(i) + (int) (wageList.get(i) * bonus));
				}
				adminCount=1;
			}
			
			if(wage!=0)
				wageList.set(number, wage);
			
			sortAscending();
			
			System.out.println("Successfuly updated employee with ID " + employeeIDList.get(number));
			System.out.println("Press ENTER to return");
			scan.nextLine();
		}
	}
	
	public void Delete() {
		Display();
		if(employeeNameList.isEmpty()) {
			System.out.println("Press ENTER to return");
			scan.nextLine();
		}else {
			int number;
			
			do {
				System.out.print("Enter index to delete[1-" + employeeNameList.size()+"]: ");
				number = scan.nextInt();
			}while(number<1 || number>employeeNameList.size());
			
			number -= 1;
			
			System.out.println("Employee with ID " + employeeIDList.get(number) + " successfully deleted");
			
			employeeIDList.remove(number);
			employeeNameList.remove(number);
			genderList.remove(number);
			positionList.remove(number);
			wageList.remove(number);
			
			System.out.println("Returning Immediately");
			System.out.println();
		}
	}
	
	public int Menu(Scanner scan) {	
		System.out.println("Welcome to PT ChipiChapa");
		System.out.println("------------------------");
		System.out.println("1. Input Employee Data");
		System.out.println("2. View Employee Data");
		System.out.println("3. Update Employee Data");
		System.out.println("4. Delete Employee Data");
		System.out.println("5. Exit");
		System.out.print("What do you want to do? ");
		return scan.nextInt();
	}
}
