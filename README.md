# Simulador de Carro

Programa em Java, executado no terminal, que simula o controle básico de um carro por meio de um menu interativo. Exercício prático do bootcamp gratuito de Java da **DIO** em parceria com o **Itaú**.

## O desafio

Criar um carro controlado por menu, com as funções: ligar, desligar, acelerar, diminuir velocidade, virar para a esquerda/direita, verificar velocidade e trocar de marcha. O carro nasce desligado, em ponto morto e com velocidade 0, e um carro desligado não executa nenhuma função.

## Funcionalidades

| Opção | Função | Regras principais |
|---|---|---|
| 1 | Ligar o carro | - |
| 2 | Desligar o carro | Só em ponto morto (marcha 0) e parado |
| 3 | Acelerar | Só ligado e com marcha engatada, até o limite da marcha atual |
| 4 | Diminuir velocidade | Só ligado e com marcha engatada, até o mínimo da marcha atual |
| 5 | Virar para esquerda/direita | Só ligado e com velocidade entre 1 e 40 km/h |
| 6 | Verificar velocidade | Só com o carro ligado |
| 7 | Trocar de marcha | Só ligado, de 0 a 6, sem pular marchas, respeitando a velocidade |
| 8 | Sair | Encerra o programa |

### Faixa de velocidade por marcha

| Marcha | Velocidade |
|---|---|
| 1ª | 0 a 20 km/h |
| 2ª | 21 a 40 km/h |
| 3ª | 41 a 60 km/h |
| 4ª | 61 a 80 km/h |
| 5ª | 81 a 100 km/h |
| 6ª | 101 a 120 km/h |

## Decisões de implementação

O enunciado tem uma contradição: na 1ª marcha o limite é 20 km/h, mas a 2ª só começa em 21 km/h, então com incremento de 1 km/h o carro nunca poderia trocar de marcha. As escolhas abaixo resolvem isso:

- **Acelerar e diminuir** percorrem a velocidade de 1 em 1 km/h com uma pausa entre cada passo, até o teto (ou piso) da marcha atual, em uma única ação do menu.
- **Subir de marcha** só é permitido quando o carro está no teto da marcha atual. **Descer** só quando está no piso.
- **Virar** exige velocidade entre 1 e 40 km/h, conforme o enunciado.

## Estrutura do projeto

- `Carro.java`: classe com o estado do carro (ligado, marcha, velocidade) e as regras de cada função. Os métodos retornam `true` ou `false` indicando se a ação foi aceita.
- `Main.java`: menu interativo, leitura do teclado e mensagens ao usuário.

A regra de negócio fica na classe `Carro` e a interação com o usuário na `Main`, mantendo as responsabilidades separadas.

## Conceitos praticados

- Orientação a objetos: classes, atributos privados, getters e métodos
- Estruturas condicionais: `if/else` e `switch`
- Laços de repetição: `do-while` e `while`
- Operadores lógicos: `&&`, `||` e `!`
- Entrada de dados com `Scanner`
- Pausa com `Thread.sleep` e tratamento de exceção com `try/catch`
