import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Exercicio3 {
    public static void main(String[] args) {
        Path caminho = Path.of("produtos.csv");

        try {
            if (!Files.exists(caminho)) {
                Files.write(caminho, List.of(
                        "Nome,Quantidade,PrecoUnitario",
                        "Monitor,5,1200.00",
                        "Mouse,10,80.50",
                        "Teclado,abc,150.00",
                        "Headset,3,220.00"));
            }

            List<String> linhas = Files.readAllLines(caminho);

            double totalInventario = 0;

            for (int i = 1; i < linhas.size(); i++) {
                String linha = linhas.get(i);
                if (linha.isBlank()) {
                    continue;
                }

                String[] campos = linha.split(",");
                if (campos.length < 3) {
                    System.out.println("Linha " + (i + 1) + " ignorada (campos insuficientes): " + linha);
                    continue;
                }

                try {
                    String nome = campos[0].trim();
                    int quantidade = Integer.parseInt(campos[1].trim());
                    double preco = Double.parseDouble(campos[2].trim());
                    double subtotal = quantidade * preco;

                    System.out.printf("%s: %d x %.2f = %.2f%n", nome, quantidade, preco, subtotal);
                    totalInventario += subtotal;
                } catch (NumberFormatException e) {
                    System.out.println("Linha " + (i + 1) + " ignorada (numero invalido): " + linha);
                }
            }

            System.out.printf("Valor total do inventario: %.2f%n", totalInventario);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
