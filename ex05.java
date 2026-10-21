import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercicio5 {

    static String padronizar(String nome) {
        return Arrays.stream(nome.split("\\s+"))
                .map(p -> p.substring(0, 1).toUpperCase() + p.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        Path entrada = Path.of("clientes_bruto.txt");
        Path saida = Path.of("clientes_padronizados.txt");

        try {
            if (!Files.exists(entrada)) {
                Files.write(entrada, List.of(
                        "  maria SILVA ",
                        "",
                        "joao   pereira",
                        "   ",
                        "ANA beatriz  "));
            }

            List<String> tratados;

            try (Stream<String> linhas = Files.lines(entrada)) {
                tratados = linhas
                        .map(String::trim)                      
                        .filter(l -> !l.isBlank())             
                        .map(Exercicio5::padronizar)            
                        .collect(Collectors.toList());
            }

            Files.write(saida, tratados,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Arquivo gerado: " + saida);
            for (String nome : tratados) {
                System.out.println("- " + nome);
            }
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e.getMessage());
        }
    }
}
