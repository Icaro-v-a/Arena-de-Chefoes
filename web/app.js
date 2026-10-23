/**
 * Desafio do aluno: conectar os botões à API Java e atualizar a interface.
 * A página é servida pelo próprio Java em http://localhost:8080.
 */
const botoes = document.querySelectorAll('[data-acao]');
const botaoReiniciar = document.querySelector('#reiniciar');

async function carregarEstado() {
  // Exemplo de uma chamada ao servidor: GET /api/estado
  const resposta = await fetch('/api/estado');
  const dados = await resposta.json();
  renderizar(dados);
}

async function enviarAcao(acao) {
  // TODO 4: envie POST /api/jogar com 'acao' no corpo.
  // Dica: body: new URLSearchParams({ acao })
  // Dica: headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  // Use await resposta.json() e chame renderizar(dados).
  alert('TODO em app.js: enviar ação ' + acao + ' ao servidor Java.');
}

function renderizar(dados) {
  // Esta parte já funciona; observe a estrutura do JSON recebido do Java.
  document.querySelector('#vida-jogador-texto').textContent = `${dados.jogadorVida} / ${dados.jogadorMax} PV`;
  document.querySelector('#vida-chefe-texto').textContent = `${dados.chefeVida} / ${dados.chefeMax} PV`;
  document.querySelector('#pocoes').textContent = dados.pocoes;
  document.querySelector('#turno').textContent = `Turno ${dados.turno}`;
  document.querySelector('#mensagem').textContent = dados.mensagem;

  // TODO 5: atualize as barras de vida usando dados.jogadorVida / dados.jogadorMax
  // TODO 6: se dados.encerrado for true, desabilite os botões.
  // TODO 7: mostre uma mensagem de vitória ou derrota em #resultado.
  // TODO 8: atualize o aviso em #aviso usando dados.aviso.
  //          Ele mostra ANTES da ação se o próximo ataque será especial.
}

botoes.forEach(botao => {
  botao.addEventListener('click', () => enviarAcao(botao.dataset.acao));
});

botaoReiniciar.addEventListener('click', async () => {
  const resposta = await fetch('/api/reiniciar', { method: 'POST' });
  const dados = await resposta.json();
  renderizar(dados);
});

carregarEstado().catch(erro => {
  document.querySelector('#mensagem').textContent = 'Falha ao conectar ao Java: ' + erro.message;
});
