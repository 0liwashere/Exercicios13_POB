import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercicio2 {
    public static void main(String[] args) {
        Path caminho = Path.of("servidor.log");

        try {
            if (!Files.exists(caminho)) {
                Files.write(caminho, List.of(
                        "[INFO] Servidor iniciado",
                        "[ERROR] Falha de conexao",
                        "[INFO] Cliente conectado",
                        "[ERROR] Timeout no banco",
                        "[WARN] Memoria alta"));
            }

            try (Stream<String> linhas = Files.lines(caminho)) {
                List<String> erros = linhas
                        .filter(l -> l.startsWith("[ERROR]"))                    
                        .map(l -> l.replaceFirst("^\\[ERROR\\]\\s*", ""))      
                        .collect(Collectors.toList());

                System.out.println("Total de erros: " + erros.size());
                for (String msg : erros) {
                    System.out.println("- " + msg);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
