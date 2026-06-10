import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    private static final String filePathUsuarios = "registros.csv";
    private static final String filePathEmpresas = "registros-empresas.csv";

    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        while (true) {
            String banner = """

                88b           d88                                    88
                888b         d888                 ,d                 88
                88`8b       d8'88                 88                 88
                88 `8b     d8' 88  ,adPPYYba,  MM88MMM  ,adPPYba,    88,dPPYba,
                88  `8b   d8'  88  ""     `Y8    88    a8"     ""    88P'    "8a
                88   `8b d8'   88  ,adPPPPP88    88    8b            88       88
                88    `888'    88  88,    ,88    88,   "8a,   ,aa    88       88
                88     `8'     88  `"8bbdP"Y8    "Y888  `"Ybbd8"'    88       88



                I8,        8        ,8I                         88
                `8b       d8b       d8'                         88
                 "8,     ,8"8,     ,8"                          88
                  Y8     8P Y8     8P   ,adPPYba,   8b,dPPYba,  88   ,d8
                  `8b   d8' `8b   d8'  a8"     "8a  88P'   "Y8  88 ,a8"
                   `8a a8'   `8a a8'   8b       d8  88          8888[
                    `8a8'     `8a8'    "8a,   ,a8"  88          88`"Yba,
                     `8'       `8'      `"YbbdP"'   88          88   `Y8a

                """;
            System.out.println(banner);
            System.out.println("\n=== BANCO DE DADOS ===");
            System.out.println("[1] Usuário");
            System.out.println("[2] Empresa");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");

            int choice = leitura.nextInt();

            if (choice == 0) {
                System.out.println(
                    "[LOG] Encerrando Banco de Dados, até a próxima!"
                );
                break;
            } else if (choice == 1) {
                System.out.println("[LOG] Entrando no Menu de 'Usuários'.");
            } else if (choice == 2) {
                System.out.println("[LOG] Entrando no Menu 'Empresas'.");
            } else {
                System.out.println("[ERROR] Input inválido, tente novamente.");
            }

            switch (choice) {
                case 1 -> usuarioRegistro(leitura);
                case 2 -> empresaRegistro(leitura);
                case 0 -> {
                    System.out.println(
                        "\n[SERVER] Desconectando e encerrando o programa..."
                    );
                    System.exit(0);
                }
                default -> System.out.println(
                    "\n[ERROR] Opção inválida no servidor."
                );
            }
        }
    }

    public static void usuarioRegistro(Scanner leitura) {
        while (true) {
            System.out.println("\n=== MENU USUÁRIO ===");
            System.out.println("[1] Create");
            System.out.println("[2] Read");
            System.out.println("[3] Update");
            System.out.println("[4] Delete");
            System.out.println("[0] Exit");
            System.out.print("Escolha uma opção: ");

            int choice = leitura.nextInt();

            if (choice == 0) {
                System.out.println(
                    "[LOG] Encerrando Banco de Dados, até a próxima!"
                );
                break;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("[SERVER] Executando operação: CREATE");
                    System.out.print("Nome do usuário: ");
                    String nome = leitura.next();
                    if (nome.length() < 2) {
                        System.out.println("Informe um nome válido.");
                        return;
                    }
                    if (
                        (nome.indexOf(" ") < 1) || (nome.lastIndexOf(" ") < 1)
                    ) {
                        System.out.println("Informe o nome completo.");
                        return;
                    }
                    boolean testenumericoNome = nome.matches("[0-9]+");
                    if (testenumericoNome == true) {
                        System.out.println("O nome não pode conter números.");
                        return;
                    }
                    System.out.print("Idade do usuário: ");
                    String idade = leitura.next();
                    boolean testenumericoIdade = idade.matches("[0-9]+");
                    if (testenumericoIdade == false) {
                        System.out.println("Informe uma idade válida.");
                        return;
                    }
                    int idadenum = Integer.parseInt(idade);
                    if ((idadenum >= 122) || (idadenum < 18)) {
                        System.out.println("Informe uma idade válida.");
                        return;
                    }
                    System.out.print("Cidade do usuário: ");
                    String cidade = leitura.next();
                    boolean testenumericoCidade = cidade.matches("[0-9]+");
                    if (testenumericoCidade == true) {
                        System.out.println(
                            "Sua cidade não pode conter números."
                        );
                        return;
                    }
                    System.out.print("Email do usuário: ");
                    String email = leitura.next();
                    if (
                        email.indexOf("@") == -1 ||
                        (email.indexOf("@") != email.lastIndexOf("@"))
                    ) {
                        System.out.println("Email inválido!");
                        return;
                    }
                    if (email.indexOf(".") == -1) {
                        System.out.println("Email inválido!");
                        return;
                    }

                    try (
                        FileWriter writeruser = new FileWriter(
                            filePathUsuarios,
                            true
                        )
                    ) {
                        writeruser
                            .append(nome.trim())
                            .append(",")
                            .append(idade.trim())
                            .append(",")
                            .append(cidade.trim())
                            .append(",")
                            .append(email.trim())
                            .append("\n");
                        System.out.println(
                            "[SERVER SUCCESS] Registro adicionado com sucesso!"
                        );
                    } catch (IOException e) {
                        System.out.println(
                            "[SERVER ERROR] Erro ao gravar no arquivo."
                        );
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("[SERVER] Executando operação: READ");
                    try (
                        BufferedReader reader = new BufferedReader(
                            new FileReader(filePathUsuarios)
                        )
                    ) {
                        StringBuilder registrosUsuarios = new StringBuilder(
                            "Registros:\n"
                        );
                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            registrosUsuarios.append(linha).append("\n");
                        }
                        System.out.printf(registrosUsuarios.toString());
                    } catch (IOException e) {
                        System.out.println("Erro ao ler o arquivo.");
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    System.out.println("[SERVER] Executando operação: UPDATE");
                    List<String> registrosUsuarios = lerRegistrosUsuarios();
                    if (registrosUsuarios.isEmpty()) {
                        System.out.println("Nenhum registro encontrado!");
                        return;
                    }
                    System.out.println(
                        "Digite o nome do usuário a ser atualizado:"
                    );
                    String nomeBusca = leitura.next();
                    boolean encontrado = false;
                    for (int i = 0; i < registrosUsuarios.size(); i++) {
                        String[] dados = registrosUsuarios.get(i).split(",");
                        if (dados[0].equalsIgnoreCase(nomeBusca)) {
                            System.out.print("Digite o novo nome: ");
                            String novoNome = leitura.next();
                            System.out.print("Digite a nova idade: ");
                            String novaIdade = leitura.next();
                            System.out.print("Digite a nova cidade: ");
                            String novaCidade = leitura.next();
                            System.out.print("Digite o novo email: ");
                            String novoEmail = leitura.next();
                            registrosUsuarios.set(
                                i,
                                novoNome +
                                    "," +
                                    novaIdade +
                                    "," +
                                    novaCidade +
                                    "," +
                                    novoEmail
                            );
                            encontrado = true;
                            break;
                        }
                    }
                    if (encontrado) {
                        escreverRegistrosUsuarios(registrosUsuarios);
                        System.out.println("Registro atualizado com sucesso!");
                    } else {
                        System.out.println("Registro não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.println("[SERVER] Executando operação: DELETE");
                    List<String> registrosUsuarios = lerRegistrosUsuarios();
                    if (registrosUsuarios.isEmpty()) {
                        System.out.println("Nenhum registro encontrado.");
                        return;
                    }

                    System.out.println(
                        "Digite o nome do registro a ser removido: "
                    );
                    String nomeBusca = leitura.next();
                    boolean encontrado = registrosUsuarios.removeIf(
                        registroUsuario ->
                            registroUsuario
                                .split(",")[0]
                                .equalsIgnoreCase(nomeBusca)
                    );

                    if (encontrado) {
                        escreverRegistrosUsuarios(registrosUsuarios);
                        System.out.println("Registro removido com sucesso!");
                    } else {
                        System.out.println("Registro não encontrado.");
                    }
                }
                default -> System.out.println(
                    "[ERROR] Input de operação inválido."
                );
            }
        }
    }

    private static List<String> lerRegistrosUsuarios() {
        List<String> registrosUsuarios = new ArrayList<>();
        try (
            BufferedReader reader = new BufferedReader(
                new FileReader(filePathUsuarios)
            )
        ) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                registrosUsuarios.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
        return registrosUsuarios;
    }

    private static void escreverRegistrosUsuarios(
        List<String> registrosUsuarios
    ) {
        try (FileWriter writer = new FileWriter(filePathUsuarios)) {
            for (String registroUsuario : registrosUsuarios) {
                writer.append(registroUsuario).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }

    public static void empresaRegistro(Scanner leitura) {
        while (true) {
            System.out.println("\n=== MENU EMPRESA ===");
            System.out.println("[1] Create");
            System.out.println("[2] Read");
            System.out.println("[3] Update");
            System.out.println("[4] Delete");
            System.out.print("Escolha uma opção: ");

            int choice = leitura.nextInt();

            if (choice == 0) {
                System.out.println(
                    "[LOG] Encerrando Banco de Dados, até a próxima!"
                );
                break;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("[SERVER] Executando operação: CREATE");
                    System.out.println("Nome da Empresa: ");
                    String nome = leitura.next();
                    if (nome.length() < 2) {
                        System.out.println("Informe um nome válido.");
                        return;
                    }
                    String testeSociedade = nome.toLowerCase();
                    if (
                        (testeSociedade.contains("ltda.") == false) &&
                        (testeSociedade.contains("s/a") == false)
                    ) {
                        System.out.println(
                            "O nome da empresa deve conter 'Ltda.' ou 'S/A'"
                        );
                        return;
                    }
                    System.out.println("Nome da Cidade: ");
                    String cidade = leitura.next();
                    boolean testenumericoCidade = cidade.matches("[0-9]+");
                    if (testenumericoCidade == true) {
                        System.out.println(
                            "Sua cidade não pode conter números."
                        );
                        return;
                    }
                    System.out.println("Email da Empresa: ");
                    String email = leitura.next();
                    System.out.println("Digite o email:");
                    if (
                        email.indexOf("@") == -1 ||
                        (email.indexOf("@") != email.lastIndexOf("@"))
                    ) {
                        System.out.println("Email inválido!");
                        return;
                    }
                    if (email.indexOf(".") == -1) {
                        System.out.println("Email inválido!");
                        return;
                    }
                    System.out.println("CNPJ da Empresa: ");
                    String cnpj = leitura.next();
                    System.out.println("Digite o CNPJ;");
                    if (
                        cnpj.indexOf("/") == -1 ||
                        (cnpj.indexOf("/") != cnpj.lastIndexOf("/"))
                    ) {
                        System.out.println(
                            "CNPJ inválido! Digite no formato: 12.XXX.345/0000-99"
                        );
                        return;
                    }
                    if (
                        cnpj.indexOf("-") == -1 ||
                        cnpj.indexOf("-") != cnpj.lastIndexOf("-")
                    ) {
                        System.out.println(
                            "CNPJ inválido! Digite no formato: 12.XXX.345/0000-99" //máscara user friendly para cnpj
                        );
                        return;
                    } else if (cnpj.trim().length() != 16) {
                        System.out.println(
                            "CNPJ inválido. Digite no formato: 12.XXX.345/0000-99"
                        );
                        return;
                    }

                    try (
                        FileWriter writeruser = new FileWriter(
                            filePathEmpresas,
                            true
                        )
                    ) {
                        writeruser
                            .append(nome.trim())
                            .append(",")
                            .append(cidade.trim())
                            .append(",")
                            .append(email.trim())
                            .append(",")
                            .append(cnpj.trim())
                            .append("\n");
                        System.out.println("Registro adicionado com sucesso!");
                    } catch (IOException e) {
                        System.out.println("Erro ao gravar no arquivo");
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("[SERVER] Executando operação: READ");
                    try (
                        BufferedReader reader = new BufferedReader(
                            new FileReader(filePathEmpresas)
                        )
                    ) {
                        StringBuilder registrosEmpresas = new StringBuilder(
                            "Registros:\n"
                        );
                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            registrosEmpresas.append(linha).append("\n");
                        }
                        System.out.printf(registrosEmpresas.toString());
                    } catch (IOException e) {
                        System.out.println("Erro ao ler o arquivo.");
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    System.out.println("[SERVER] Executando operação: UPDATE");
                    List<String> registrosEmpresas = lerRegistrosEmpresas();
                    if (registrosEmpresas.isEmpty()) {
                        System.out.println("Nenhum registro encontrado!");
                        return;
                    }
                    System.out.println(
                        "Digite o nome do usuário a ser atualizado:"
                    );
                    String nomeBusca = leitura.next();
                    boolean encontrado = false;
                    for (int i = 0; i < registrosEmpresas.size(); i++) {
                        String[] dados = registrosEmpresas.get(i).split(",");
                        if (dados[0].equalsIgnoreCase(nomeBusca)) {
                            System.out.println("Digite o novo nome: ");
                            String novoNome = leitura.next();
                            System.out.println("Digite a nova cidade: ");
                            String novaCidade = leitura.next();
                            System.out.print("Digite o novo email: ");
                            String novoEmail = leitura.next();
                            System.out.print("Digite o novo CNPJ: ");
                            String novoCnpj = leitura.next();
                            registrosEmpresas.set(
                                i,
                                novoNome +
                                    "," +
                                    novaCidade +
                                    "," +
                                    novoEmail +
                                    "," +
                                    novoCnpj
                            );
                            encontrado = true;
                            break;
                        }
                    }
                    if (encontrado) {
                        escreverRegistrosEmpresas(registrosEmpresas);
                        System.out.println("Registro atualizado com sucesso!");
                    } else {
                        System.out.println("Registro não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.println("[SERVER] Executando operação: DELETE");
                    List<String> registrosEmpresas = lerRegistrosEmpresas();
                    if (registrosEmpresas.isEmpty()) {
                        System.out.println("Nenhum registro encontrado.");
                        return;
                    }

                    System.out.println(
                        "Digite o nome do registro a ser removido: "
                    );
                    String nomeBusca = leitura.next();
                    boolean encontrado = registrosEmpresas.removeIf(
                        registroEmpresa ->
                            registroEmpresa
                                .split(",")[0]
                                .equalsIgnoreCase(nomeBusca)
                    );

                    if (encontrado) {
                        escreverRegistrosEmpresas(registrosEmpresas);
                        System.out.println("Registro removido com sucesso!");
                    } else {
                        System.out.println("Registro não encontrado.");
                    }
                }
                default -> System.out.println(
                    "[ERROR] Input de operação inválido."
                );
            }
        }
    }

    private static List<String> lerRegistrosEmpresas() {
        List<String> registrosEmpresas = new ArrayList<>();
        try (
            BufferedReader reader = new BufferedReader(
                new FileReader(filePathEmpresas)
            )
        ) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                registrosEmpresas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
        return registrosEmpresas;
    }

    private static void escreverRegistrosEmpresas(
        List<String> registrosEmpresas
    ) {
        try (FileWriter writer = new FileWriter(filePathEmpresas)) {
            for (String registroEmpresa : registrosEmpresas) {
                writer.append(registroEmpresa).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }
}
