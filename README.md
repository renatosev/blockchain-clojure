# Blockchain API em Clojure

## Descrição do Projeto

API em Clojure para registrar transações em uma blockchain. Usa SHA-256 e Prova de Trabalho (PoW) para garantir a integridade e imutabilidade dos blocos.

### Conceitos da Blockchain

- **Bloco**: Um registro que contém uma lista de transações. Cada bloco é vinculado ao bloco anterior, formando uma cadeia.
- **Bloco Gênese**: O bloco inicial da cadeia, que define o estado inicial do sistema.
- **Hash**: Uma assinatura criptográfica gerada pela função SHA-256. Cada bloco contém um hash, que é uma combinação dos dados do bloco e do hash do bloco anterior.
- **Nonce**: Um número gerado durante o processo de mineração do bloco, necessário para criar um hash com um certo número de zeros à esquerda.

### Funcionalidades

- **Registro de Transações**: Adiciona transações ao blockchain.
- **Mineração de Blocos**: Gera blocos válidos com base no algoritmo de Prova de Trabalho (PoW).
- **Verificação de Imutabilidade**: Assegura que cada bloco contém o hash do bloco anterior, garantindo a integridade da cadeia.

### Estrutura dos Blocos

Cada bloco na blockchain contém as seguintes informações:

- **Número do Bloco**: Identificador único e sequencial do bloco.
- **Nonce**: Número usado apenas uma vez para gerar um hash válido com 4 zeros à esquerda.
- **Dados**: Lista de transações armazenadas no bloco.
- **Hash Prévio**: Hash do bloco anterior.
- **Hash**: Hash do bloco atual, gerado a partir dos dados acima.

### Como Rodar o Projeto

1. **Clonar o Repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_REPOSITORIO>

2. **Instalar Dependências**:
    ```bash
    lein deps
3. **Rodar o Servidor**:
    ```bash
    lein ring server
    Abra o index.html na pasta resources/public
