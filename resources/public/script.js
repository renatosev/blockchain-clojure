document.addEventListener('DOMContentLoaded', () => {
    const transactionForm = document.getElementById('transaction-form');
    const viewTransactionsBtn = document.getElementById('view-transactions');
    const transactionsList = document.getElementById('transactions-list');
    const createBlockBtn = document.getElementById('create-block');
    const viewBlocksBtn = document.getElementById('view-blocks');
    const blocksList = document.getElementById('blocks-list');
  
    transactionForm.addEventListener('submit', async (event) => {
      event.preventDefault();
      const valor = document.getElementById('valor').value;
      const tipo = document.getElementById('tipo').value;
      await criarTransacao({ valor: parseFloat(valor), tipo });
      transactionForm.reset();
    });
  
    viewTransactionsBtn.addEventListener('click', async () => {
      const transacoes = await lerTransacoes();
      exibirTransacoes(transacoes);
    });
  
    createBlockBtn.addEventListener('click', async () => {
      const transacoes = await lerTransacoes(); 
      await criarBloco({ data: 'Novo bloco', transacoes });
    });
  
    viewBlocksBtn.addEventListener('click', async () => {
      const blocos = await lerBlocos();
      exibirBlocos(blocos);
    });
  
    async function criarTransacao(transacao) {
      const response = await fetch('http://localhost:3000/criar-transacao', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(transacao)
      });
      const data = await response.json();
      console.log('Transação criada:', data);
    }
  
    async function lerTransacoes() {
      const response = await fetch('http://localhost:3000/ler-transacao');
      const data = await response.json();
      return data;
    }
  
    async function criarBloco(bloco) {
      const response = await fetch('http://localhost:3000/criar-bloco', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(bloco)
      });
      const data = await response.json();
      console.log('Bloco criado:', data);
    }
  
    async function lerBlocos() {
      const response = await fetch('http://localhost:3000/ler-bloco');
      const data = await response.json();
      return data;
    }
  
    function exibirTransacoes(transacoes) {
      transactionsList.innerHTML = '<h3>Transações</h3>';
      const ul = document.createElement('ul');
      transactionsList.appendChild(ul);
      transacoes.forEach(transacao => {
        const li = document.createElement('li');
        li.innerHTML = `ID: ${transacao.id}<br>Valor: ${transacao.valor}<br>Tipo: ${transacao.tipo}<br>`;
        ul.appendChild(li);
      });
    }    
    function exibirBlocos(blocos) {
      blocksList.innerHTML = '<h3>Blocos</h3>';
      blocos.forEach(bloco => {
        const blocoDiv = document.createElement('div');
        if (bloco['numero-bloco'] == 0){
        blocoDiv.innerHTML = `
          <strong>Número do Bloco:</strong> ${bloco['numero-bloco']}<br>
          <strong>Hash Prévio:</strong> ${bloco['prev-hash']}<br>
          <strong>Nonce:</strong> ${bloco.nonce}<br>
          <strong>Hash:</strong> ${bloco.hash}<br>
          <strong>Transações:</strong><br>
          <strong>Primeiro bloco da Blockchain</strong>
        `;
        } else if (bloco['numero-bloco'] != 0){
          blocoDiv.innerHTML = `
          <strong>Número do Bloco:</strong> ${bloco['numero-bloco']}<br>
          <strong>Hash Prévio:</strong> ${bloco['prev-hash']}<br>
          <strong>Nonce:</strong> ${bloco.nonce}<br>
          <strong>Hash:</strong> ${bloco.hash}<br>
          <strong>Transações:</strong>
        `;
        }
        const ul = document.createElement('ul');
        bloco.transacoes.forEach(transacao => {
          const li = document.createElement('li');
          li.innerHTML = `ID: ${transacao.id}<br>Valor: ${transacao.valor}<br>Tipo: ${transacao.tipo}`;
          ul.appendChild(li);
        });
        blocoDiv.appendChild(ul);
        blocksList.appendChild(blocoDiv);
      });
    }
  }
);
  