import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercicio4 {
    public static void main(String[] args) {
        Path base = Path.of("src"); 

        if (!Files.exists(base)) {
            System.out.println("Pasta nao encontrada: " + base.toAbsolutePath());
            return;
        }

        try (Stream<Path> stream = Files.walk(base)) {
            List<Path> arquivosJava = stream
                    .filter(Files::isRegularFile)                     
                    .filter(p -> p.toString().endsWith(".java"))       
                    .collect(Collectors.toList());

            long totalBytes = 0;

            for (Path p : arquivosJava) {
                long tamanho = Files.size(p);
                System.out.println(base.relativize(p) + " - " + tamanho + " bytes");
                totalBytes += tamanho;
            }

            System.out.println("Arquivos .java encontrados: " + arquivosJava.size());
            System.out.println("Tamanho total: " + totalBytes + " bytes");
        } catch (IOException e) {
            System.out.println("Erro ao varrer o diretorio: " + e.getMessage());
        }
    }
}
