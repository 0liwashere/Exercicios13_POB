import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class Exercicio1 {
    public static void main(String[] args) {
        Path diretorio = Path.of("dados/relatorios");
        Path arquivo = diretorio.resolve("sumario.txt");

        try {
            if (!Files.exists(diretorio)) {
                Files.createDirectories(diretorio);
            }

            String conteudo = "RELATORIO DE SUMARIO\n"
                    + "====================\n"
                    + "Gerado em: " + LocalDateTime.now() + "\n";

            Files.writeString(arquivo, conteudo,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("Arquivo criado em: " + arquivo);
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e.getMessage());
        }
    }
}
