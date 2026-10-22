public class Carro {

    private boolean carro = false; // estado do carro: true = ligado, false = desligado
    private int marcha = 0;        // marcha atual (0 = ponto morto)
    private int velocidade = 0;    // velocidade atual em km/h

    // Converte o boolean em texto pra exibir na tela
    public String getCarro() {
        return carro ? "Ligado" : "Desligado";
    }

    // Devolve a marcha atual (o atributo é private, então só se acessa por aqui)
    public int getMarcha() {
        return marcha;
    }

    // Devolve a velocidade atual
    public int getVelocidade() {
        return velocidade;
    }

    // Devolve true se o carro está ligado (usado pela Main pra decidir mensagens)
    public boolean estaLigado(){
        return carro;
    }

    // Calcula a velocidade mínima (piso) da marcha atual
    // Usado no desacelerar e no trocarMarcha, pra não repetir a conta
    private int calcularPiso() {
        if (marcha <= 1) { // ponto morto e 1ª marcha: piso é 0
            return 0;
        }
        return (marcha - 1) * 20 + 1; // demais marchas: teto da anterior + 1
    }

    // Metodos

    // Liga o carro
    public boolean ligarCarro() {
        carro = true;
        return carro;
    }

    // Desliga o carro, mas só se estiver em ponto morto e parado
    public boolean desligarCarro() {
        if (!carro) { // já está desligado: recusa
            return false;
        }
        if (marcha != 0 || velocidade != 0) { // fora do ponto morto ou em movimento: recusa
            return false;
        }
        carro = false;
        return true; // true = deu certo
    }

    // Metodo pra troca de marcha
    // Só aceita ligado, marcha de 0 a 6 e marcha vizinha, respeitando a velocidade
    public boolean trocarMarcha(int marchaCarro) {
        if (!carro) { // desligado: recusa
            return false;
        }
        if (marchaCarro < 0 || marchaCarro > 6) { // marcha inexistente: recusa
            return false;
        }

        if (marchaCarro == marcha + 1) { // quer subir de marcha
            if (velocidade != marcha * 20) { // só sobe quando chegou no teto da marcha atual
                return false;
            }
        } else if (marchaCarro == marcha - 1) { // quer descer de marcha
            if (velocidade > calcularPiso()) { // só desce quando chegou no piso da marcha atual
                return false;
            }
        } else { // não é marcha vizinha (pulo ou mesma marcha): recusa
            return false;
        }

        marcha = marchaCarro; // passou em todas as regras: atualiza a marcha
        return true;
    }

    // Metodo acelerar
    // Sobe 1 km por vez até o teto da marcha atual (marcha * 20)
    public boolean acelerar(){
        if(!carro){ // desligado: recusa
            return false;
        }else if (marcha == 0) { // ponto morto: recusa
            return false;
        }else if (velocidade >= marcha * 20){ // já está no teto: recusa
            return false;
        }
        while (velocidade < marcha * 20) {
            velocidade++;
            System.out.println(velocidade); // mostra cada valor
            try {
                Thread.sleep(500); // pausa de meio segundo pra simular a aceleração
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    // Metodo desacelerar
    // Desce 1 km por vez até o piso da marcha atual
    public boolean desacelerar(){
        if(!carro){ // desligado: recusa
            return false;
        }
        if (marcha == 0) { // ponto morto: recusa
            return false;
        }

        int piso = calcularPiso(); // velocidade mínima da marcha atual

        if (velocidade <= piso) { // já está no piso: não tem mais o que frear
            return false;
        }

        // desce 1 km por vez até chegar no piso, mostrando cada valor
        while (velocidade > piso) {
            velocidade--;
            System.out.println(velocidade);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    // Metodo virar: só diz se o carro pode virar (não precisa saber a direção)
    public boolean virar(){
        if(!carro){ // desligado: recusa
            return false;
        }
        if(velocidade < 1 || velocidade > 40){ // fora da faixa de 1 a 40 km/h: recusa
            return false;
        }
        return true; // true = pode virar
    }
}