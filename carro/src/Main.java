import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var scanner = new Scanner(System.in); // lê o que o usuário digita no teclado
        var carro = new Carro();              // cria o carro (nasce desligado, marcha 0, velocidade 0)
        int menu;                             // guarda a opção escolhida no menu

        do{
            // mostra o menu, uma opção por linha
            System.out.println("===== MENU DO CARRO =====");
            System.out.println("1 - Ligar o carro");
            System.out.println("2 - Desligar o carro");
            System.out.println("3 - Acelerar");
            System.out.println("4 - Diminuir velocidade");
            System.out.println("5 - Virar para esquerda/direita");
            System.out.println("6 - Verificar velocidade");
            System.out.println("7 - Trocar a marcha (0 a 6)");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: "); // print sem ln: o cursor fica na mesma linha
            menu = scanner.nextInt(); // lê a opção do usuário

            switch (menu){ // executa o bloco de acordo com a opção
                case 1: // ligar
                    carro.ligarCarro();
                    System.out.println(carro.getCarro()); // mostra "Ligado"
                    break;
                case 2: // desligar
                    if(carro.desligarCarro()){ // true = conseguiu desligar
                        System.out.println(carro.getCarro()); // mostra "Desligado"
                    }
                    else { // false = o carro recusou
                        System.out.println("Não foi possível desligar. O carro precisa estar ligado, em ponto morto (0) e parado");
                    }
                    break;
                case 3: // acelerar
                    if(carro.acelerar()){ // true = acelerou até o teto da marcha
                        System.out.println(carro.getVelocidade()); // mostra a velocidade final
                    }
                    else { // false = o carro recusou
                        System.out.println("Não foi possível acelerar. Verifique se o carro está ligado, se há uma marcha engatada e se a velocidade já atingiu o limite da marcha (troque de marcha para continuar)");
                    }
                    break;
                case 4: // diminuir velocidade
                    if(carro.desacelerar()){ // true = desceu até o piso da marcha
                        System.out.println(carro.getVelocidade()); // mostra a velocidade final
                    }
                    else { // false = o carro recusou
                        System.out.println("Não foi possível diminuir a velocidade. Verifique se o carro está ligado, se há uma marcha engatada e se a velocidade já está no mínimo da marcha");
                    }
                    break;
                case 5: // virar
                    System.out.println("Digite 1 para virar à esquerda ou 2 para virar à direita");
                    int direcao = scanner.nextInt(); // direção que o usuário escolheu

                    if(carro.virar()){ // true = o carro aceitou virar
                        if(direcao == 1){ // 1 = esquerda
                            System.out.println("Virou à esquerda");
                        } else if(direcao == 2){ // 2 = direita
                            System.out.println("Virou à direita");
                        } else { // qualquer outro número
                            System.out.println("Direção inválida");
                        }
                    } else { // false = o carro recusou
                        System.out.println("Não foi possível virar. O carro precisa estar ligado e a velocidade deve estar entre 1 e 40 km/h");
                    }
                    break;
                case 6: // verificar velocidade
                    if(!carro.estaLigado()){ // desligado: não mostra a velocidade
                        System.out.println("Ligue o carro para ver a velocidade");
                    }
                    else{
                        System.out.println("Velocidade atual: " + carro.getVelocidade() + " Km/h");
                    }
                    break;
                case 7: // trocar marcha
                    System.out.println("Você deseja colocar qual marcha?");
                    int marchaCarro = scanner.nextInt(); // marcha que o usuario pediu

                    if(carro.trocarMarcha(marchaCarro)){ // true = o carro aceitou a troca
                        System.out.println("Marcha atual: " + carro.getMarcha()); // mostra a marcha atual
                    }
                    else { // false = o carro recusou
                        System.out.println("Não foi possível trocar. O carro precisa estar ligado, a marcha deve ser vizinha (de 0 a 6) e a velocidade deve estar no limite da marcha atual");
                    }
                    break;
                case 8: // sair: só quebra o switch, quem encerra é o while
                    break;
                default: // qualquer número fora de 1 a 8
                    System.out.println("Opção inválida");
                    break;
            }
        }while (menu != 8); // repete o menu até o usuário digitar 8
    }
}