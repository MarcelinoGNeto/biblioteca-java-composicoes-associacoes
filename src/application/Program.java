package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy"); //mascara para receber valores do tipo data
		
		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String workerName = sc.nextLine();
		System.out.print("Level: ");
		String workerLevel = sc.nextLine();
		System.out.print("Base Salary: ");
		double baseSalary = sc.nextDouble();
		
		//instanciar as variaveis do programa com as classes
		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));
		
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		
		for (int i=1; i<n; i++) {
			System.out.println("Enter contract #" + i + "data:");
			System.out.print("Date (DD/MM/YYYY): ");
			Date contractDate = sdf.parse(sc.next());
			System.out.print("Value per hour: ");
			double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();		
			
			HourContract contract = new HourContract(contractDate, valuePerHour, hours);
			worker.addContract(contract); //associa��o: adicionando um contrato ao trabalhador
		}
		
		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		String monthAndYear = sc.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2)); //armazenar nesta vari�vel os dois primeiros valores da data informada ('08'/2018)
		int year = Integer.parseInt(monthAndYear.substring(3)); //armazenar nesta variavel todos os valores a partir do terceiro caractere (08/'2018')
		System.out.println("Name: " + worker.getName()); //mostrar no console o trabalhador
		System.out.println("Department: " + worker.getDepartment().getName()); //mostrar no console o departamento do trabalhador
		System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

			
		sc.close();
	}

}
