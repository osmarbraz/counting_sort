/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplina: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Baseado nos slides 110 da aula do dia 15/09/2017  
 *
 * Página 140 Thomas H. Cormen 3 ed
 *
 * Algoritmo CountingSort/Ordenação Digital
 *
 * Atenção:
 * Vetor em java inicia em 0, os algoritmos consideram início em 1.
 * A subtração de -1 ocorre somente no local de acesso ao vetor ou matriz 
 * para manter a compatibilidade entre os algoritmos.
 * 
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

    /**
     * Retorna o maior elemento do vetor.
     * @param A vetor com os dados 
     * @return Um valor inteiro com o maior elemento do vetor
     */
    public static int maiorElemento(int[] A) {
        int maior = A[1-1];
        for (int i = 1; i <= A.length; i++) {
            if (A[i-1] > maior) {
                maior = A[i-1];
            }
        }
        return maior;
    }

    /**
     * Counting Sort.
     * Algoritmos de ordenação podem ser ou não in-place ou estáveis.
     * Um método de ordenação é estável se elementos iguais ocorrem no 
     * vetor ordenado na mesma ordem em que são passados na entrada.
     * O CountingSort é estável. 
     *
     * Complexidade para o pior caso Theta(k+n).
     * Complexidade para o caso médio/esperado Theta(k+n).
     * k pertence a O(n) portanto O(n)
     * 
     * @param A Vetor com os dados desordenados
     * @param B Vetor com os dados ordenados
     * @param n Quantidade de elementos do vetor
     * @param k Maior elemento do vetor A
     */
    public static void countingSort(int[] A, int[] B, int n, int k) {

        //Cria o vetor auxiliar C com base do maior elemento de A
        // Se k varia até 5, ou seja, de 0 a 5, então são necessários k+1 elementos
        int C[] = new int[k+1];
        
        //Inicializar com zero o vetor auxiliar C        
        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }

        //Realiza a contagem das ocorrencias
        //C[j] É o número de A[j] tais que A[j] = i
        for (int j = 1; j <= n; j++) {
            C[A[j-1]] = (C[A[j-1]]) + 1;
        }
        //Orrdenando os indices do vetor auxiliar C
        //C[i] é o número de js tais que A[j] 
        for (int i = 1; i <= k; i++) {
            C[i] = C[i] + C[i - 1];
        }
        // Classifica o vetor da direita para a esquerda
        // Procure no vetor de ocorrências a última ocorrência do valor dado
        // Coloca no vetor ordenado
        // Decrementa o índice da última ocorrência do valor dado
        // Continue com o valor anterior do vetor de entrada, termina se todos os valores já foram classificados
        // n - 1 pois vetor em java começa em 0 zero
        for (int j = n; j >= 1; j--) {
            B[C[A[j-1]] - 1] = A[j-1];
            C[A[j-1]] = C[A[j-1]] - 1;
        }
    }

    public static void main(String args[]) {
        
        //Vetor dos dados    
        int A[] = {5, 7, 6, 0, 1, 3, 2, 4};

        //Tamanho do vetor
        int n = A.length;

        System.out.println(">>> Algoritmo CountingSort/Ordenação Digital <<<");
        System.out.println("Original: ");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " - " + A[i]);
        }
        
        // Busca o maior elemento a fim de determinar o limite de número de 0 até o maior elemento
        int k = maiorElemento(A);
        //Vetor para receber os dados ordenados, com tamanho igual ao vetor A
        int B[] = new int[A.length];

        countingSort(A, B, n, k);

        System.out.println("Depois: ");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " - " + B[i]);
        }
    }
}