// inspirações que utilizamos:
// tabelas https://pt.stackoverflow.com/questions/510792/como-formatar-uma-string-para-adicionar-espa%C3%A7os-%C3%A0-direita-numa-tabela-impressa
// transformar int em string https://pt.stackoverflow.com/questions/43354/diferen%C3%A7a-entre-integer-valueofstring-e-integer-parseintstring
// arte ascii https://patorjk.com/

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

    public static void imprimirCabecalho(String titulo) {
        System.out.println("\n" + "=".repeat(60));
        System.out.printf("=========[ SISTEMA ] -%s%n", titulo.toUpperCase());
        System.out.println("=".repeat(60));
    }

    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        String arte = """

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
        System.out.println(arte);
        while (true) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println(
                "=========[ INICIANDO SISTEMA ] - BANCO DE DADOS ============"
            );
            System.out.println("=".repeat(60));
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
                System.out.println("[INFO] Entrando no Menu de 'Usuários'.");
            } else if (choice == 2) {
                System.out.println("[INFO] Entrando no Menu 'Empresas'.");
            } else {
                System.out.println("[ERROR] Input inválido, tente novamente.");
            }

            switch (choice) {
                case 1 -> usuarioRegistro(leitura);
                case 2 -> empresaRegistro(leitura);
                case 0 -> {
                    System.out.println(
                        "\n[INFO] Desconectando e encerrando o programa..."
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
            imprimirCabecalho(" MENU USUÁRIO ========================");

            System.out.println("[1] Create");
            System.out.println("[2] Read");
            System.out.println("[3] Update");
            System.out.println("[4] Delete");
            System.out.println("[0] Return");
            System.out.print("Escolha uma opção: ");

            int choice = leitura.nextInt();

            if (choice == 0) {
                System.out.println(
                    "[INFO] Encerrando Banco de Dados, até a próxima!"
                );
                break;
            }
            switch (choice) {
                case 1 -> {
                    imprimirCabecalho(" Executando operação: CREATE =========");
                    System.out.print("ID do usuário: ");
                    String id = leitura.next();
                    leitura.nextLine();
                    boolean testenumericoId = id.matches("[0-9]+");
                    if (testenumericoId == false) {
                        System.out.println(
                            "[ERROR] Informe o ID em caracteres numéricos."
                        );
                        return;
                    }
                    int idnum = Integer.parseInt(id);
                    if (idnum <= 0) {
                        System.out.println("[ERROR] Informe um ID válido.");
                        return;
                    }
                    System.out.print("Nome do usuário: ");
                    String nome = leitura.nextLine();
                    if (nome.length() < 2) {
                        System.out.println(
                            "[ERROR] Nome muito curto, informe um nome válido."
                        );
                        return;
                    }
                    if (
                        (nome.indexOf(" ") < 1) || (nome.lastIndexOf(" ") < 1)
                    ) {
                        System.out.println(
                            "[ERROR] Informe o nome e sobrenome."
                        );
                        return;
                    }
                    boolean testenumericoNome = nome.matches("[0-9]+");
                    if (testenumericoNome == true) {
                        System.out.println(
                            "[ERROR] O nome não pode conter números."
                        );
                        return;
                    }
                    System.out.print("Idade do usuário: ");
                    String idade = leitura.next();
                    leitura.nextLine();
                    boolean testenumericoIdade = idade.matches("[0-9]+");
                    if (testenumericoIdade == false) {
                        System.out.println(
                            "[ERROR] Informe a idade em caracteres numéricos."
                        );
                        return;
                    }
                    int idadenum = Integer.parseInt(idade);
                    if ((idadenum >= 122) || (idadenum < 18)) {
                        System.out.println("[ERROR] Informe uma idade válida.");
                        return;
                    }
                    System.out.print("Cidade do usuário: ");
                    String cidade = leitura.nextLine();
                    boolean testenumericoCidade = cidade.matches("[0-9]+");
                    if (testenumericoCidade == true) {
                        System.out.println(
                            "[ERROR] Sua cidade não pode conter números."
                        );
                        return;
                    }
                    if (cidade.length() < 2) {
                        System.out.println(
                            "[ERROR] Cidade muito curta, informe uma cidade válida."
                        );
                        return;
                    }
                    System.out.print("Email do usuário: ");
                    String email = leitura.next();
                    leitura.nextLine();
                    if (
                        email.indexOf("@") == -1 ||
                        (email.indexOf("@") != email.lastIndexOf("@"))
                    ) {
                        System.out.println(
                            "[ERROR] O Email deve conter um '@', informe um Email válido."
                        );
                        return;
                    }
                    if (email.indexOf(".") == -1) {
                        System.out.println(
                            "[ERROR] O Email deve conter pelo menos um '.', informe um Email válido."
                        );
                        return;
                    }

                    try (
                        FileWriter writeruser = new FileWriter(
                            filePathUsuarios,
                            true
                        )
                    ) {
                        writeruser
                            .append(id.trim())
                            .append(",")
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
                    imprimirCabecalho(" Executando operação: READ ===========");
                    try (
                        BufferedReader reader = new BufferedReader(
                            new FileReader(filePathUsuarios)
                        )
                    ) {
                        String mascara =
                            "%-4s | %-22s | %-5s | %-22s | %-35s%n";
                        System.out.println("=".repeat(95));
                        System.out.printf(
                            mascara,
                            "ID",
                            "NOME",
                            "IDADE",
                            "CIDADE",
                            "EMAIL"
                        );
                        System.out.println("=".repeat(95));
                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            String[] dados = linha.split(",");
                            if (dados.length >= 5) {
                                String id = dados[0].trim();
                                String nome = dados[1].trim();
                                String idade = dados[2].trim();
                                String cidade = dados[3].trim();
                                String email = dados[4].trim();

                                System.out.printf(
                                    mascara,
                                    id,
                                    nome,
                                    idade,
                                    cidade,
                                    email
                                );
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("[ERROR] Erro ao ler o arquivo.");
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    imprimirCabecalho(" Executando operação: UPDATE =========");
                    List<String> registrosUsuarios = lerRegistrosUsuarios();
                    if (registrosUsuarios.isEmpty()) {
                        System.out.println(
                            "[ERROR] Nenhum registro encontrado!"
                        );
                        return;
                    }
                    System.out.print(
                        "Informe o ID do usuário a ser atualizado: "
                    );
                    int idBusca = leitura.nextInt();
                    leitura.nextLine();
                    boolean encontrado = false;
                    for (int i = 0; i < registrosUsuarios.size(); i++) {
                        String[] dados = registrosUsuarios.get(i).split(",");
                        if (Integer.parseInt(dados[0].trim()) == idBusca) {
                            System.out.print("Informe o novo ID: ");
                            String novoId = leitura.next();
                            leitura.nextLine();
                            System.out.print("Informe o novo nome: ");
                            String novoNome = leitura.nextLine();
                            System.out.print("Informe a nova idade: ");
                            String novaIdade = leitura.next();
                            leitura.nextLine();
                            System.out.print("Informe a nova cidade: ");
                            String novaCidade = leitura.nextLine();
                            System.out.print("Informe o novo email: ");
                            String novoEmail = leitura.next();
                            leitura.nextLine();
                            registrosUsuarios.set(
                                i,
                                novoId +
                                    "," +
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
                        System.out.println(
                            "[SERVER SUCCESS] Registro atualizado com sucesso!"
                        );
                    } else {
                        System.out.println("[ERROR] Registro não encontrado.");
                    }
                }
                case 4 -> {
                    imprimirCabecalho(" Executando operação: DELETE =========");
                    List<String> registrosUsuarios = lerRegistrosUsuarios();
                    if (registrosUsuarios.isEmpty()) {
                        System.out.println(
                            "[ERROR] Nenhum registro encontrado."
                        );
                        return;
                    }

                    System.out.print(
                        "Informe o ID do registro a ser removido: "
                    );
                    String idBusca = leitura.next();
                    leitura.nextLine();
                    boolean encontrado = registrosUsuarios.removeIf(
                        registroUsuario ->
                            registroUsuario
                                .split(",")[0]
                                .equalsIgnoreCase(idBusca)
                    );

                    if (encontrado) {
                        escreverRegistrosUsuarios(registrosUsuarios);
                        System.out.println(
                            "[SERVER SUCCESS] Registro removido com sucesso!"
                        );
                    } else {
                        System.out.println("[ERROR] Registro não encontrado.");
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
            System.out.println("[ERROR] Erro ao ler o arquivo.");
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
            System.out.println("[ERROR] Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }

    public static void empresaRegistro(Scanner leitura) {
        while (true) {
            imprimirCabecalho(" MENU EMPRESA ========================");
            System.out.println("[1] Create");
            System.out.println("[2] Read");
            System.out.println("[3] Update");
            System.out.println("[4] Delete");
            System.out.println("[0] Return");
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
                    imprimirCabecalho(" Executando operação: CREATE =========");
                    System.out.print("ID da Empresa: ");
                    String id = leitura.next();
                    leitura.nextLine();
                    boolean testenumericoId = id.matches("[0-9]+");
                    if (testenumericoId == false) {
                        System.out.println(
                            "[ERROR] Informe o ID em caracteres numéricos."
                        );
                        return;
                    }
                    int idnum = Integer.parseInt(id);
                    if (idnum <= 0) {
                        System.out.println("[ERROR] Informe um ID válido.");
                        return;
                    }
                    System.out.print("Nome da Empresa: ");
                    String nome = leitura.nextLine();
                    if (nome.length() < 2) {
                        System.out.println(
                            "[ERROR] Nome muito curto, informe um nome válido."
                        );
                        return;
                    }
                    String testeSociedade = nome.toLowerCase();
                    if (
                        (testeSociedade.contains("ltda.") == false) &&
                        (testeSociedade.contains("s/a") == false)
                    ) {
                        System.out.println(
                            "[ERROR] O nome da empresa deve conter 'Ltda.' ou 'S/A'"
                        );
                        return;
                    }
                    System.out.print("Nome da Cidade: ");
                    String cidade = leitura.nextLine();
                    boolean testenumericoCidade = cidade.matches("[0-9]+");
                    if (testenumericoCidade == true) {
                        System.out.println(
                            "[ERROR] Sua cidade não pode conter números."
                        );
                        return;
                    }
                    if (cidade.length() < 2) {
                        System.out.println(
                            "[ERROR] Cidade muito curta, informe uma cidade válida."
                        );
                        return;
                    }
                    System.out.print("Informe o Email da Empresa: ");
                    String email = leitura.next();
                    leitura.nextLine();
                    if (
                        email.indexOf("@") == -1 ||
                        (email.indexOf("@") != email.lastIndexOf("@"))
                    ) {
                        System.out.println(
                            "[ERROR] O Email deve conter um '@', informe um Email válido."
                        );
                        return;
                    }
                    if (email.indexOf(".") == -1) {
                        System.out.println(
                            "[ERROR] O Email deve conter pelo menos um '.', informe um Email válido."
                        );
                        return;
                    }
                    System.out.print("Informe o CNPJ da Empresa: ");
                    String cnpj = leitura.next();
                    leitura.nextLine();
                    if (
                        cnpj.indexOf("/") == -1 ||
                        (cnpj.indexOf("/") != cnpj.lastIndexOf("/"))
                    ) {
                        System.out.println(
                            "[ERROR] CNPJ inválido! Informe no formato: 12.XXX.345/0000-99"
                        );
                        return;
                    }
                    if (
                        cnpj.indexOf("-") == -1 ||
                        cnpj.indexOf("-") != cnpj.lastIndexOf("-")
                    ) {
                        System.out.println(
                            "[ERROR] CNPJ inválido! Informe no formato: 12.XXX.345/0000-99"
                        );
                        return;
                    } else if (cnpj.trim().length() != 18) {
                        System.out.println(
                            "[ERROR] CNPJ inválido. Informe no formato: 12.XXX.345/0000-99"
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
                            .append(id.trim())
                            .append(",")
                            .append(nome.trim())
                            .append(",")
                            .append(cidade.trim())
                            .append(",")
                            .append(email.trim())
                            .append(",")
                            .append(cnpj.trim())
                            .append("\n");
                        System.out.println(
                            "[SERVER SUCCESS]Registro adicionado com sucesso!"
                        );
                    } catch (IOException e) {
                        System.out.println("[ERROR] Erro ao gravar no arquivo");
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    imprimirCabecalho(" Executando operação: READ ===========");
                    try (
                        BufferedReader reader = new BufferedReader(
                            new FileReader(filePathEmpresas)
                        )
                    ) {
                        String mascara =
                            "%-4s | %-21s | %-22s | %-47s | %-35s%n";
                        System.out.println("=".repeat(122));
                        System.out.printf(
                            mascara,
                            "ID",
                            "NOME",
                            "CIDADE",
                            "EMAIL",
                            "CNPJ"
                        );
                        System.out.println("=".repeat(122));
                        String linha;
                        while ((linha = reader.readLine()) != null) {
                            String[] dados = linha.split(",");
                            if (dados.length >= 5) {
                                String id = dados[0].trim();
                                String nome = dados[1].trim();
                                String cidade = dados[2].trim();
                                String email = dados[3].trim();
                                String cnpj = dados[4].trim();

                                System.out.printf(
                                    mascara,
                                    id,
                                    nome,
                                    cidade,
                                    email,
                                    cnpj
                                );
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("[ERROR] Erro ao ler o arquivo.");
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    imprimirCabecalho(" Executando operação: UPDATE =========");
                    List<String> registrosEmpresas = lerRegistrosEmpresas();
                    if (registrosEmpresas.isEmpty()) {
                        System.out.println(
                            "[ERROR] Nenhum registro encontrado!"
                        );
                        return;
                    }
                    System.out.print(
                        "Informe o ID do usuário a ser atualizado: "
                    );
                    String idBusca = leitura.next();
                    leitura.nextLine();
                    boolean encontrado = false;
                    for (int i = 0; i < registrosEmpresas.size(); i++) {
                        String[] dados = registrosEmpresas.get(i).split(",");
                        if (dados[0].equalsIgnoreCase(idBusca)) {
                            System.out.print("Informe o novo ID: ");
                            String novoId = leitura.next();
                            leitura.nextLine();
                            System.out.print("Informe o novo nome: ");
                            String novoNome = leitura.nextLine();
                            System.out.print("Informe a nova cidade: ");
                            String novaCidade = leitura.nextLine();
                            System.out.print("Informe o novo email: ");
                            String novoEmail = leitura.next();
                            leitura.nextLine();
                            System.out.print("Informe o novo CNPJ: ");
                            String novoCnpj = leitura.next();
                            leitura.nextLine();
                            registrosEmpresas.set(
                                i,
                                novoId.trim() +
                                    "," +
                                    novoNome.trim() +
                                    "," +
                                    novaCidade.trim() +
                                    "," +
                                    novoEmail.trim() +
                                    "," +
                                    novoCnpj.trim()
                            );
                            encontrado = true;
                            break;
                        }
                    }
                    if (encontrado) {
                        escreverRegistrosEmpresas(registrosEmpresas);
                        System.out.println(
                            "[SERVER SUCCESS] Registro atualizado com sucesso!"
                        );
                    } else {
                        System.out.println("[ERROR] Registro não encontrado.");
                    }
                }
                case 4 -> {
                    imprimirCabecalho(" Executando operação: DELETE =========");
                    List<String> registrosEmpresas = lerRegistrosEmpresas();
                    if (registrosEmpresas.isEmpty()) {
                        System.out.println(
                            "[ERROR] Nenhum registro encontrado."
                        );
                        return;
                    }

                    System.out.print(
                        "Informe o ID do registro a ser removido: "
                    );
                    String idBusca = leitura.next();
                    leitura.nextLine();
                    boolean encontrado = registrosEmpresas.removeIf(
                        registroEmpresa ->
                            registroEmpresa
                                .split(",")[0]
                                .equalsIgnoreCase(idBusca)
                    );

                    if (encontrado) {
                        escreverRegistrosEmpresas(registrosEmpresas);
                        System.out.println(
                            "[SERVER SUCCESS] Registro removido com sucesso!"
                        );
                    } else {
                        System.out.println("[ERROR] Registro não encontrado.");
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
            System.out.println("[ERROR] Erro ao ler o arquivo.");
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
            System.out.println("[ERROR] Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }
}
