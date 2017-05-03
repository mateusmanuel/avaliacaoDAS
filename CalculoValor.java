public class CalculoValor {

	private final Acesso acesso;
	private int quantidadeHoras;
	private int quantidadeMinutos;	
	
	public CalculoValor(Acesso acesso){
		this.acesso = acesso;
	}
	
	float compute(){
		calculaQuantidadeHorasMinutos();
		
		if (quantidadeHoras >=9)
			return acesso.VALOR_DIARIA;
		else 
			return calculaValorTotal(quantidadeHoras, quantidadeMinutos);
	}

	private void calculaQuantidadeHorasMinutos() {
		quantidadeHoras = calculaDiferencaQuantidadeHoras();  
		
		if (acesso.horaSaida == acesso.horaEntrada)
			quantidadeMinutos = calculaDiferencaQuantidadeMinutos();
		else if (acesso.horaSaida > acesso.horaEntrada && acesso.minutosEntrada == acesso.minutosSaida){
			quantidadeMinutos = 0;
			quantidadeHoras = calculaDiferencaQuantidadeHoras();
		}
		else if (acesso.horaSaida > acesso.horaEntrada && acesso.minutosEntrada > acesso.minutosSaida) 
			quantidadeMinutos = calculaDiferencaQuantidadeMinutos();
		else if (acesso.horaSaida > acesso.horaEntrada && acesso.minutosSaida < acesso.minutosEntrada){
			final int quantidadeMinutosHorasMaior = acesso.minutosSaida + (60 - acesso.minutosEntrada);
			quantidadeMinutos = quantidadeMinutosHorasMaior;
			quantidadeHoras = calculaDiferencaQuantidadeHoras() - 1;
		}
		else {
			quantidadeHoras = 0;
			quantidadeMinutos = 0;
		}
	}
	
	private float calculaValorTotal(int quantidadeHoras, int quantidadeMinutos){
		float valorTotal = 0;
		
		valorTotal += quantidadeHoras * acesso.VALOR_HORA;
		valorTotal += Math.ceil(quantidadeMinutos / 15.0) * acesso.VALOR_FRACAO;		
		
		return valorTotal;
	}
	
	int calculaDiferencaQuantidadeHoras(){
		return acesso.horaSaida - acesso.horaEntrada;
	}
	
	int calculaDiferencaQuantidadeMinutos(){
		return acesso.minutosSaida - acesso.minutosEntrada;
	}
}
