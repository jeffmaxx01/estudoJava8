package estudoJava8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class TesteJava8 {
	
	static int inst;

	public static void main(String[] args) {
		
		long instateInicial = System.currentTimeMillis();
		Instant dataIni = Instant.now();
		
		System.out.println(LocalDateTime.now());
		System.out.println(dataIni);
			
		try {
		Thread.sleep(1000);
		} catch (InterruptedException e) {
		       e.printStackTrace();
		}
		long instanteFinal = System.currentTimeMillis();
		Instant dataFinal = Instant.now();
		Duration duracao = Duration.between(dataIni, dataFinal);
		
		System.out.println("Duração em nanos segundos: " + duracao.toNanos());
		System.out.println("Duração em minutos: " + duracao.toMinutes());
		System.out.println("Duração em horas: " + duracao.toHours());
		System.out.println("Duração em milisegundos: " + duracao.toMillis());
		System.out.println("Duração em dias: " + duracao.toDays());
		
		long duracaoEmMilesegundos = instanteFinal - instateInicial;
		System.out.println("Duração em segundos: " + ( duracaoEmMilesegundos / 1000 ) % 60 );

		LocalDate dataAntes = LocalDate.of(2010, 3, 7);
		LocalDate dataDepois = LocalDate.of(2015, 3, 5);
		
		
		
		System.out.println(dataAntes.isAfter(dataDepois));
		System.out.println(dataAntes.isBefore(dataDepois));
		System.out.println(dataAntes.isEqual(dataDepois));
		System.out.println(dataAntes.compareTo(dataDepois));
		
		Period periodo = Period.between(dataAntes, dataDepois);
		System.out.println(periodo.getYears() + " Anos " + periodo.getMonths() + " Meses " + periodo.getDays() + " Dias"); 
		System.out.println("Apenas meses: " + periodo.toTotalMonths());
		
		LocalDate dataManipulacao = LocalDate.now();
		System.out.println("Mais 5 dias:" + dataManipulacao.plusDays(5));
		System.out.println("Mais 5 semanas:" + dataManipulacao.plusWeeks(5));
		System.out.println("Mais 5 anos:" + dataManipulacao.plusYears(5));
		System.out.println("Mais 2 meses:" + dataManipulacao.plusMonths(2));
		System.out.println("Menos 1 ano:" + dataManipulacao.minusYears(1));
		System.out.println("Menos 1 mês:" + dataManipulacao.minusMonths(1)); 
		System.out.println("Menos 3 dia: " + dataManipulacao.minusDays(3));
		System.out.println("Data Original:" + dataManipulacao);
		
		LocalDateTime horaSp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		LocalDateTime horaParis = LocalDateTime.now(ZoneId.of("Europe/Paris"));

		System.out.println(horaSp);
		System.out.println(horaParis);
		
		Duration diferencaDeHoras = Duration.between(horaSp, horaParis);
		System.out.println(diferencaDeHoras.getSeconds() / 60 / 60);
		ZoneId.getAvailableZoneIds().forEach(s -> System.out.println(s+" "+LocalDateTime.now(ZoneId.of(s)).format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"))));
		
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatadorTraco = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  
		System.out.println("Data com /: " + hoje.format(formatadorBarra));     
		System.out.println("Data com -: " + hoje.format(formatadorTraco));
		
		List<String> palavras = new ArrayList<String>();
		palavras.add("abc");
		palavras.add("bcdd");
		palavras.add("cdeee");
		palavras.add("deffff");

		Consumer<String> comparador = new ConsumidorDeString();
		
		palavras.get(0).startsWith("l", 0);
		palavras.sort(Comparator.comparing(String::length));
		palavras.sort(Comparator.comparing(s -> s.length()));
		palavras.sort(String.CASE_INSENSITIVE_ORDER);
		//palavras.forEach(System.out::println);
		
		List<String> frutas = (List<String>) Arrays.asList("ma�a", "laranja", "limao");
		
//		Arrays.asList("ma�a", "laranja", "limao").stream()
//		.filter(s -> s.startsWith("l", 0)) //filtra as palavras que come�am com a letra l
//		.forEach(System.out::println); // faz um for pelo lista imprimindo as strings
//		
		
		
		palavras.forEach(palavra -> System.out.println(palavra));

		//System.out.println(palavras);
		
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso("Python", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java 8", 113));
		cursos.add(new Curso("C", 55));
		
		Optional<Curso> a = cursos.stream()
		   .filter(c -> c.getAlunos() > 100)
		   .findAny();
		
		int aluno = 0;
				
		a.ifPresent(s -> {inst=s.getAlunos();});
		
	//////	System.out.println("alunos "+inst);
		
		LocalDate dataLocal = LocalDate.now();
		DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss H/24");
		LocalDateTime time = LocalDateTime.now();
		
		System.out.println(formatData.format(dataLocal));
		System.out.println(formatTime.format(time));
		
			
	
	
	
	}

}

class ConsumidorDeString implements Consumer<String> {
	public void accept(String s) {
		System.out.println(s);
	}
}

class Curso {
    private String nome;
    private int alunos;

    public Curso(String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public int getAlunos() {
        return alunos;
    }
}