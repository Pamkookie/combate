package mortalKombatI;

import java.util.Scanner;

public class Combate {

    private String nome;
    private int ataque;
    private int armadura;
    private int vida;

    public Combate(String nome, int ataque, int armadura, int vida) {
        this.nome = nome;
        this.ataque = ataque;
        this.armadura = armadura;
        this.vida = vida;
    }

    public void takeDamage(int ataqueInimigo) {
        int dano = Math.max(ataqueInimigo - this.armadura, 1);
        this.vida = Math.max(this.vida - dano, 0);
    }

    public String status() {
        if (this.vida <= 0) {
            return "\033[31m" + this.nome + ": 0 de vida (morreu)" + "\033[0m";
        } else {
            return "\033[32m" + this.nome + ": " + this.vida + " de vida" + "\033[0m";
        }
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVida() {
        return vida;
    }

    public static void iniciarCombate(Scanner scanner) {
        System.out.println("Digite os dados do primeiro campeão:");
        System.out.print("Nome: ");
        String nome1 = scanner.nextLine();
        System.out.print("Vida inicial: ");
        int vida1 = scanner.nextInt();
        System.out.print("Ataque: ");
        int ataque1 = scanner.nextInt();
        System.out.print("Armadura: ");
        int armadura1 = scanner.nextInt();

        Combate campeao1 = new Combate(nome1, ataque1, armadura1, vida1);

        scanner.nextLine();

        System.out.println("Digite os dados do segundo campeão:");
        System.out.print("Nome: ");
        String nome2 = scanner.nextLine();
        System.out.print("Vida inicial: ");
        int vida2 = scanner.nextInt();
        System.out.print("Ataque: ");
        int ataque2 = scanner.nextInt();
        System.out.print("Armadura: ");
        int armadura2 = scanner.nextInt();

        Combate campeao2 = new Combate(nome2, ataque2, armadura2, vida2);

        System.out.print("Quantos turnos você deseja executar? ");
        int numeroDeTurnos = scanner.nextInt();

        for (int turno = 1; turno <= numeroDeTurnos; turno++) {
            System.out.println("Resultado do turno " + turno + ":");

            campeao1.takeDamage(campeao2.getAtaque());
            campeao2.takeDamage(campeao1.getAtaque());

            System.out.println(campeao1.status());
            System.out.println(campeao2.status());

            if (campeao1.getVida() == 0 || campeao2.getVida() == 0) {
                break;
            }

            System.out.println();
        }

        System.out.println("### FIM DO COMBATE ###");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcao;

        do {
            iniciarCombate(scanner);

            System.out.print("Deseja iniciar um novo combate? (S/N): ");
            opcao = scanner.next().toUpperCase();

            scanner.nextLine();
            System.out.println();
        } while (opcao.equals("S"));

        scanner.close();
        System.out.println("Programa encerrado.");
    }
}