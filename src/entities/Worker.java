package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {

	private String name;
	private WorkerLevel level; //classe da entidade Enums 'WorkLevel (JUNIOR, MID_LEVEL, SENIOR;)'
	private Double baseSalary;
	
	//ASSOCIA��ES ENTRE AS CLASSES
	private Department department; //classe da entidade 'Department' - COMPOSI��O da rela��o (1 Worker possui 1 Departament)
	private List<HourContract> contracts = new ArrayList<>(); //classe da entidade 'HourContract' - COMPOSI��O da rela��o (1 Worker possui varias HourContract)
	
	public Worker() { //construtor vazio
	}

	public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
		//quando se associa uma lista na rela��o (TEM MUITOS) gera o construtor sem a list (List<HourContract> contracts)
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public void setLevel(WorkerLevel level) {
		this.level = level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}

/* o set da lista deve ser substituido pelos m�todos da classe, sen�o, ir� trocar as listas
	public void setContracts(List<HourContract> contracts) {
		this.contracts = contracts;
	}
*/	
// M�TODOS DA CLASSE
	//metodo para fazer a associa��o do trabalhador com o contrato - adicionando um contrato na lista
	public void addContract(HourContract contract) {
		contracts.add(contract);
	}
	
	//metodo para desfazer a associa��o do trabalhador com o contrato  - removendo um contrato na lista
	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}
	
	//m�todo para somar os contratos que correspondem a determinadas datas
	public double income(int year, int month) {
		double sum = baseSalary;
		Calendar cal = Calendar.getInstance(); //instanciando a fun��o 'Calendario'
		
		for (HourContract c : contracts) { //la�o 'for each'
			cal.setTime(c.getDate()); //algoritmo para captar dados de uma data
			int c_year = cal.get(Calendar.YEAR); //declarando vari�vel para pegar o ano da data
			int c_month = 1 + cal.get(Calendar.MONTH); //declarando vari�vel para pegar o m�s da data
			
			if (year == c_year && month == c_month) { //if para comparar a data informada e a data do calend�rio
				sum += c.totalValue(); //se for verdadeiro, a soma ser� armazenada 
			}
		}
		return sum; //retornando a soma
	}
}
